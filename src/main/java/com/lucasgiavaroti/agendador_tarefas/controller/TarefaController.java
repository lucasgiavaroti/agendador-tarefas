package com.lucasgiavaroti.agendador_tarefas.controller;

import com.lucasgiavaroti.agendador_tarefas.business.TarefaService;
import com.lucasgiavaroti.agendador_tarefas.business.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefa")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> salvarTarefa(@RequestBody TarefaDTO tarefaDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.salvarTarefa(tarefaDTO, token));
    }

}
