package models;
// Generated 10-feb-2017 9:21:19 by Hibernate Tools 4.3.1.Final

import java.util.Date;

/**
 * CiudadEvento generated by hbm2java
 */
public class CiudadEvento implements java.io.Serializable {

	private Integer idCiudadEvento;
	private Ciudad ciudad;
	private Evento evento;
	private Date fecha;

	public CiudadEvento() {
	}

	public CiudadEvento(Ciudad ciudad, Evento evento, Date fecha) {
		this.ciudad = ciudad;
		this.evento = evento;
		this.fecha = fecha;
	}

	public Integer getIdCiudadEvento() {
		return this.idCiudadEvento;
	}

	public void setIdCiudadEvento(Integer idCiudadEvento) {
		this.idCiudadEvento = idCiudadEvento;
	}

	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Evento getEvento() {
		return this.evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}