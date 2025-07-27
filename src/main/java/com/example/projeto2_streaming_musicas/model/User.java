package com.example.projeto2_streaming_musicas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Size(max = 250)
    @NotNull
    @Column(name = "user_name", nullable = false, length = 250)
    private String userName;

    @Size(max = 250)
    @NotNull
    @Column(name = "user_password", nullable = false, length = 250)
    private String userPassword;

    @OneToOne(
            fetch = FetchType.LAZY,
            optional = false,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn( name = "user_role",  referencedColumnName = "role_id", unique = true )
    private Role userRole;

    public User() {}
    public User( String name, String password )
    {
        this.userName = name;
        this.userPassword = password;
    }

    public Long getId() {
        return id;
    }
    public String getUserName() {
        return userName;
    }
    public String getUserPassword() {
        return userPassword;
    }

    public Role getUserRole() {
        return userRole;
    }
    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof User user) ) return false;
        return (Objects.equals(userName, user.userName) && Objects.equals(userPassword, user.userPassword) );
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userPassword);
    }

}