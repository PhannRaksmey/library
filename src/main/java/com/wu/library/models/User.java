package com.wu.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
@Entity(name = "user_entity")
@Table(name = "tbl_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements UserDetails {

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private String id;
    private String name;
    private String phone;
    private String username;
    private String password;
    private String address;
    private Boolean status;
    private String profileImg;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "user_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "id") })
    private List<Role> roles;

    public User(String username, String password, Boolean status, String profileImg, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.profileImg = profileImg;
        this.roles = roles;
    }

    public User(String id, String username, String password, Boolean status, String profileImg) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.profileImg = profileImg;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
        return status;
    }
}
