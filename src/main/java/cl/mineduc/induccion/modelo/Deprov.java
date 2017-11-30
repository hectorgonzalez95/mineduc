package cl.mineduc.induccion.modelo;

import java.util.List;

public class Deprov 
{
	private int idDeprov;
	private String nombreDeprov;
	private List<Comuna> comunas;
	public int getIdDeprov() {
		return idDeprov;
	}
	public void setIdDeprov(int idDeprov) {
		this.idDeprov = idDeprov;
	}
	public String getNombreDeprov() {
		return nombreDeprov.trim();
	}
	public void setNombreDeprov(String nombreDeprov) {
		this.nombreDeprov = nombreDeprov;
	}
	public List<Comuna> getComunas() {
		return comunas;
	}
	public void setComunas(List<Comuna> comunas) {
		this.comunas = comunas;
	}
	
	
}
