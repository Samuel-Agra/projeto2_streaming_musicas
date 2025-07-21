package com.example.projeto2_streaming_musicas.repository;

import com.example.projeto2_streaming_musicas.moodel.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingerRepository extends JpaRepository<Singer, Long> {
    Long id(Long id);
}