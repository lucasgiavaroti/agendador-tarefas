package com.lucasgiavaroti.agendador_tarefas.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasgiavaroti.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;

import java.time.LocalDateTime;

public record TarefaDTORecord(
        String id,
        String nomeTarefa,
        String descricao,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime dataCriacao,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime dataAgendamento,
        String emailUsuario,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime dataAlteracao,
        StatusNotificacaoEnum statusNotificacao
        ) {
}
