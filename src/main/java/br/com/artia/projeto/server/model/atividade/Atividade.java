package br.com.artia.projeto.server.model.atividade;

import lombok.Data;
import java.util.Date;

@Data
public class Atividade {

    private Long id;
    private String nome;
    private Date dataInicio;
    private Date dataFinal;
    private boolean finalizada;
    private Long idProjeto;
}
