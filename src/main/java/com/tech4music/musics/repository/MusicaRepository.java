package com.tech4music.musics.repository;
import com.tech4music.musics.model.Musica;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MusicaRepository extends MongoRepository<Musica, String> {
    
}
