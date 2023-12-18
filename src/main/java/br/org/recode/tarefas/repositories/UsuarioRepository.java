package br.org.recode.tarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.recode.tarefas.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@SuppressWarnings("unchecked")
	Usuario save(Usuario usuarioExistente);

}
