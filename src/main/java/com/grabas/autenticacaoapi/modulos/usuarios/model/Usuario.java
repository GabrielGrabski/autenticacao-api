package com.grabas.autenticacaoapi.modulos.usuarios.model;

import com.grabas.autenticacaoapi.comum.enums.EStatus;
import com.grabas.autenticacaoapi.modulos.roles.model.Role;
import com.grabas.autenticacaoapi.modulos.usuarios.dto.UsuarioRequest;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private EStatus status;

    @Column(name = "NOME")
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USUARIO_ROLE",
            joinColumns = @JoinColumn(name = "USUARIO_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private List<Role> roles;

    public static Usuario of(UsuarioRequest request, List<Role> roles) {
        var usuario = new Usuario();
        BeanUtils.copyProperties(request, usuario);
        usuario.setRoles(roles);
        return usuario;
    }
}
