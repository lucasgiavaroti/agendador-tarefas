package com.lucasgiavaroti.agendador_tarefas.business.mapper;

import com.lucasgiavaroti.agendador_tarefas.business.dto.TarefaDTO;
import com.lucasgiavaroti.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateTarefas(TarefaDTO tarefaDTO, @MappingTarget TarefasEntity tarefasEntity);

}
