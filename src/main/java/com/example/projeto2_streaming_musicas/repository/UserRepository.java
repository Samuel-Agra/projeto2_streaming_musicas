package com.example.projeto2_streaming_musicas.repository;

import com.example.projeto2_streaming_musicas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query ("SELECT u FROM User u JOIN FETCH u.userRole WHERE u.userName = :name")
    Optional<User> findUserWithRoleByName(@Param( "name" ) String name);

}
