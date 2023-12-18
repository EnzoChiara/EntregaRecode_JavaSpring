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
import br.org.recode.tarefas.entities.Voo;
import br.org.recode.tarefas.services.VooService;

@Controller
@RequestMapping("/voos")
public class VooController {

    @Autowired
    private VooService vooService;

    @GetMapping
    public String listVoos(Model model) {
        List<Voo> voos = vooService.getAllVoos();
        model.addAttribute("voos", voos);
        return "ListarVoo";
    }

    @GetMapping("/novovoo")
    public String showFormForAdd(Model model) {
        Voo voo = new Voo();
        model.addAttribute("voo", voo);
        return "vooForm";
    }

    @PostMapping("/savevoo")
    public String saveVoo(@ModelAttribute("voo") @Validated Voo voo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "vooForm";
        }

        vooService.saveVoo(voo);
        return "redirect:/voos";
    }


    @GetMapping("/editarvoo/{IDVoo}")
    public String showFormForEdit(@PathVariable("IDVoo") int vooId, Model model) {
        Voo voo = vooService.getVooById(vooId);
        model.addAttribute("voo", voo);
        return "vooForm";
    }

    @PostMapping("/editarvoo/{IDVoo}")
    public String editVoo(@PathVariable("IDVoo") int vooId, @ModelAttribute("voo") Voo voo, BindingResult bindingResult) {

 
        if (bindingResult.hasErrors()) {
            
            return "vooForm";
        }

        Voo vooExistente = vooService.getVooById(vooId);

        vooExistente.setNumeroVoo(voo.getNumeroVoo());
        vooExistente.setAeroportoOrigem(voo.getAeroportoOrigem());
        vooExistente.setAeroportoDestino(voo.getAeroportoDestino());
        vooExistente.setDataHoraPartida(voo.getDataHoraPartida());
        vooExistente.setDataHoraChegada(voo.getDataHoraChegada());
        vooExistente.setCompanhiaAerea(voo.getCompanhiaAerea());
        vooExistente.setPreco(voo.getPreco());
        vooExistente.setNovaData(voo.getNovaData());

        vooService.updateVoo(vooId, vooExistente);

        return "redirect:/voos";
    }

    @PostMapping("/deletarvoo/{IDVoo}")
    public String deleteVoo(@PathVariable int IDVoo) {
        vooService.deleteVoo(IDVoo);
        return "redirect:/voos";
    }
}
