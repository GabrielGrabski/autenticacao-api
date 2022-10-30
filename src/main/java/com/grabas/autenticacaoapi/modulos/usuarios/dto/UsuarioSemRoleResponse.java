package com.grabas.autenticacaoapi.modulos.usuarios.dto;

import com.grabas.autenticacaoapi.comum.enums.EStatus;

public record UsuarioSemRoleResponse(String email, EStatus status, String nome) {
}
