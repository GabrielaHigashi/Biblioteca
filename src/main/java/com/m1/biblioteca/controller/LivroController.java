package com.m1.biblioteca.controller;

import com.m1.biblioteca.model.Livro;
import com.m1.biblioteca.repository.LivroRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SuppressWarnings("null")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/loginleitor";
        }

        List<Livro> todosLivros = livroRepository.findAll();
        
        model.addAttribute("totalTitulos", todosLivros.size());
        model.addAttribute("disponiveis", todosLivros.stream().filter(l -> !l.isEmManutencao()).count());
        model.addAttribute("manutencao", todosLivros.stream().filter(Livro::isEmManutencao).count());
        
        model.addAttribute("livros", todosLivros);

        return "dashboard";
    }

    @GetMapping("/acervo")
    public String exibirAcervo(@RequestParam(value = "search", required = false) String search, Model model,
                               HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/loginleitor";
        }

        List<Livro> listaLivros;
        if (search != null && !search.isEmpty()) {
            listaLivros = livroRepository.findByTituloContainingIgnoreCase(search);
            model.addAttribute("search", search);
        } else {
            listaLivros = livroRepository.findAll();
        }

        model.addAttribute("livros", listaLivros);
        model.addAttribute("totalTitulos", listaLivros.size());
        model.addAttribute("disponiveis", listaLivros.stream().filter(l -> !l.isEmManutencao()).count());
        model.addAttribute("manutencao", listaLivros.stream().filter(Livro::isEmManutencao).count());

        return "acervo";
    }

    @PostMapping("/livros/salvar")
    public String salvarLivro(@ModelAttribute Livro livro, HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/loginleitor";
        }

        livroRepository.save(livro);
        
        return "redirect:/acervo";
    }

    @GetMapping("/acervo/excluir/{id}")
    public String excluirLivro(@PathVariable String id, HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/loginleitor";
        }

        livroRepository.deleteById(id);
        return "redirect:/acervo";
    }
}