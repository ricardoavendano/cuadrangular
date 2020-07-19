package com.co.cuadrangular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.cuadrangular.datatransfer.EquipoDTO;
import com.co.cuadrangular.datatransfer.Error;
import com.co.cuadrangular.datatransfer.LigaDTO;
import com.co.cuadrangular.request.EquipoRq;
import com.co.cuadrangular.service.CuadrangularService;
import com.co.cuadrangular.service.ErrorService;

import fj.data.Either;

@EnableAutoConfiguration
@CrossOrigin(origins = "*")
@RequestMapping
@RestController
@Controller
public class CuadrabgularController {
	
	@Autowired
	private ErrorService errorService;
	
	@Autowired
	private CuadrangularService cuadrangularService;
	
	@PostMapping(value = "/cargar/equipos")
	public ResponseEntity<?> validarLogin(@RequestBody EquipoRq equipoRq) {

		Either<Exception, LigaDTO> resultEither = cuadrangularService.crearTorneo(equipoRq.getNumeroEquipo());

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}
		
		Error error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(value = "/guardar/puntos")
	public ResponseEntity<?> validarLogin(@RequestBody LigaDTO liga) {

		Either<Exception, List<EquipoDTO>> resultEither = cuadrangularService.crearPuntuacion(liga);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}
		
		Error error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
