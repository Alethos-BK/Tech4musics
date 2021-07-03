package com.tech4music.musics.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tech4music.musics.model.Musica;
import com.tech4music.musics.repository.MusicaRepository;
import com.tech4music.musics.shared.MusicaDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class MusicaServiceImpl implements MusicaService{
    
    @Autowired
    private MusicaRepository _musicaRepository;

    @Override
    public MusicaDto criarMusica(MusicaDto musica) {
        return salvarMusica(musica);
    }

    @Override
    public List<MusicaDto> obterTodos() {
        List<Musica> musicas = _musicaRepository.findAll();

        return musicas.stream().map(musica -> new ModelMapper().map(musica, MusicaDto.class))
        .collect(Collectors.toList());
    }

    @Override
    public Optional<MusicaDto> obterPorId(String id) {
        Optional<Musica> musica = _musicaRepository.findById(id);

        if(musica.isPresent()) {
            return Optional.of(new ModelMapper().map(musica.get(), MusicaDto.class));
        }

        return Optional.empty();
    }

    @Override
    public MusicaDto atualizarMusica(String id, MusicaDto musica){
        musica.setId(id);

        return salvarMusica(musica);
    }

    @Override
    public void removerMusica(String id) {
        _musicaRepository.deleteById(id);
    }

    private MusicaDto salvarMusica(MusicaDto musica) {
        ModelMapper mapper = new ModelMapper();
        Musica musicaEntidade = mapper.map(musica, Musica.class);
        musicaEntidade = _musicaRepository.save(musicaEntidade);

        return mapper.map(musicaEntidade, MusicaDto.class);
    }
}
