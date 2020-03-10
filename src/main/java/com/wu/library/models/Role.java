package com.wu.library.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_role")
public class Role implements GrantedAuthority {
    @Id
   // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;
    private String role;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "user_roles",
            joinColumns = { @JoinColumn(name = "id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private List<User> userList;

    public Role(String id, String role) {
        this.id = id;
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return "ROLE_"+role;
}
}
