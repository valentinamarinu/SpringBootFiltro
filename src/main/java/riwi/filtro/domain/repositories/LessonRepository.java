package riwi.filtro.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import riwi.filtro.domain.entities.LessonEntity;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {
    
}
