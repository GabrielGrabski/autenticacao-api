package com.grabas.autenticacaoapi.modulos.roles.repository;

import com.grabas.autenticacaoapi.comum.enums.EStatus;
import com.grabas.autenticacaoapi.modulos.roles.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {

    List<Role> findAllByIdInAndStatus(List<Integer> ids, EStatus status);
}
