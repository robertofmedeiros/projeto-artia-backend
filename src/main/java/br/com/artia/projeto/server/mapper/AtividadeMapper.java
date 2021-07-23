package br.com.artia.projeto.server.mapper;

import br.com.artia.projeto.server.entitys.Atividade;
import br.com.artia.projeto.server.model.atividade.AtividadeResponse;

public class AtividadeMapper {
    public static AtividadeResponse atividadeResponseMapper(Atividade atividade){
        AtividadeResponse out = new AtividadeResponse();

        out.setId(atividade.getId());
        out.setNome(atividade.getNome());
        out.setDataInicio(atividade.getDataInicio());
        out.setDataFinal(atividade.getDataFinal());
        out.setIdProjeto(atividade.getProjeto().getId());
        out.setFinalizada(atividade.isFinalizada());

        return out;
    }
}
