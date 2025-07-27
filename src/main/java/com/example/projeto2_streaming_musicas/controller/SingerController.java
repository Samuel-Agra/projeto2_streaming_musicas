package com.example.projeto2_streaming_musicas.controller;

import com.example.projeto2_streaming_musicas.model.Singer;
import com.example.projeto2_streaming_musicas.service.SingerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    public SingerController(SingerService singerService) {
        this.singerService = singerService;
    }

    @GetMapping ( "/login" )
    public String login() {
        return "login";
    }

    @GetMapping ( "/singers" )
    public String listSingers(Model model) {
        model.addAttribute("singers", singerService.findAll());
        return "singers";
    }

    @ModelAttribute( "singer" )
    private Singer bindBookObjectToHtmlForm() {
        return new Singer();
    }

    @GetMapping ( "/singers/add" )
    public String showAddForm(Model model) {
        model.addAttribute("singer", new Singer());
        return "add_singer";
    }

    @PostMapping ( "/singers/add" )
    public String addSinger(@Valid @ModelAttribute Singer singer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_singer";
        }
        singerService.addSinger( singer );
        return "redirect:/singers";
    }

    @GetMapping ( "/singers/delete/{id}" )
    public String deleteSinger(@PathVariable Long id) {
        singerService.deleteSinger( id );
        return "redirect:/singers";
    }

    @GetMapping ( "/singers/edit/{id}" )
    public String editSinger(@PathVariable("id") Long id, Model model) {
        Optional<Singer> singer = singerService.findById( id );
        if (singer.isPresent()) {
            model.addAttribute("singer", singer.get());
            return "edit_singer";
        }
        return "redirect:/singers";
    }

    @PostMapping ( "/singers/edit/{id}" )
    public String updateSinger(@PathVariable("id") Long id,
                             @Valid @ModelAttribute("singer") Singer singer,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "edit_singer";
        }

        Optional<Singer> existingSinger = singerService.findById(id);
        if (existingSinger.isPresent()) {
            Singer updatedSinger = existingSinger.get();
            updatedSinger.setFirstName(singer.getFirstName());
            updatedSinger.setLastName(singer.getLastName());
            updatedSinger.setBirthDate(singer.getBirthDate());
            singerService.addSinger(updatedSinger);
        }
        return "redirect:/singers";
    }

}
