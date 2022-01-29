package com.wjnovoam.app.repository;

import com.wjnovoam.app.entitys.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository  extends JpaRepository<Rol, Long> {

    Optional<Rol> findByName(String name);
}
