package br.org.recode.tarefas.services;

import java.util.List;

import br.org.recode.tarefas.entities.Voo;

public interface VooService {
    List<Voo> getAllVoos();

    Voo getVooById(int id);

    Voo saveVoo(Voo voo);

    Voo updateVoo(int id, Voo vooAtualizado);

    void deleteVoo(int id);
}
