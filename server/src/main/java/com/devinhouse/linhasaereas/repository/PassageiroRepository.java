package com.devinhouse.linhasaereas.repository;

import com.devinhouse.linhasaereas.model.Passageiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassageiroRepository extends JpaRepository<Passageiro, Long> {


}