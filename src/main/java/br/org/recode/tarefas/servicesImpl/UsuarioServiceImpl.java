package br.org.recode.tarefas.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.org.recode.tarefas.entities.Usuario;
import br.org.recode.tarefas.repositories.UsuarioRepository;
import br.org.recode.tarefas.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario updateUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if (usuarioExistente != null) {
            usuarioExistente.setNome(usuarioAtualizado.getNome());
            
            return usuarioRepository.save(usuarioExistente);
        } else {
            throw new RuntimeException("Usuário com o ID " + id + " não encontrado!");
        }
    }

    @Override
    @Transactional
    public void deleteUsuario(long id) {
        usuarioRepository.deleteById(id);
    }
}
