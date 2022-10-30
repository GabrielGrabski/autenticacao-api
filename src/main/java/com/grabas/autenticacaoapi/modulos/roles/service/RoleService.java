package com.grabas.autenticacaoapi.modulos.roles.service;

import com.grabas.autenticacaoapi.comum.enums.EErrors;
import com.grabas.autenticacaoapi.comum.enums.EStatus;
import com.grabas.autenticacaoapi.comum.exception.model.ValidacaoException;
import com.grabas.autenticacaoapi.modulos.roles.dto.RoleRequest;
import com.grabas.autenticacaoapi.modulos.roles.dto.RoleSemUsuariosResponse;
import com.grabas.autenticacaoapi.modulos.roles.model.Role;
import com.grabas.autenticacaoapi.modulos.roles.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public Page<RoleSemUsuariosResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(role -> new RoleSemUsuariosResponse(role.getId(),
                        role.getCodigo(),
                        role.getStatus(),
                        role.getDescricao()));
    }

    public RoleSemUsuariosResponse alterarSituacaoById(Integer id) {
        var role = repository.findById(id).orElseThrow(() -> new ValidacaoException(EErrors.ROLE_NAO_ENCONTRADA.getDescricao()));

        if (role.getStatus().equals(EStatus.A)) {
            role.setStatus(EStatus.I);
        } else {
            role.setStatus(EStatus.A);
        }

        return new RoleSemUsuariosResponse(role.getId(), role.getCodigo(), role.getStatus(), role.getDescricao());
    }

    public RoleSemUsuariosResponse save(RoleRequest request) {
        var role = Role.of(request);
        repository.save(setAtivoSeRoleNova(role));
        return new RoleSemUsuariosResponse(role.getId(), role.getCodigo(), role.getStatus(), role.getDescricao());
    }

    private Role setAtivoSeRoleNova(Role role) {
        if (role.getId() == null) {
            role.setStatus(EStatus.A);
        }
        return role;
    }
}
