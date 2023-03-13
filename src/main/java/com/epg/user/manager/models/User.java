package com.epg.user.manager.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;



@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_table")
@JsonSerialize
public class User implements UserDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long userId;

    private String userName;
    private String userPassword;
    private String userEmail;

    @Builder.Default
    private String userRole="USER";

    @Builder.Default
    private Boolean userEnabled=Boolean.TRUE;
    @Builder.Default
    private Boolean userLocked=Boolean.FALSE;
    @Builder.Default
    private Boolean userExpired=Boolean.FALSE;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roleCollection= new LinkedList<>();
        roleCollection.add(this.userRole);
        return roleCollection.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.userExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.userLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.userExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.userEnabled;
    }
}
