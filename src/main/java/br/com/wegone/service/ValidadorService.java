package br.com.wegone.service;

import br.com.wegone.exception.DadosIncompletosException;
import br.com.wegone.model.Orientacao;
import java.util.List;


public class ValidadorService {

        // Excessões para o Service das Orientações

        public static void validarInputVazio(String texto) {

            if (texto == null || texto.isBlank()) {

                throw new DadosIncompletosException("Informação não pode estar vazia.");

            }

        }

        public static void validarExistenciaOrientacao(Orientacao orientacao) {

            if (orientacao == null) {

                throw new DadosIncompletosException("Orientação não encontrada.");

            }

        }

        // Validações para o código de cadastro
        public static void validarCodigoCadastro(String codigo, List<Orientacao> listaOrientacoes) throws DadosIncompletosException {

            validarInputVazio(codigo);

            if (codigo.length() < 3) {
                throw new DadosIncompletosException("Código deve ter pelo menos 3 caracteres.");
            }

            if (!Character.isDigit(codigo.charAt(0))) {
                throw new DadosIncompletosException("Código deve começar com um número.");
            }

            // Duplicidade

            for (Orientacao orientacao : listaOrientacoes) {
                if (orientacao.getCodigo().equalsIgnoreCase(codigo)) {
                    throw new DadosIncompletosException("Este código já está cadastrado.");
                }
            }

        }

    }
