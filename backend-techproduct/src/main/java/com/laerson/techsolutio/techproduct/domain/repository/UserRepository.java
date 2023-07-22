package com.laerson.techsolutio.techproduct.domain.repository;

import com.laerson.techsolutio.techproduct.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}