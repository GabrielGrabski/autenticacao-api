package com.grabas.autenticacaoapi.modulos.usuarios.dto;

import com.grabas.autenticacaoapi.comum.enums.EStatus;
import com.grabas.autenticacaoapi.modulos.roles.dto.RoleSemUsuariosResponse;

import java.util.List;

public record UsuarioResponse(Integer id, String email, String nome,EStatus status, List<RoleSemUsuariosResponse> roles) {
}
