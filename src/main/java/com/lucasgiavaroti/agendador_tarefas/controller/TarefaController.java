package com.lucasgiavaroti.agendador_tarefas.controller;

import com.lucasgiavaroti.agendador_tarefas.business.TarefaService;
import com.lucasgiavaroti.agendador_tarefas.business.dto.TarefaDTORecord;
import com.lucasgiavaroti.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTORecord> salvarTarefa(@RequestBody TarefaDTORecord tarefaDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.salvarTarefa(tarefaDTO, token));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTORecord>> buscaTarefasAgendadasPorPeriodo(
            @RequestParam("data_inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam("data_fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim){
        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicio, dataFim));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTORecord>> buscaTarefasPorEmailUsuario(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefaService.buscaTarefasPorEmailUsuario(token));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTORecord> atualizaDadosTarefa(@RequestBody TarefaDTORecord tarefaDTO, @PathVariable String id){
        return  ResponseEntity.ok(tarefaService.atualizaDadosTarefa(tarefaDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable String id){
        tarefaService.deletarTarefa(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TarefaDTORecord> alterarStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status, @PathVariable String id){
        return ResponseEntity.ok(tarefaService.alterarStatus(status, id));
    }

}
