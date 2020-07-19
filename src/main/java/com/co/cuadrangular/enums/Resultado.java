package com.co.cuadrangular.enums;

public enum Resultado {
	
	GANO(3), EMPATO(2), PERDIO(1);
	private int estado;
	
	private Resultado (int estado){
		this.estado = estado;
	}

	public int getEstado() {
		return estado;
	}	

}
