package br.org.recode.tarefas.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.org.recode.tarefas.entities.Usuario;
import br.org.recode.tarefas.services.UsuarioService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuario", new Usuario());
        return "ListarUsuarios";
    }

    // FORMULÁRIO DE CRIAÇÃO
    @GetMapping("/novo")
    public String showFormForAdd(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "usuarioForm";
    }

    // PERSISTÊNCIA DA CRIAÇÃO
    @PostMapping("/save")
    public String saveUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.saveUsuario(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{IDUsuario}")
    public String showFormForEdit(@PathVariable("IDUsuario") Long userId, Model model) {
        Usuario usuario = usuarioService.getUsuarioById(userId);
        model.addAttribute("usuario", usuario);
       return "usuarioForm";
    }

    @PostMapping("/editar/{IDUsuario}")
    public String editUser(@PathVariable("IDUsuario") Long userId, @ModelAttribute("usuario") Usuario usuario) {
        Usuario usuarioExistente = usuarioService.getUsuarioById(userId);

        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setSobrenome(usuario.getSobrenome());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setSenha(usuario.getSenha());
        usuarioExistente.setInformacoesContato(usuario.getInformacoesContato());

        usuarioService.updateUsuario(userId, usuarioExistente);

        return "redirect:/usuarios";
    }

    // EXCLUIR CATEGORIA
    @PostMapping("/deletar/{IDUsuario}")
    public String deleteUsuario(@PathVariable("IDUsuario") Long id) {
        usuarioService.deleteUsuario(id);
        return "redirect:/usuarios";
    }
}
