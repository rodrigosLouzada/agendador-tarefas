package com.projectSpringB.agendador_tarefas.business.mapper;

import com.projectSpringB.agendador_tarefas.business.dto.TarefasDTO;
import com.projectSpringB.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    TarefasEntity paraTarefaEntity(TarefasDTO tarefasDTO);

    TarefasDTO paraTarefaDTO(TarefasEntity tarefasEntity);
}
