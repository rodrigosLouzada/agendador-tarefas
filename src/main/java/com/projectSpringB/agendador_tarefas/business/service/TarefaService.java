package com.projectSpringB.agendador_tarefas.business.service;

import com.projectSpringB.agendador_tarefas.business.dto.TarefasDTO;
import com.projectSpringB.agendador_tarefas.business.mapper.TarefaConverter;
import com.projectSpringB.agendador_tarefas.infrastructure.entity.TarefasEntity;
import com.projectSpringB.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.projectSpringB.agendador_tarefas.infrastructure.exceptions.ResourceNotFound;
import com.projectSpringB.agendador_tarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.projectSpringB.agendador_tarefas.infrastructure.repository.TarefasRepository;
import com.projectSpringB.agendador_tarefas.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefasRepository tarefasRepository;
    private final TarefaConverter tarefaConverter;
    private  final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefa(String token,TarefasDTO tarefasDTO){

        String email = jwtUtil.extractUsername(token.substring(7));
        tarefasDTO.setEmailUsuario(email);
        tarefasDTO.setDataCriacao(LocalDateTime.now());
        tarefasDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        return tarefaConverter.paraTarefaDTO(tarefasRepository.save
                (tarefaConverter.paraTarefaEntity(tarefasDTO)));
    }


    public List<TarefasDTO> buscarTarefasAgendadas(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return tarefaConverter.paraListaTarefasEntity(tarefasRepository.findByDataEventoBetween(dataInicial,dataFinal));
    }

    public List<TarefasDTO> buscarTarefasPorEmail(String token){
        String email = jwtUtil.extractUsername(token.substring(7));
        return tarefaConverter.paraListaTarefasEntity(tarefasRepository.findByEmailUsuario(email));
    }

    public void deletarPorId(String id){
        try{
            tarefasRepository.deleteById(id);

        } catch (ResourceNotFoundException e) {
            throw new RuntimeException("id não encontrado" + id, e.getCause());
        }
    }

    public TarefasDTO alterarStatus(StatusNotificacaoEnum statusNotificacaoEnum, String id){

        TarefasEntity tarefa = tarefasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id não encontrado"));
        tarefa.setStatusNotificacaoEnum(statusNotificacaoEnum);

        return tarefaConverter.paraTarefaDTO(tarefasRepository.save(tarefa));
    }
}
