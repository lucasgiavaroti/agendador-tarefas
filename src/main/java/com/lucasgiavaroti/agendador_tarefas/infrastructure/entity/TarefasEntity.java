package com.lucasgiavaroti.agendador_tarefas.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasgiavaroti.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("tarefa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefasEntity {

    @Id
    private String id;

    private String nomeTarefa;

    private String descricao;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAgendamento;

    private String emailUsuario;

    private LocalDateTime dataAlteracao;

    private StatusNotificacaoEnum statusNotificacao;

}
