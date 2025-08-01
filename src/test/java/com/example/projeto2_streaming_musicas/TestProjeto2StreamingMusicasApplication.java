package com.example.projeto2_streaming_musicas;

import org.springframework.boot.SpringApplication;

public class TestProjeto2StreamingMusicasApplication {

	public static void main(String[] args) {
		SpringApplication.from(Projeto2StreamingMusicasApplication::main)
				.with(TestcontainersConfiguration.class)
				.run(args);
	}

}
