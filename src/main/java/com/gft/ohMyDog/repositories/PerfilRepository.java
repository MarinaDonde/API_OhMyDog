package com.gft.ohMyDog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.ohMyDog.entities.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long>{

}
