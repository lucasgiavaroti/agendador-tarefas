package com.lucasgiavaroti.agendador_tarefas.business;

import com.lucasgiavaroti.agendador_tarefas.business.dto.TarefaDTO;
import com.lucasgiavaroti.agendador_tarefas.business.mapper.TarefaConverter;
import com.lucasgiavaroti.agendador_tarefas.infrastructure.entity.TarefasEntity;
import com.lucasgiavaroti.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.lucasgiavaroti.agendador_tarefas.infrastructure.repository.TarefaRepository;
import com.lucasgiavaroti.agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository  tarefaRepository;
    private final TarefaConverter   tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefaDTO salvarTarefa(TarefaDTO tarefaDTO, String token){

        String email = jwtUtil.extractEmail(token.substring(7));

        tarefaDTO.setDataCriacao(LocalDateTime.now());
        tarefaDTO.setStatusNotificacao(StatusNotificacaoEnum.PENDENTE);
        tarefaDTO.setEmailUsuario(email);

        TarefasEntity tarefasEntitySaved = tarefaRepository.save(tarefaConverter.paraTarefaEntity(tarefaDTO));

        return tarefaConverter.paraTarefaDTO(tarefasEntitySaved);

    }

    public List<TarefaDTO> buscaTarefasAgendadasPorPeriodo (LocalDateTime dataInicio, LocalDateTime dataFim){
        return tarefaConverter.paraListaTarefaDTO(tarefaRepository.findByDataAgendamentoBetween(dataInicio, dataFim));
    }

    public List<TarefaDTO> buscaTarefasPorEmailUsuario(String token){

        String email = jwtUtil.extractEmail(token.substring(7));

        return tarefaConverter.paraListaTarefaDTO(tarefaRepository.findByEmailUsuario(email));

    }

}
