package com.api.repository;

import com.api.model.JogadorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Miguel Castro
 */
@Repository
public interface JogadorRepository extends JpaRepository<JogadorModel, Long>{
    
}
