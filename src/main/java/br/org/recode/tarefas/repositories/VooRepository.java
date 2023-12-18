package br.org.recode.tarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.org.recode.tarefas.entities.Voo;

@Repository
public interface VooRepository extends JpaRepository<Voo, Integer> {

}
