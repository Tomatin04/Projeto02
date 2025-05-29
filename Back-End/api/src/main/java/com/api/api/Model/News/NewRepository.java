package com.api.api.Model.News;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface NewRepository extends JpaRepository<New, Long> {
    @Query(value = """
            SELECT * FROM news WHERE is_deleted IS FALSE ORDER BY id DESC
            """, nativeQuery = true)
    Page<New> findAllByIsNotDeleted(Pageable pageable);


    @Query(value = """
            SELECT * FROM news 
            WHERE titulo LIKE %:titulo%
            AND is_deleted IS FALSE
            """, nativeQuery = true)
    New findByTituloNotDeleted(String titulo);

    @Query(value = """
            SELECT * FROM news 
            WHERE id = :id
            AND is_deleted IS FALSE
            """, nativeQuery = true)
    New findByIdNotDeleted(Long id);

    //Optional<New> findById(@NotBlank @NotNull Long aLong);
}
