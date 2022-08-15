package com.gft.ohMyDog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.ohMyDog.entities.Cachorro;

@Repository
public interface CachorroRepository extends JpaRepository<Cachorro, Long>{

}
