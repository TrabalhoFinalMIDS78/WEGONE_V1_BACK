package br.com.wegone.repository;

import br.com.wegone.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryUsuarioRepository {
    private final List<Usuario> usuarios = new ArrayList<>();
    private int sequence = 1;

    public Usuario save(Usuario u) {
        if (u.getId() == null) {
            u.setId(sequence++);
            usuarios.add(u);
        } else {
            usuarios.removeIf(existing -> existing.getId().equals(u.getId()));
            usuarios.add(u);
        }
        return u;
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuarios.stream()
            .filter(u -> u.getEmail().equalsIgnoreCase(email))
            .findFirst();
    }

    public Optional<Usuario> findByMatricula(String matricula) {
        return usuarios.stream()
            .filter(u -> u.getMatricula().equals(matricula))
            .findFirst();
    }

    public List<Usuario> findAll() {
        return new ArrayList<>(usuarios);
    }
}