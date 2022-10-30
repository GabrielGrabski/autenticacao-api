package com.grabas.autenticacaoapi.modulos.usuarios.controller;

import com.grabas.autenticacaoapi.modulos.usuarios.dto.UsuarioRequest;
import com.grabas.autenticacaoapi.modulos.usuarios.dto.UsuarioResponse;
import com.grabas.autenticacaoapi.modulos.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("principal")
    public Principal getPrincipal(Principal user) {
        return user;
    }

    @GetMapping
    public Page<UsuarioResponse> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("{id}")
    public UsuarioResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public UsuarioResponse save(@RequestBody UsuarioRequest request) {
        return service.save(request);
    }

    @PutMapping("{id}/alterar-situacao")
    public UsuarioResponse alterarSituacaoById(Integer id) {
        return service.alterarSituacaoById(id);
    }
}
