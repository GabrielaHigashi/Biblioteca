package com.m1.biblioteca.controller;

import com.m1.biblioteca.model.Emprestimo;
import com.m1.biblioteca.repository.EmprestimoRepository;
import com.m1.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmprestimoController {

    
    @Autowired
    private EmprestimoRepository emprestimoRepository; 

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/emprestimo")
    public String telaEmprestimo() {
        return "emprestimo";
    }

    @PostMapping("/emprestimo/salvar")
    public String salvarEmprestimo(Emprestimo emprestimo) {
        emprestimo.setAtivo(true);
        emprestimoRepository.save(emprestimo);

        livroRepository.findByTitulo(emprestimo.getIdentificadorLivro()).ifPresent(livro -> {
            livro.setDisponivel(false);
            livroRepository.save(livro);
        });

        return "redirect:/acervo";
    }
}