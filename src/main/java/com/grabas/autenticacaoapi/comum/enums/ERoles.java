package com.grabas.autenticacaoapi.comum.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ERoles {

    ADMIN("ADMIN"),
    GESTOR_002("GESTOR_002");

    private final String codigo;

    public String getCodigoComRole() {
        return "ROLE_" + codigo;
    }
}
