package com.lucasgiavaroti.agendador_tarefas.business;

import com.lucasgiavaroti.agendador_tarefas.business.dto.TarefaDTO;
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

    public TarefaDTO atualizaDadosTarefa(TarefaDTO tarefaDTO, String id){
        try{
            TarefasEntity tarefasEntity = tarefaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao buscar a tarefa com o id: " + id));

            tarefaUpdateConverter.updateTarefas(tarefaDTO, tarefasEntity);

            tarefasEntity.setDataAlteracao(LocalDateTime.now());

            return tarefaConverter.paraTarefaDTO(tarefaRepository.save(tarefasEntity));
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

    public TarefaDTO alterarStatus(StatusNotificacaoEnum status, String id){
        try{
            TarefasEntity tarefasEntity = tarefaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao buscar a tarefa com o id: " + id));

            tarefasEntity.setStatusNotificacao(status);

            tarefaRepository.save(tarefasEntity);

            return  tarefaConverter.paraTarefaDTO(tarefasEntity);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao alterar o status da tarefa", e.getCause());
        }
    }

}
