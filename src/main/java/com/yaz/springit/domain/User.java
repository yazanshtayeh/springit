package com.yaz.springit.domain;


import com.yaz.springit.domain.validator.PasswordsMatch;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
@PasswordsMatch
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Size(min = 8, max = 30)
    @Column(nullable = false, unique = true)
    private String email;

    @NonNull
    @Column(length = 100)
    private String password;

    @NonNull
    @Column(nullable = false)
    private boolean enabled;

    private String activationCode;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();

    @NonNull
    @NotEmpty(message = "You must enter First Name.")
    private String firstName;

    @NonNull
    @NotEmpty(message = "You must enter Last Name.")
    private String lastName;

    @Transient
    @Setter(AccessLevel.NONE)
    private String fullName;

    @NonNull
    @NotEmpty(message = "Please enter alias.")
    @Column(nullable = false, unique = true)
    private String alias;

    @Transient
    @NotEmpty(message = "please enter password confirmation")
    private String confirmPassword;

    public String getFullName(){
        return firstName + " " + lastName;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void addRoles(Set<Role> roles) {
        roles.forEach(this::addRole);
    }


    @Override
    public String getUsername() {
        return null;
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
        return enabled;
    }
}