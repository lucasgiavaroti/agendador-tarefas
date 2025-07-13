package com.lucasgiavaroti.agendador_tarefas.business.mapper;

import com.lucasgiavaroti.agendador_tarefas.business.dto.TarefaDTO;
import com.lucasgiavaroti.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    TarefasEntity paraTarefaEntity(TarefaDTO tarefaDTO);

    TarefaDTO paraTarefaDTO(TarefasEntity tarefasEntity);

}
