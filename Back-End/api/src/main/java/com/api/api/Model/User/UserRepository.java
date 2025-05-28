package com.api.api.Model.User;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);

    @Query(value = """
           SELECT id FROM users WHERE email = :email
           """, nativeQuery = true)
    Long userId(String email);

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE USERS SET ATIVO = 0 WHERE ID = :id; 
           """, nativeQuery = true)
    int intelDeleteUser(Long id);

    @Query(value = """
        select ativo from users where email = :email;
           """, nativeQuery = true)
    Boolean userActive(String email);
}
