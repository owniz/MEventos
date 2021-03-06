package models;
// Generated 13-feb-2017 16:09:05 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Evento generated by hbm2java
 */
public class Evento implements java.io.Serializable {

	private Integer idEvento;
	private String denominacion;
	private String horaInicio;
	private String horaFin;
	private Date fecha;
	private String descripcion;
	private String path;
	private Set eventoSuscritos = new HashSet(0);
	private Set ciudadEventos = new HashSet(0);

	public Evento() {
	}

	public Evento(String denominacion, String horaInicio, String horaFin, Date fecha, String descripcion, String path,
			Set eventoSuscritos, Set ciudadEventos) {
		this.denominacion = denominacion;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.path = path;
		this.eventoSuscritos = eventoSuscritos;
		this.ciudadEventos = ciudadEventos;
	}

	public Integer getIdEvento() {
		return this.idEvento;
	}

	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}

	public String getDenominacion() {
		return this.denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Set getEventoSuscritos() {
		return this.eventoSuscritos;
	}

	public void setEventoSuscritos(Set eventoSuscritos) {
		this.eventoSuscritos = eventoSuscritos;
	}

	public Set getCiudadEventos() {
		return this.ciudadEventos;
	}

	public void setCiudadEventos(Set ciudadEventos) {
		this.ciudadEventos = ciudadEventos;
	}

}
