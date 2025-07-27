package com.example.projeto2_streaming_musicas.service;

import com.example.projeto2_streaming_musicas.model.Singer;
import com.example.projeto2_streaming_musicas.repository.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SingerService {

    @Autowired
    private final SingerRepository singerRepository;

    public SingerService(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    public List<Singer> findAll() {
        return singerRepository.findAll();
    }

    public Optional<Singer> findById(Long aLong) {
        return singerRepository.findById(aLong);
    }

    @Transactional
    public Singer addSinger(Singer singer) {
        return singerRepository.save(singer);
    }

    public void deleteSinger( Long aLong) {
        singerRepository.deleteById( aLong );
    }

}
