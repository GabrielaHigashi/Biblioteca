package com.m1.biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AtendimentoController {

    @GetMapping("/atendimento")
    public String telaAtendimento() {
        return "atendimento"; 
    }
}