package com.co.cuadrangular.adapter;

import org.springframework.stereotype.Component;

import com.co.cuadrangular.datatransfer.EquipoDTO;
import com.co.cuadrangular.domain.Equipo;

@Component
public class EquipoAdapter {
	
	public EquipoDTO equipoAdp(Equipo equipo) {
		
		EquipoDTO equipoDTO = new EquipoDTO();
		equipoDTO.setNombre(equipo.getNombre());
		equipoDTO.setPuntuacion(equipo.getPuntuacion());
		
		return equipoDTO;
	}

}
