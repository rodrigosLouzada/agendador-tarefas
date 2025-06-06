package com.projectSpringB.agendador_tarefas.controller;

import com.projectSpringB.agendador_tarefas.business.dto.TarefasDTO;
import com.projectSpringB.agendador_tarefas.business.service.TarefaService;
import com.projectSpringB.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscaListaTarefasPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal ,
                                                                        @RequestHeader("Authorization") String token){

        return ResponseEntity.ok(tarefaService.buscarTarefasAgendadas(dataInicial,dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscarListaTarefasPorEmail(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefaService.buscarTarefasPorEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarPorId(@RequestParam("id") String id){
        tarefaService.deletarPorId(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTO> alterarStatus(@RequestParam("status") StatusNotificacaoEnum statusNotificacaoEnum,
                                                    @RequestParam("id") String id){
        return ResponseEntity.ok().body(tarefaService.alterarStatus(statusNotificacaoEnum, id));
    }

    @PutMapping ResponseEntity<TarefasDTO> alterarTarefa(@RequestBody TarefasDTO tarefasDTO,
                                                         @RequestParam("id") String id){
        return ResponseEntity.ok().body(tarefaService.updateTarefa(id, tarefasDTO));
    }
}
