package br.org.recode.tarefas.services;

import java.util.List;

import br.org.recode.tarefas.entities.Usuario;

public interface UsuarioService {
List<Usuario> getAllUsuarios ();

Usuario getUsuarioById (Long id);

Usuario saveUsuario (Usuario usuario);

Usuario updateUsuario (Long id, Usuario usuarioExistente);

void deleteUsuario (long id);



}