package riwi.filtro.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import riwi.filtro.domain.entities.MultimediaEntity;

@Repository
public interface MultimediaRepository extends JpaRepository<MultimediaEntity, Long> {
    
}
