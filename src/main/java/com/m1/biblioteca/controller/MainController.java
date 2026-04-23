package com.m1.biblioteca.controller;

import com.m1.biblioteca.model.Usuario;
import com.m1.biblioteca.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/")
    public String index() {
        return "redirect:/loginleitor";
    }

    @GetMapping("/loginleitor")
    public String telaLoginLeitor() {
        return "loginleitor";
    }

    @GetMapping("/loginbibliotecario")
    public String telaLoginBibliotecario() {
        return "login-bibliotecario";
    }

    @PostMapping("/loginleitor")
    public String processarLoginLeitor(@RequestParam String usuario,
            @RequestParam String senha,
            HttpSession session,
            Model model) {
        Optional<Usuario> user = usuarioRepository.findByRegistro(usuario);

        if (user.isPresent() && encoder.matches(senha, user.get().getSenha())) {
            session.setAttribute("usuarioLogado", user.get());
            return "redirect:/acervo";
        }

        model.addAttribute("erro", "Credenciais incorretas!");
        return "loginleitor";
    }

    @GetMapping("/cadastro-usuario")
    public String telaCadastro() {
        return "cadastro-usuario";
    }

    @PostMapping("/cadastro-usuario")
    public String cadastrarNovoUsuario(@RequestParam String nome,
            @RequestParam String registro,
            @RequestParam String senha,
            Model model) {
        try {
            Usuario novo = new Usuario();
            novo.setNomeCompleto(nome);
            novo.setRegistro(registro);
            novo.setSenha(encoder.encode(senha)); 
            novo.setTipoUsuario("LEITOR");

            usuarioRepository.save(novo);
            return "redirect:/loginleitor?sucesso=true";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao realizar cadastro. Tente outro número de registro.");
            return "cadastro-usuario";
        }
    }
}