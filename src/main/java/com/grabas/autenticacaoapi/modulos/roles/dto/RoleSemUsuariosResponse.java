package com.grabas.autenticacaoapi.modulos.roles.dto;

import com.grabas.autenticacaoapi.comum.enums.EStatus;

public record RoleSemUsuariosResponse(Integer id, String codigo, EStatus status, String descricao) {
}
