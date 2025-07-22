package com.example.projeto2_streaming_musicas.controller;

import com.example.projeto2_streaming_musicas.moodel.Singer;
import com.example.projeto2_streaming_musicas.service.SingerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class SingerController {

    @Autowired
    private final SingerService singerService;

    public SingerController(SingerService singerService, SingerService singerService1) {
        this.singerService = singerService1;
    }

    @GetMapping ( "/login" )
    public String login() {
        return "login";
    }

    @GetMapping ( "/singers" )
    public String singers(Model model) {
        model.addAttribute("singers", singerService.findAll());
        return "singers";
    }

    @GetMapping ( "/singers/add" )
    public String addSinger(){
        return "add_Singer";
    }

    @PostMapping ( "/singers/add" )
    public String addSinger(@Valid @ModelAttribute Singer singer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_Singer";
        }
        singerService.addSinger( singer );
        return "singers";
    }

    @GetMapping ( "/singers/delete/{id}" )
    public String deleteSinger(@PathVariable Long id) {
        singerService.deleteSinger( id );
        return "redirect:/singers";
    }

    @GetMapping ( "singer/edit/{id}" )
    public String editSinger(@PathVariable Long id, Model model) {
        Optional<Singer> singer = singerService.findById( id );
        if (singer.isPresent()) {
            model.addAttribute("singer", singer.get());
            return "editSinger";
        }
        return "redirect:/singers";
    }

    @PostMapping ( "/singer/edit/{id}" )
    public String updateBook(@PathVariable("id") Long id,
                             @Valid @ModelAttribute("book") Singer singer,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "editSinger";
        }

        Optional<Singer> existingSinger = singerService.findById(id);
        if (existingSinger.isPresent()) {
            Singer updatedSinger = existingSinger.get();
            updatedSinger.setFirstName(singer.getFirstName());
            updatedSinger.setLastName(singer.getLastName());
            updatedSinger.setBirthDate(singer.getBirthDate());
            singerService.addSinger(updatedSinger);
        }
        return "redirect:/singer";
    }

}
