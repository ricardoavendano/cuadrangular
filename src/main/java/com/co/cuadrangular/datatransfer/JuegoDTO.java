package com.co.cuadrangular.datatransfer;

public class JuegoDTO {

	private int local;
	private int visitante;

	public JuegoDTO(int local, int visitante) {
		super();
		this.local = local;
		this.visitante = visitante;
	}

	public int getLocal() {
		return local;
	}

	public void setLocal(int local) {
		this.local = local;
	}

	public int getVisitante() {
		return visitante;
	}

	public void setVisitante(int visitante) {
		this.visitante = visitante;
	}

}
