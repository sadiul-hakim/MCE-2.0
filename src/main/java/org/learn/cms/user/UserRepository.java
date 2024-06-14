package org.learn.cms.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserRepository extends JpaRepository<UserModel,Long> {
    Optional<UserModel> findByEmail(String mail);
}
