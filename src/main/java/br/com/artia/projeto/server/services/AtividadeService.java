package br.com.artia.projeto.server.services;

import br.com.artia.projeto.server.entitys.Atividade;
import br.com.artia.projeto.server.entitys.Projeto;
import br.com.artia.projeto.server.mapper.AtividadeMapper;
import br.com.artia.projeto.server.model.atividade.AtividadeRequest;
import br.com.artia.projeto.server.model.atividade.AtividadeResponse;
import br.com.artia.projeto.server.repository.AtividadeRepository;
import br.com.artia.projeto.server.repository.ProjetoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Slf4j
@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    public AtividadeResponse criarAtividade(AtividadeRequest atividadeRequest){
        Atividade atividade = new Atividade();
        atividade.setNome(atividadeRequest.getNome());
        atividade.setDataInicio(atividadeRequest.getDataInicio());
        atividade.setDataFinal(atividadeRequest.getDataFinal());
        atividade.setFinalizada(atividadeRequest.isFinalizada());

        Optional<Projeto> projeto = projetoRepository.findById(atividadeRequest.getIdProjeto());
        atividade.setProjeto(projeto.get());

        Atividade atividadeRetorno = atividadeRepository.save(atividade);

        return AtividadeMapper.atividadeResponseMapper(atividadeRetorno);
    }

    public AtividadeResponse retornarAtividade(Long idAtividade){

        Atividade atividade = atividadeRepository.getById(idAtividade);

        return AtividadeMapper.atividadeResponseMapper(atividade);
    }

    public List<AtividadeResponse> retornarTodasAtividades(){
        List<Atividade> atividadeList = atividadeRepository.findAll();

        List<AtividadeResponse> out = atividadeList.stream()
                .map(AtividadeMapper::atividadeResponseMapper)
                .collect(Collectors.toList());

        return out;
    }

    public void deletarAtividade(Long idAtividade){

        atividadeRepository.deleteById(idAtividade);

    }

    public List<Atividade> retornarAtividadesProjeto(Long idProjeto){
        List<Atividade> atividadeList = atividadeRepository.getAtividadesProjeto(idProjeto);

        return atividadeList;
    }

    public List<AtividadeResponse> retornarAtividadesResponseProjeto(Long idProjeto){
        List<Atividade> atividadeList = atividadeRepository.getAtividadesProjeto(idProjeto);

        List<AtividadeResponse> out = atividadeList.stream()
                .map(AtividadeMapper::atividadeResponseMapper)
                .collect(Collectors.toList());

        return out;
    }

    public AtividadeResponse atualizarAtividade(Long idAtividade, AtividadeRequest atividadeRequest){

        Optional<Atividade> atividade;

        atividade = atividadeRepository.findById(idAtividade)
                .map(record -> {
                    record.setNome(atividadeRequest.getNome());
                    record.setDataInicio(atividadeRequest.getDataInicio());
                    record.setDataFinal(atividadeRequest.getDataFinal());
                    record.setFinalizada(atividadeRequest.isFinalizada());
                    return atividadeRepository.save(record);
                });

        if(nonNull(atividade.get()))
            return AtividadeMapper.atividadeResponseMapper(atividade.get());

        return null;

    }
}
