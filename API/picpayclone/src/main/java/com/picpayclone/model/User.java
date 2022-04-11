package com.picpayclone.model;

import com.picpayclone.enums.PermissionType;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "USERS")
public class User extends BaseEntity implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Column(name = "USU_LOGIN", nullable = false)
    private String login;

    @Column(name = "USU_PASSWORD", nullable = false)
    private String password;

    @Column(name = "USU_EMAIL", nullable = false)
    private String email;

    @Column(name = "USU_FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "USU_CPF", nullable = false)
    private String cpf;

    @Column(name = "USU_BIRTHDAY", nullable = false)
    private LocalDate birthday;

    @Column(name = "USU_TELEPHONE_NUMBER", nullable = false)
    private String telephoneNumber;

    // FetchType.LAZY = slowly render data to avoid stopping
    // CascadeType.MERGE = when user updates, update CreditCard info
    // orphanRemoval = remove all data associated with CreditCard
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<CreditCard> creditCards;

    @Column(name = "USU_BALANCE", nullable = false)
    private Double balance;

    @Column(name = "USU_ACTIVE", nullable = false)
    private Boolean active;

    @Enumerated(EnumType.STRING)
    @Column(name = "USU_PERMISSION", nullable = false)
    private PermissionType permission;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorizationList =
                Arrays.asList(new SimpleGrantedAuthority(permission.getCode()));
        return authorizationList;
    }


    // Getters
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }

}
