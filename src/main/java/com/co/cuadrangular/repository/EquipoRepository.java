package com.co.cuadrangular.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.co.cuadrangular.domain.Equipo;

@Component
public interface EquipoRepository extends CrudRepository<Equipo, Long>{
	
	@Query("SELECT e FROM Equipo e ORDER BY e.puntuacion DESC")
	List<Equipo> equipoOrdenadoPuntuacion();

}
