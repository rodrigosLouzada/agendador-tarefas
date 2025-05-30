package com.projectSpringB.agendador_tarefas.security;

import com.projectSpringB.agendador_tarefas.business.dto.UsuarioDTO;
import com.projectSpringB.agendador_tarefas.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl{

    // Repositório para acessar dados de usuário no banco de dados
    @Autowired
    private UsuarioClient usuarioClient;


    public UserDetails carregaDadosUsuario(String email, String token){
        UsuarioDTO usuarioDTO = usuarioClient.buscarUsuarioPorEmail(email, token);
        return User
                .withUsername(usuarioDTO.getEmail())
                .password(usuarioDTO.getSenha())
                .build();
    }
}
