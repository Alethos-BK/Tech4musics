package com.tech4music.musics.service;

import java.util.List;
import java.util.Optional;

import com.tech4music.musics.shared.MusicaDto;

public interface MusicaService {
    MusicaDto criarMusica(MusicaDto musica);
    List<MusicaDto> obterTodos();
    Optional<MusicaDto> obterPorId(String id);
    MusicaDto atualizarMusica(String id, MusicaDto musica);
    void removerMusica(String id);

}
