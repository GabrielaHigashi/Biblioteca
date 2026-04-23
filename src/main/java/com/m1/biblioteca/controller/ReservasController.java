package com.m1.biblioteca.controller;

import com.m1.biblioteca.model.Reserva;
import com.m1.biblioteca.repository.ReservaRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@SuppressWarnings("null") 
public class ReservasController {

    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping("/reservas")
    public String telaReservas(Model model, HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/login";
        }

        List<Reserva> listaReservas = reservaRepository.findAll();
        
        model.addAttribute("reservas", listaReservas);
        model.addAttribute("totalReservas", listaReservas.size());
        
        return "reservas";
    }

    @PostMapping("/reservas/salvar")
    public String salvarReserva(@ModelAttribute Reserva reserva, HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/login";
        }

        if (reserva != null) {
            reservaRepository.save(reserva);
        }
        
        return "redirect:/reservas";
    }
}