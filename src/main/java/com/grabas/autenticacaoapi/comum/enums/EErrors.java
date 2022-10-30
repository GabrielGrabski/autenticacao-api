package com.grabas.autenticacaoapi.comum.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EErrors {

    ROLE_NAO_ENCONTRADA("Role não encontrada."),
    ROLE_INATIVA("Role não encontrada."),
    USUARIO_NAO_ENCONTRADO("Usuário não encontrado."),
    USUARIO_INATIVO("Usuário inativo.");

    private final String descricao;
}
