package com.wjnovoam.app.repository;

import com.wjnovoam.app.entitys.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
}
