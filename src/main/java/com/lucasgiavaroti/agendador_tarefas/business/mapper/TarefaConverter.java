package com.lucasgiavaroti.agendador_tarefas.business.mapper;

import com.lucasgiavaroti.agendador_tarefas.business.dto.TarefaDTORecord;
import com.lucasgiavaroti.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    TarefasEntity paraTarefaEntity(TarefaDTORecord tarefaDTO);

    TarefaDTORecord paraTarefaDTORecord(TarefasEntity tarefasEntity);

    List<TarefaDTORecord> paraListaTarefaDTORecord(List<TarefasEntity> tarefasEntityList);

}
