package com.grabas.autenticacaoapi.modulos.roles.dto;

import com.grabas.autenticacaoapi.comum.enums.EStatus;

import javax.validation.constraints.NotNull;

public record RoleRequest(@NotNull String codigo, @NotNull EStatus status, @NotNull String descricao) {
}
