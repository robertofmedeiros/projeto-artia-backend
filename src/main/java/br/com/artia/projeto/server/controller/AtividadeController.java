package br.com.artia.projeto.server.controller;

import br.com.artia.projeto.server.model.atividade.AtividadeRequest;
import br.com.artia.projeto.server.model.atividade.AtividadeResponse;
import br.com.artia.projeto.server.services.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.isNull;

@RestController
@RequestMapping({"/atividades"})
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<AtividadeResponse>> listarTodasAtividades(){

        List<AtividadeResponse> atividadeResponseList = new ArrayList<>();

        try {
            atividadeResponseList = atividadeService.retornarTodasAtividades();
        } catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(atividadeResponseList);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = {"/projeto/{id}"})
    public ResponseEntity<List<AtividadeResponse>> listarTodasAtividadesProjeto(@PathVariable long id){

        List<AtividadeResponse> atividadeResponseList = new ArrayList<>();

        try {
            atividadeResponseList = atividadeService.retornarAtividadesResponseProjeto(id);
        } catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(atividadeResponseList);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<AtividadeResponse> listarAtividade(@PathVariable long id){

        AtividadeResponse atividadeResponse = new AtividadeResponse();

        try {
            atividadeResponse = atividadeService.retornarAtividade(id);
        } catch (Exception e){
            e.printStackTrace();
        }

        if(isNull(atividadeResponse)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(atividadeResponse);
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<AtividadeResponse> criarAtividade(@RequestBody AtividadeRequest atividadeRequest){

        AtividadeResponse atividadeResponse = atividadeService.criarAtividade(atividadeRequest);

        return ResponseEntity.ok().body(atividadeResponse);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(value="/{id}")
    public ResponseEntity<AtividadeResponse> atualizarAtualizar(@PathVariable long id,
                                                            @RequestBody AtividadeRequest atividadeRequest){

        AtividadeResponse atividadeResponse = atividadeService.atualizarAtividade(id, atividadeRequest);

        return ResponseEntity.ok().body(atividadeResponse);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<Void> deletarAtividade(@PathVariable long id){

        atividadeService.deletarAtividade(id);

        return ResponseEntity.ok().build();
    }
}
