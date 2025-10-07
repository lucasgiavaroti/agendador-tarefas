package com.lucasgiavaroti.agendador_tarefas.infrastructure.exceptions;

import javax.security.sasl.AuthenticationException;

public class UnauthorizedException extends AuthenticationException {
    public UnauthorizedException(String mensagem){
        super(mensagem);
    }
    public UnauthorizedException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
