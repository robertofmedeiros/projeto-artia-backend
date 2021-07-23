package br.com.artia.projeto.server.repository;

import br.com.artia.projeto.server.entitys.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

    @Query("select a from atividade a where a.projeto.id = :idProjeto")
    public List<Atividade> getAtividadesProjeto(@Param("idProjeto") Long idProjeto);
}
