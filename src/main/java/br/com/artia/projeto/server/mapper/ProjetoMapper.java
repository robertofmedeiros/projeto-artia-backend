package br.com.artia.projeto.server.mapper;

import br.com.artia.projeto.server.entitys.Projeto;
import br.com.artia.projeto.server.model.projeto.ProjetoResponse;
import br.com.artia.projeto.server.services.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjetoMapper {

    @Autowired
    private static AtividadeService atividadeService;

    public static ProjetoResponse projetoResponseMapper(Projeto projeto){
        ProjetoResponse projetoResponse = new ProjetoResponse();

        projetoResponse.setNome(projeto.getNome());
        projetoResponse.setDataFinal(projeto.getDataFinal());
        projetoResponse.setDataInicio(projeto.getDataInicio());
        projetoResponse.setId(projeto.getId());

        return projetoResponse;
    }
}
