package com.lucasgiavaroti.agendador_tarefas.infrastructure.repository;

import com.lucasgiavaroti.agendador_tarefas.infrastructure.entity.TarefasEntity;
import com.lucasgiavaroti.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefaRepository extends MongoRepository<TarefasEntity, String> {

    List<TarefasEntity> findByDataAgendamentoBetweenAndStatusNotificacao(LocalDateTime dataAgendamentoAfter, LocalDateTime dataAgendamentoBefore, StatusNotificacaoEnum statusNotificacao);
    List<TarefasEntity> findByEmailUsuario(String emailUsuario);
}
