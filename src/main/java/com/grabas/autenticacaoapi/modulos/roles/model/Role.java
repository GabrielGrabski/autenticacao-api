package com.grabas.autenticacaoapi.modulos.roles.model;

import com.grabas.autenticacaoapi.comum.enums.EStatus;
import com.grabas.autenticacaoapi.modulos.roles.dto.RoleRequest;
import com.grabas.autenticacaoapi.modulos.usuarios.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROLE")
public class Role {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CODIGO")
    private String codigo;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private EStatus status;

    @Column(name = "DESCRICAO")
    private String descricao;

    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;

    public static Role of(RoleRequest request) {
        var role = new Role();
        BeanUtils.copyProperties(request, role);
        return role;
    }
}
