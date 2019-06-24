package com.vault.examen.repository;

import com.vault.examen.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long>{
    
}
