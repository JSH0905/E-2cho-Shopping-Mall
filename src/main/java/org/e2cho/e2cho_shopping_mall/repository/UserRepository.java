package org.e2cho.e2cho_shopping_mall.repository;

import org.e2cho.e2cho_shopping_mall.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findBySnsId(String snsId);
}
