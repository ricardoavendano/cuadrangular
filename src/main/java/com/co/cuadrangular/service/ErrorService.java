package com.co.cuadrangular.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.co.cuadrangular.datatransfer.Error;
import com.co.cuadrangular.exception.ErrorGuardarException;
import com.co.cuadrangular.exception.ErrorMostrandoPosicion;

@Service
public class ErrorService {

	public Error getError(Exception e) {

		if (e instanceof ErrorGuardarException) {

			return new Error("ErrorGuardarException001", "No se pudo guardar equipos");

		}
		if (e instanceof ErrorMostrandoPosicion) {

			return new Error("ErrorMostrandoPosicion001", "No se pudo mostrar la tabla de posiciones");

		}
										
		Map<String, String> params = new HashMap<>();
		params.put("Exception", e.getClass() + "-" + e.getMessage());
		params.put("Time", LocalDateTime.now().toString());

		return new Error("Cuadrangular001", "Unknown Error");
	}
}
