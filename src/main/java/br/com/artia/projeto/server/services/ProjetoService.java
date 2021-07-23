package br.com.artia.projeto.server.services;

import br.com.artia.projeto.server.entitys.Atividade;
import br.com.artia.projeto.server.entitys.Projeto;
import br.com.artia.projeto.server.mapper.ProjetoMapper;
import br.com.artia.projeto.server.model.projeto.ProjetoRequest;
import br.com.artia.projeto.server.model.projeto.ProjetoResponse;
import br.com.artia.projeto.server.repository.ProjetoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Slf4j
@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private AtividadeService atividadeService;

    public ProjetoResponse criarProjeto(ProjetoRequest projetoRequest){
        Projeto projeto = new Projeto();

        projeto.setNome(projetoRequest.getNome());
        projeto.setDataFinal(projetoRequest.getDataFinal());
        projeto.setDataInicio(projetoRequest.getDataInicio());

        Projeto projetoRetorno = projetoRepository.save(projeto);

        return ProjetoMapper.projetoResponseMapper(projetoRetorno);
    }

    public ProjetoResponse retornarProjeto(Long idProjeto){
        Projeto projeto = projetoRepository.getById(idProjeto);

        return retornarProjetoResponse(projeto);
    }

    public void deletarProjeto(Long idProjeto){
        projetoRepository.deleteById(idProjeto);
    }

    public ProjetoResponse atualizarProjeto(Long idProjeto, ProjetoRequest projetoRequest){

        Optional<Projeto> projeto;

        projeto = projetoRepository.findById(idProjeto)
                .map(record -> {
                    record.setNome(projetoRequest.getNome());
                    record.setDataInicio(projetoRequest.getDataInicio());
                    record.setDataFinal(projetoRequest.getDataFinal());
                    return projetoRepository.save(record);
                });

        if(nonNull(projeto.get()))
            return ProjetoMapper.projetoResponseMapper(projeto.get());

        return null;
    }

    public List<ProjetoResponse> retornarTodosProjetos(){

        List<Projeto> projetoList = projetoRepository.findAll();

        List<ProjetoResponse> out = new ArrayList<>();

        for(Projeto projeto : projetoList){
            out.add(retornarProjetoResponse(projeto));
        }

        return out;
    }

    private ProjetoResponse retornarProjetoResponse(Projeto projeto){

        Double quantidadeFinalizada = 0.0;
        Double quantidadeTotal = 0.0;
        Double percentualFinalizada = 0.0;
        Boolean projetoAtrasado = false;

        ProjetoResponse out = ProjetoMapper.projetoResponseMapper(projeto);

        try {
            List<Atividade> atividadeList = atividadeService.retornarAtividadesProjeto(projeto.getId());

            for (Atividade atividade : atividadeList) {
                if (atividade.isFinalizada())
                    quantidadeFinalizada++;

                if(atividade.getDataFinal().before(projeto.getDataFinal()) &&
                        projetoAtrasado == false)
                    projetoAtrasado = true;

                quantidadeTotal++;
            }

            if(quantidadeTotal > 0)
                percentualFinalizada = Double.valueOf((quantidadeFinalizada / quantidadeTotal) * 100);

            out.setPercentualAndamento(percentualFinalizada.intValue());
            out.setAtrasado(projetoAtrasado);
        } catch (Exception e){
            e.printStackTrace();
        }

        return out;
    }
}
