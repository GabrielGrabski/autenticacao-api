package com.grabas.autenticacaoapi.modulos.roles.dto;

import com.grabas.autenticacaoapi.comum.enums.EStatus;
import com.grabas.autenticacaoapi.modulos.usuarios.model.Usuario;

import java.util.List;

public record RoleResponse(Integer id, String nome, String codigo, String descricao, EStatus status, List<Usuario> usuarios) {
}
