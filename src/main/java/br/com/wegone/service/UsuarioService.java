/*
 * 
 * package br.com.wegone.service;
 * 
 * import br.com.wegone.exception.DadosIncompletosException;
 * import br.com.wegone.model.Usuario;
 * 
 * import java.util.Optional;
 * 
 * public class UsuarioService {
 * 
 * private static IdiomaMensagens mensagem = new IdiomaMensagens();
 * 
 * public Usuario cadastrar(String matricula, String nome, String email, String
 * senha) {
 * // valida matrícula: 5 dígitos
 * if (matricula == null || !matricula.matches("\\d{5}")) {
 * throw new
 * DadosIncompletosException(mensagem.get("exception.user.invalid_matricula"));
 * }
 * // valida campos obrigatórios
 * if (nome == null || nome.isBlank()) {
 * throw new
 * DadosIncompletosException(mensagem.get("exception.dados.vazio.nome"));
 * }
 * if (email == null || email.isBlank()) {
 * throw new
 * DadosIncompletosException(mensagem.get("exception.dados.vazio.email"));
 * }
 * if (senha == null || senha.isBlank()) {
 * throw new
 * DadosIncompletosException(mensagem.get("exception.dados.vazio.senha"));
 * }
 * // verifica duplicidade
 * Optional<Usuario> byEmail = repo.findByEmail(email);
 * if (byEmail.isPresent()) {
 * throw new
 * DadosIncompletosException(mensagem.get("exception.dados.duplicate_email"));
 * }
 * Optional<Usuario> byMat = repo.findByMatricula(matricula);
 * if (byMat.isPresent()) {
 * throw new
 * DadosIncompletosException(mensagem.get("exception.dados.duplicate_matricula")
 * );
 * }
 * // cria e salva
 * Usuario u = new Usuario(matricula, nome, email, senha);
 * return repo.save(u);
 * }
 * 
 * public Usuario login(String cadastro, String senha) {
 * if (cadastro == null || cadastro.isBlank() || senha == null ||
 * senha.isBlank()) {
 * throw new
 * DadosIncompletosException(mensagem.get("exception.dados.vazio.login"));
 * }
 * Usuario u = repo.findByMatricula(cadastro)
 * .orElseThrow(() -> new
 * DadosIncompletosException(mensagem.get("exception.auth.user_not_found")));
 * if (!u.getSenha().equals(senha)) {
 * throw new
 * DadosIncompletosException(mensagem.get("exception.auth.wrong_password"));
 * }
 * return u;
 * }
 * }
 * 
 * /*
 * 
 */

// Implementar apenas após conexão com banco de dados