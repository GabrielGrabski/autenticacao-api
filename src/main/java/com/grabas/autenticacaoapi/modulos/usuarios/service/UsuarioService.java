package com.grabas.autenticacaoapi.modulos.usuarios.service;

import com.grabas.autenticacaoapi.comum.enums.EStatus;
import com.grabas.autenticacaoapi.comum.exception.model.ValidacaoException;
import com.grabas.autenticacaoapi.modulos.roles.dto.RoleSemUsuariosResponse;
import com.grabas.autenticacaoapi.modulos.roles.model.Role;
import com.grabas.autenticacaoapi.modulos.roles.repository.RoleRepository;
import com.grabas.autenticacaoapi.modulos.usuarios.dto.UsuarioRequest;
import com.grabas.autenticacaoapi.modulos.usuarios.dto.UsuarioResponse;
import com.grabas.autenticacaoapi.modulos.usuarios.model.Usuario;
import com.grabas.autenticacaoapi.modulos.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.grabas.autenticacaoapi.comum.enums.EErrors.*;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private RoleRepository roleRepository;

    public UsuarioResponse save(UsuarioRequest request) {
        var roles = roleRepository.findAllByIdInAndStatus(request.rolesIds(), EStatus.A);
        validarRoles(roles);
        var usuario = Usuario.of(request, roles);

        repository.save(setAtivoSeNovoUsuario(usuario));
        saveRoles(usuario.getRoles(), usuario);

        return new UsuarioResponse(usuario.getId(),
                usuario.getEmail(),
                usuario.getNome(),
                usuario.getStatus(),
                getRolesSemUsuarios(usuario.getRoles()));
    }

    private void saveRoles(List<Role> roles, Usuario usuario) {
        roles.forEach(role -> {
            role.getUsuarios().add(usuario);
            roleRepository.save(role);
        });
    }

    private Usuario setAtivoSeNovoUsuario(Usuario usuario) {
        if (usuario.getId() == null) {
            usuario.setStatus(EStatus.A);
        }
        return usuario;
    }

    public UsuarioResponse alterarSituacaoById(Integer id) {
        var usuario = repository.findById(id).orElseThrow(() -> new ValidacaoException(USUARIO_NAO_ENCONTRADO.getDescricao()));

        if (usuario.getStatus().equals(EStatus.A)) {
            usuario.setStatus(EStatus.I);
        } else {
            usuario.setStatus(EStatus.A);
        }

        return new UsuarioResponse(usuario.getId(),
                usuario.getEmail(),
                usuario.getNome(),
                usuario.getStatus(),
                getRolesSemUsuarios(usuario.getRoles()));
    }

    public UsuarioResponse findById(Integer id) {
        var usuario = repository.findById(id).orElseThrow(() -> new RuntimeException(USUARIO_NAO_ENCONTRADO.getDescricao()));
        return new UsuarioResponse(usuario.getId(),
                usuario.getEmail(),
                usuario.getNome(),
                usuario.getStatus(),
                getRolesSemUsuarios(usuario.getRoles()));
    }

    public Page<UsuarioResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(usuario -> new UsuarioResponse(
                        usuario.getId(),
                        usuario.getEmail(),
                        usuario.getNome(),
                        usuario.getStatus(),
                        getRolesSemUsuarios(usuario.getRoles())
                ));
    }

    private List<RoleSemUsuariosResponse> getRolesSemUsuarios(List<Role> roles) {
        return roles.stream()
                .map(role -> new RoleSemUsuariosResponse(role.getId(), role.getCodigo(), role.getStatus(), role.getDescricao()))
                .collect(Collectors.toList());
    }

    private void validarRoles(List<Role> roles) {
        validarRolesVazias(roles);
        validarRolesInativas(roles);
    }

    private void validarRolesVazias(List<Role> roles) {
        if (roles.isEmpty()) {
            throw new ValidacaoException(ROLE_NAO_ENCONTRADA.getDescricao());
        }
    }

    private void validarRolesInativas(List<Role> roles) {
        roles.forEach(role -> {
            if (role.getStatus().equals(EStatus.I)) {
                throw new ValidacaoException(ROLE_INATIVA.getDescricao());
            }
        });
    }
}
