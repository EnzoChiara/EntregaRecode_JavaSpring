package br.org.recode.tarefas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.recode.tarefas.entities.Reserva;
import br.org.recode.tarefas.services.ReservaService;
import br.org.recode.tarefas.entities.Usuario;
import br.org.recode.tarefas.services.UsuarioService;
import br.org.recode.tarefas.entities.Voo;
import br.org.recode.tarefas.services.VooService;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private VooService vooService;

    @GetMapping
    public String listarReservas(Model model) {
        List<Reserva> reservas = reservaService.getAllReservas();
        model.addAttribute("reservas", reservas);
        return "ListarReserva";
    }

    @GetMapping("/novareserva")
    public String exibirFormularioAdicao(Model model) {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        List<Voo> voos = vooService.getAllVoos();

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("voos", voos);

        Reserva reserva = new Reserva();
        model.addAttribute("reserva", reserva);

        return "reservaForm";
    }

    @PostMapping("/salvarreserva")
    public String salvarReserva(@ModelAttribute("reserva") @Validated Reserva reserva, BindingResult result) {
        reservaService.saveReserva(reserva);
        return "redirect:/reservas";
    }

    @GetMapping("/editarreserva/{IDReserva}")
    public String exibirFormularioEdicao(@PathVariable("IDReserva") int reservaId, Model model) {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        List<Voo> voos = vooService.getAllVoos();

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("voos", voos);

        Reserva reserva = reservaService.getReservaById(reservaId);
        model.addAttribute("reserva", reserva);

        return "reservaForm";
    }

    @PostMapping("/editarreserva/{IDReserva}")
    public String editarReserva(@PathVariable("IDReserva") int reservaId, @ModelAttribute("reserva") @Validated Reserva reserva, BindingResult result) {

        if (result.hasErrors()) {
           
            return "reservaForm";
        }

        Reserva reservaExistente = reservaService.getReservaById(reservaId);

        reservaExistente.setStatusReserva(reserva.getStatusReserva());
        reservaExistente.setDataHoraReserva(reserva.getDataHoraReserva());
        reservaExistente.setUsuario(reserva.getUsuario());
        reservaExistente.setVoo(reserva.getVoo());

        reservaService.updateReserva(reservaId, reservaExistente);

        return "redirect:/reservas";
    }

    @PostMapping("/deletarreserva/{IDReserva}")
    public String deletarReserva(@PathVariable int IDReserva) {
        reservaService.deleteReserva(IDReserva);
        return "redirect:/reservas";
    }
    
    
}
