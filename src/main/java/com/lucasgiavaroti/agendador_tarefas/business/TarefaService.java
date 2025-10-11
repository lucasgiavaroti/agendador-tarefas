package com.lucasgiavaroti.agendador_tarefas.business;

import com.lucasgiavaroti.agendador_tarefas.business.dto.TarefaDTORecord;
import com.lucasgiavaroti.agendador_tarefas.business.mapper.TarefaConverter;
import com.lucasgiavaroti.agendador_tarefas.business.mapper.TarefaUpdateConverter;
import com.lucasgiavaroti.agendador_tarefas.infrastructure.entity.TarefasEntity;
import com.lucasgiavaroti.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.lucasgiavaroti.agendador_tarefas.infrastructure.exceptions.ResourceNotFoundException;
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
    private final TarefaUpdateConverter tarefaUpdateConverter;
    private final JwtUtil jwtUtil;

    public TarefaDTORecord salvarTarefa(TarefaDTORecord tarefaDTO, String token){

        String email = jwtUtil.extractEmail(token.substring(7));

        TarefaDTORecord dtoFinal = new TarefaDTORecord(null, tarefaDTO.nomeTarefa(), tarefaDTO.descricao(), LocalDateTime.now(), tarefaDTO.dataAgendamento(), email, null, StatusNotificacaoEnum.PENDENTE);

        TarefasEntity tarefasEntitySaved = tarefaRepository.save(tarefaConverter.paraTarefaEntity(dtoFinal));

        return tarefaConverter.paraTarefaDTORecord(tarefasEntitySaved);

    }

    public List<TarefaDTORecord> buscaTarefasAgendadasPorPeriodo (LocalDateTime dataInicio, LocalDateTime dataFim){
        return tarefaConverter.paraListaTarefaDTORecord(tarefaRepository.findByDataAgendamentoBetweenAndStatusNotificacao(dataInicio, dataFim, StatusNotificacaoEnum.PENDENTE));
    }

    public List<TarefaDTORecord> buscaTarefasPorEmailUsuario(String token){

        String email = jwtUtil.extractEmail(token.substring(7));

        return tarefaConverter.paraListaTarefaDTORecord(tarefaRepository.findByEmailUsuario(email));

    }

    public TarefaDTORecord atualizaDadosTarefa(TarefaDTORecord tarefaDTO, String id){
        try{
            TarefasEntity tarefasEntity = tarefaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao buscar a tarefa com o id: " + id));

            tarefaUpdateConverter.updateTarefas(tarefaDTO, tarefasEntity);

            tarefasEntity.setDataAlteracao(LocalDateTime.now());

            return tarefaConverter.paraTarefaDTORecord(tarefaRepository.save(tarefasEntity));
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao atualizar a tarefa", e.getCause());
        }
    }

    public void deletarTarefa(String id){
        try{
            tarefaRepository.deleteById(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao deletar a tarefa com o id: " + id, e.getCause());
        }
    }

    public TarefaDTORecord alterarStatus(StatusNotificacaoEnum status, String id){
        try{
            TarefasEntity tarefasEntity = tarefaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao buscar a tarefa com o id: " + id));

            tarefasEntity.setStatusNotificacao(status);

            tarefaRepository.save(tarefasEntity);

            return  tarefaConverter.paraTarefaDTORecord(tarefasEntity);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao alterar o status da tarefa", e.getCause());
        }
    }

}
