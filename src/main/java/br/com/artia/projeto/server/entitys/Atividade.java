package br.com.artia.projeto.server.entitys;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "atividade")
public class Atividade {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private Date dataInicio;
    @Column(nullable = false)
    private Date dataFinal;
    private boolean finalizada;

    @ManyToOne
    @JoinColumn(name="idProjeto", nullable=false)
    private Projeto projeto;
}
