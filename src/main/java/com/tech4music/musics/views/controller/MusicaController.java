package com.tech4music.musics.views.controller;

import java.util.List;
import java.util.Optional;

import com.tech4music.musics.model.Musica;
import com.tech4music.musics.service.MusicaService;
import com.tech4music.musics.shared.MusicaDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/musicas")
public class MusicaController {
    
    @Autowired
    private MusicaService _musicaService;

    @GetMapping
    public ResponseEntity<List<MusicaDto>> obterTodos(){
        
        if(_musicaService.obterTodos().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(_musicaService.obterTodos(), HttpStatus.OK);
    }

    @GetMapping(value="{id}")
    public ResponseEntity<MusicaDto> obterPorId(@PathVariable String id){
        Optional<MusicaDto> musica = _musicaService.obterPorId(id);

        if(musica.isPresent()) {
            return new ResponseEntity<>(
                new ModelMapper().map(musica.get(), MusicaDto.class),
                HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<MusicaDto> criarMusica(@RequestBody MusicaDto musica){
        MusicaDto criarMusica = _musicaService.criarMusica(musica);

        return new ResponseEntity<>(criarMusica, HttpStatus.CREATED);
    }

    @PutMapping(value="{id}")
        public ResponseEntity<MusicaDto> atualizarMusica(@PathVariable String id, @RequestBody Musica musica){
            ModelMapper mapper = new ModelMapper();

            MusicaDto atualizarMusica = mapper.map(musica, MusicaDto.class);

            return new ResponseEntity<>(_musicaService.atualizarMusica(id, atualizarMusica), HttpStatus.OK);
        }

    @DeleteMapping(value="{id}")
    public ResponseEntity<Void> removerMusica(@PathVariable String id){
        _musicaService.removerMusica(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
