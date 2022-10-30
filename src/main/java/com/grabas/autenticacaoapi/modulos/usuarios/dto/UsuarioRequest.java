package com.grabas.autenticacaoapi.modulos.usuarios.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public record UsuarioRequest(@NotNull String email, @NotNull String senha, @NotNull String nome, @NotNull List<Integer> rolesIds) {
}
