package com.lucasgiavaroti.agendador_tarefas.infrastructure.repository;

import com.lucasgiavaroti.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends MongoRepository<TarefasEntity, String> {
}
