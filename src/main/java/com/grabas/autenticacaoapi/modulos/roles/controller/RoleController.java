package com.grabas.autenticacaoapi.modulos.roles.controller;

import com.grabas.autenticacaoapi.modulos.roles.dto.RoleRequest;
import com.grabas.autenticacaoapi.modulos.roles.dto.RoleSemUsuariosResponse;
import com.grabas.autenticacaoapi.modulos.roles.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/roles")
public class RoleController {

    @Autowired
    private RoleService service;

    @GetMapping
    public Page<RoleSemUsuariosResponse> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @PostMapping
    public RoleSemUsuariosResponse save(@RequestBody RoleRequest request) {
        return service.save(request);
    }

    @PutMapping("{id}/alterar-situacao")
    public RoleSemUsuariosResponse alterarSituacaoById(@PathVariable Integer id) {
        return service.alterarSituacaoById(id);
    }
}
