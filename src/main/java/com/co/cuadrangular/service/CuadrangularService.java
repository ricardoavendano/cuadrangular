package com.co.cuadrangular.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.co.cuadrangular.datatransfer.EquipoDTO;
import com.co.cuadrangular.datatransfer.LigaDTO;

import fj.data.Either;

@Service
public interface CuadrangularService {
	
	public Either<Exception, LigaDTO> crearTorneo(Integer cantidadEquipo);
	
	public Either<Exception, List<EquipoDTO>> crearPuntuacion(LigaDTO listaLiga);

}
