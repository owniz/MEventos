package models;
// Generated 13-feb-2017 16:09:05 by Hibernate Tools 4.3.1.Final

/**
 * CiudadEvento generated by hbm2java
 */
public class CiudadEvento implements java.io.Serializable {

	private Integer idCiudadEvento;
	private Ciudad ciudad;
	private Evento evento;

	public CiudadEvento() {
	}

	public CiudadEvento(Ciudad ciudad, Evento evento) {
		this.ciudad = ciudad;
		this.evento = evento;
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

}
