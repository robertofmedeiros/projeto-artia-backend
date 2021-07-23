package br.com.artia.projeto.server.controller;

import br.com.artia.projeto.server.model.projeto.ProjetoRequest;
import br.com.artia.projeto.server.model.projeto.ProjetoResponse;
import br.com.artia.projeto.server.services.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static java.util.Objects.isNull;

@RestController
@RequestMapping({"/projetos"})
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<ProjetoResponse>> listarTodosProjetos(){

        List<ProjetoResponse> projetoResponseList = projetoService.retornarTodosProjetos();

        if(isNull(projetoResponseList)){
            return ResponseEntity.notFound()
                    .build();
        }

        return ResponseEntity.ok()
                .body(projetoResponseList);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<ProjetoResponse> listarProjeto(@PathVariable long id){

        ProjetoResponse projetoResponse = projetoService.retornarProjeto(id);

        if(isNull(projetoResponse)){
            return ResponseEntity.notFound()
                    .build();
        }

        return ResponseEntity.ok().body(projetoResponse);
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<ProjetoResponse> criarProjeto(@RequestBody ProjetoRequest projetoRequest){

        ProjetoResponse projetoResponse = projetoService.criarProjeto(projetoRequest);

        return ResponseEntity.ok()
                .body(projetoResponse);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(value="/{id}")
    public ResponseEntity<ProjetoResponse> atualizarProjeto(@PathVariable long id,
                                                            @RequestBody ProjetoRequest projetoRequest){

        ProjetoResponse projetoResponse = projetoService.atualizarProjeto(id, projetoRequest);

        return ResponseEntity.ok()
                .body(projetoResponse);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<Void> deletarProjeto(@PathVariable long id){

        projetoService.deletarProjeto(id);

        return ResponseEntity.ok()
                .build();
    }
}
