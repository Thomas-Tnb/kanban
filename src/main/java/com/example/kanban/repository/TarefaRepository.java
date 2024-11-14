package com.example.kanban.repository;

import com.example.kanban.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
    List<Tarefa> findByStatusOrderByPrioridadeAsc(String status);
    List<Tarefa> findByPrioridade(String prioridade);
    List<Tarefa> findByDataFinal(Date dataFinal);


    @Query("SELECT t FROM Tarefa t WHERE t.status IN (0, 1) AND t.dataFinal < CURRENT_DATE")
    List<Tarefa> findTarefasAtrasadas();

    List<Tarefa> findByStatus(int status);
}

