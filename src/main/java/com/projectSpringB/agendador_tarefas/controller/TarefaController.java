package com.projectSpringB.agendador_tarefas.controller;

import com.projectSpringB.agendador_tarefas.business.dto.TarefasDTO;
import com.projectSpringB.agendador_tarefas.business.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefasDTO> gravarTarefas(@RequestBody TarefasDTO tarefasDTO,
                                                    @RequestHeader("Authorization") String token){

        return ResponseEntity.ok(tarefaService.gravarTarefa(token,tarefasDTO));
    }
}
