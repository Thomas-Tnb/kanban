package com.example.kanban.service;

import com.example.kanban.model.Tarefa;
import com.example.kanban.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    public List<List<Tarefa>> findAll(){
        List<List<Tarefa>> lista = new ArrayList<>();
        lista.add(tarefaRepository.findByStatus(0));
        lista.add(tarefaRepository.findByStatus(1));
        lista.add(tarefaRepository.findByStatus(2));
        return lista;
    }

    public List<Tarefa> getTarefasByPrioridade(String prioridade) {
        return tarefaRepository.findByPrioridade(prioridade);
    }

    public List<Tarefa> getTarefasByDataLimite(Date dataFinal) {
        return tarefaRepository.findByDataFinal(dataFinal);
    }

    public List<List<Tarefa>> getRelatorio() {
        List<List<Tarefa>> listaTarefasPorColuna = new ArrayList<>();

        List<Tarefa> tarefasAtrasadas = tarefaRepository.findTarefasAtrasadas();

        List<Tarefa> tarefasAFazer = tarefasAtrasadas.stream()
                .filter(tarefa -> (tarefa.getStatus() == 0))

                .collect(Collectors.toList());

        List<Tarefa> tarefasEmProgresso = tarefasAtrasadas.stream()
                .filter(tarefa -> (tarefa.getStatus() == 1))
                .collect(Collectors.toList());

        listaTarefasPorColuna.add(tarefasAFazer);
        listaTarefasPorColuna.add(tarefasEmProgresso);

        return listaTarefasPorColuna;
    }


    public Tarefa findById(int id){
        return tarefaRepository.findById(id).orElse(null);
    }

    public Tarefa save(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public void delete(int id){
        tarefaRepository.deleteById(id);
    }

    public Tarefa mudarStatus(int id) {
        Tarefa tarefa = this.findById(id);
        if(tarefa.getStatus() < 2){
            tarefa.setStatus(tarefa.getStatus()+1);
            tarefaRepository.save(tarefa);
        }
        return tarefa;
    }


    public Tarefa editarTarefa(int id, Tarefa tarefa) {
        Tarefa tarefaAnt = this.findById(id);
        tarefaAnt.setTitulo(tarefa.getTitulo());
        tarefaAnt.setDescricao(tarefa.getDescricao());
        tarefaAnt.setPrioridade(tarefa.getPrioridade());
        tarefaAnt.setDataLimite(tarefa.getDataLimite());
        tarefaRepository.save(tarefaAnt);
        return tarefaAnt;
    }

    public void deletarTarefa(int id) {
        tarefaRepository.deleteById(id);
    }
}
