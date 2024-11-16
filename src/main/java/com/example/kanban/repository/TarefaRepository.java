package com.example.kanban.repository;

import com.example.kanban.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
    List<Tarefa> findByPrioridade(int prioridade);
    List<Tarefa> findByDataLimite(LocalDate dataLimite);

    @Query(value = "SELECT * FROM tarefa t WHERE t.status IN (0, 1) AND t.dataLimite < CURRENT_DATE", nativeQuery = true)
    List<Tarefa> findTarefasAtrasadas();

//    @Query("SELECT t FROM Tarefa t WHERE t.status IN (0, 1) AND t.dataLimite < CURRENT_DATE")
//    List<Tarefa> findTarefasAtrasadas();

    List<Tarefa> findByStatus(int status);
}

