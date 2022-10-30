package com.grabas.autenticacaoapi.config.security.service;

import com.grabas.autenticacaoapi.comum.enums.EStatus;
import com.grabas.autenticacaoapi.comum.exception.model.ValidacaoException;
import com.grabas.autenticacaoapi.modulos.usuarios.model.Usuario;
import com.grabas.autenticacaoapi.modulos.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.grabas.autenticacaoapi.comum.enums.EErrors.USUARIO_INATIVO;
import static com.grabas.autenticacaoapi.comum.enums.EErrors.USUARIO_NAO_ENCONTRADO;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = repository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(USUARIO_NAO_ENCONTRADO.getDescricao()));

        validarUsuarioInativo(usuario);

        return new User(usuario.getEmail(), usuario.getSenha(), getAuthorities(usuario));
    }

    private void validarUsuarioInativo(Usuario usuario) {
        if (usuario.getStatus().equals(EStatus.I)) {
            throw new ValidacaoException(USUARIO_INATIVO.getDescricao());
        }
    }

    private List<? extends GrantedAuthority> getAuthorities(Usuario usuario) {
        return usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getCodigo()))
                .collect(Collectors.toList());
    }
}
