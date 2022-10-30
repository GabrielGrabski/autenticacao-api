package com.grabas.autenticacaoapi.modulos.usuarios.repository;

import com.grabas.autenticacaoapi.modulos.usuarios.model.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);
}
