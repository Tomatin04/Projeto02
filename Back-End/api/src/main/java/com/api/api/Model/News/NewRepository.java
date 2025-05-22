package com.api.api.Model.News;

import com.api.api.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewRepository extends JpaRepository<New, Long> {
}
