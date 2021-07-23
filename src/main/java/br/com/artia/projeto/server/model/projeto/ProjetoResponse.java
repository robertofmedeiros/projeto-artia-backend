package br.com.artia.projeto.server.model.projeto;

import lombok.Data;

@Data
public class ProjetoResponse extends Projeto{

    private Integer percentualAndamento = 0;
    private Boolean atrasado = false;
}
