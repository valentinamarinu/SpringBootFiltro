package riwi.filtro.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import riwi.filtro.domain.entities.ClassEntity;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
    Page<ClassEntity> findByNameOrDescriptionAndActive(String name, String description, PageRequest pagination, Boolean active);
}
