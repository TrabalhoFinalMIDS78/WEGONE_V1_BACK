package br.com.wegone.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Usuario {
    private Integer id;
    private String matricula;
    private String nome;
    private String email;
    private String senha;
    private final LocalDateTime registro;
    private TipoUsuario tipoUsuario = TipoUsuario.USUARIO;

    // Construtor para novo usuário (id sempre null)
    public Usuario(String matricula, String nome, String email, String senha) {
        this.id        = null; // id é gerado no banco de dados
        this.matricula = matricula;
        this.nome      = nome;
        this.email     = email;
        this.senha     = senha;
        this.registro  = LocalDateTime.now();
    }

    // Getters e setters
    public Integer getId() { return id; }
    public void setId(Integer id) { 
        this.id = id; 
    }

    public String getMatricula() { 
        return matricula; 
    }
    public void setMatricula(String matricula) { 
        this.matricula = matricula; 
    }

    public String getNome() { 
        return nome; 
    }
    public void setNome(String nome) { 
        this.nome = nome; 
    }

    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }

    public String getSenha() { 
        return senha; 
    }
    public void setSenha(String senha) { 
        this.senha = senha; 
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void add(Usuario admin) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public enum TipoUsuario {
        ADMIN,
        USUARIO
    }

    public LocalDateTime getRegistro() { return registro; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario u = (Usuario) o;
        return Objects.equals(email.toLowerCase(), u.email.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(email.toLowerCase());
    }
}
