package com.lucasgiavaroti.agendador_tarefas.infrastructure.security;

import com.lucasgiavaroti.agendador_tarefas.business.dto.UsuarioDTORecord;
import com.lucasgiavaroti.agendador_tarefas.infrastructure.security.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UsuarioClient usuarioClient;

    public UserDetails loadUser(String email, String token){
        UsuarioDTORecord usuarioDTO = usuarioClient.buscaUsuarioPorEmail(email, token);

        return User
                .withUsername(usuarioDTO.email()) // Define o nome de usuário como o e-mail
                .password(usuarioDTO.senha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails

    }

}
