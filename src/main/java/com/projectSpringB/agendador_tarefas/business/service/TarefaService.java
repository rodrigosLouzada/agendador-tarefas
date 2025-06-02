package com.projectSpringB.agendador_tarefas.business.service;

import com.projectSpringB.agendador_tarefas.infrastructure.repository.TarefasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private TarefasRepository tarefasRepository;

}
