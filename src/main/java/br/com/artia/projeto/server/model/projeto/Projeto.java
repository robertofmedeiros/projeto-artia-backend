package br.com.artia.projeto.server.model.projeto;

import lombok.Data;

import java.util.Date;

@Data
public class Projeto {

    private Long id;
    private String nome;
    private Date dataInicio;
    private Date dataFinal;
}
