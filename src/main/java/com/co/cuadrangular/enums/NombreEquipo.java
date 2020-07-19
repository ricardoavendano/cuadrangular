package com.co.cuadrangular.enums;

public enum NombreEquipo {

	NOMBRE("Equipo");
	private String nombre;

	private NombreEquipo(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
}
