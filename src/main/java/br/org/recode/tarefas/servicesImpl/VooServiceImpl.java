package br.org.recode.tarefas.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.org.recode.tarefas.entities.Voo;
import br.org.recode.tarefas.repositories.VooRepository;
import br.org.recode.tarefas.services.VooService;

@Service
public class VooServiceImpl implements VooService {

    @Autowired
    private VooRepository vooRepository;

    @Override
    public List<Voo> getAllVoos() {
        return vooRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Voo getVooById(int id) {
        return vooRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Voo saveVoo(Voo voo) {
        return vooRepository.save(voo);
    }

    @Override
    @Transactional
    public Voo updateVoo(int id, Voo vooAtualizado) {
        Voo vooExistente = vooRepository.findById(id).orElse(null);
        if (vooExistente != null) {
            vooExistente.setNumeroVoo(vooAtualizado.getNumeroVoo());
            vooExistente.setAeroportoOrigem(vooAtualizado.getAeroportoOrigem());
            vooExistente.setAeroportoDestino(vooAtualizado.getAeroportoDestino());
            vooExistente.setDataHoraPartida(vooAtualizado.getDataHoraPartida());
            vooExistente.setDataHoraChegada(vooAtualizado.getDataHoraChegada());
            vooExistente.setCompanhiaAerea(vooAtualizado.getCompanhiaAerea());
            vooExistente.setPreco(vooAtualizado.getPreco());
            vooExistente.setNovaData(vooAtualizado.getNovaData());
            return vooRepository.save(vooExistente);
        } else {
            throw new RuntimeException("Voo com o ID " + id + " não encontrado!");
        }
    }

    @Override
    @Transactional
    public void deleteVoo(int id) {
        vooRepository.deleteById(id);
    }
}
