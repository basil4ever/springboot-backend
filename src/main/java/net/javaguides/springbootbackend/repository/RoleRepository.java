package net.javaguides.springbootbackend.repository;

import net.javaguides.springbootbackend.model.ERole;
import net.javaguides.springbootbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
