package models;
// Generated 13-feb-2017 16:09:05 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Ciudad generated by hbm2java
 */
public class Ciudad implements java.io.Serializable {

	private Integer idCiudad;
	private String nombreCiudad;
	private String provincia;
	private Set ciudadEventos = new HashSet(0);

	public Ciudad() {
	}

	public Ciudad(String nombreCiudad, String provincia, Set ciudadEventos) {
		this.nombreCiudad = nombreCiudad;
		this.provincia = provincia;
		this.ciudadEventos = ciudadEventos;
	}

	public Integer getIdCiudad() {
		return this.idCiudad;
	}

	public void setIdCiudad(Integer idCiudad) {
		this.idCiudad = idCiudad;
	}

	public String getNombreCiudad() {
		return this.nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public Set getCiudadEventos() {
		return this.ciudadEventos;
	}

	public void setCiudadEventos(Set ciudadEventos) {
		this.ciudadEventos = ciudadEventos;
	}

}
