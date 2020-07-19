package com.co.cuadrangular.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "equipo")
public class Equipo implements Serializable{

	private static final long serialVersionUID = -6802910756118730001L;
	
	@Id
	@Column(nullable = false, name = "idequipo")
	private Long idEquipo;

	@Column(nullable = false, name = "nombre")
	private String nombre;
	
	@Column(nullable = false, name = "puntuacion")
	private Long puntuacion;

	public Long getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Long idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Long puntuacion) {
		this.puntuacion = puntuacion;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idEquipo != null ? idEquipo.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Equipo)) {
			return false;
		}
		Equipo other = (Equipo) object;
		if ((this.idEquipo == null && other.idEquipo != null)
				|| (this.idEquipo != null && !this.idEquipo.equals(other.idEquipo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entity.Compra[ idcompra=" + idEquipo + " ]";
	}

}
