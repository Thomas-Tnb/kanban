package com.example.kanban.controller;

import com.example.kanban.model.Tarefa;
import com.example.kanban.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<List<Tarefa>> getAllProducts(){
        return tarefaService.findAll();
    }

    @PostMapping
    public Tarefa createTarefa(@RequestBody Tarefa tarefa){
        return tarefaService.save(tarefa);
    }

    @GetMapping("/prioridade/{prioridade}") //filtro por prioridade
    private List<Tarefa> getTarefasByPrioridade(@PathVariable String prioridade){
        return tarefaService.getTarefasByPrioridade(prioridade);
    }

    @GetMapping("/dataLimite/{dataLimite}") //filtro por dataFinal
    private List<Tarefa> getTarefasByDataLimite(@PathVariable Date dataFinal){
        return tarefaService.getTarefasByDataLimite(dataFinal);
    }

    @GetMapping("/relatorio")
    private List<List<Tarefa>> getRelatorio(){
        return tarefaService.getRelatorio();
    }

    @PatchMapping("/{id}")
    public Tarefa mudarStatus(@PathVariable int id){
        return tarefaService.mudarStatus(id);
    }

    @PatchMapping("/edit/{id}")
    public Tarefa editarTarefa(@PathVariable int id, @RequestBody Tarefa tarefa){
        return tarefaService.editarTarefa(id, tarefa);
    }

    @DeleteMapping("/{id}")
    public void excluirTarefa(@PathVariable int id){
        tarefaService.deletarTarefa(id);
    }
}
