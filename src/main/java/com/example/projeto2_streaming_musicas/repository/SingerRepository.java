package com.example.projeto2_streaming_musicas.repository;

import com.example.projeto2_streaming_musicas.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingerRepository extends JpaRepository<Singer, Long> {
}