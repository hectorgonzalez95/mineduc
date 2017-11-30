package cl.mineduc.induccion.modelo;

import java.util.List;

public class Region 
{
	private int idRegion;
	private String nombreRegion;
	private List<Deprov> deprovs;
	
	public int getIdRegion() {
		return idRegion;
	}
	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}
	public String getNombreRegion() {
		return nombreRegion.trim();
	}
	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
	}
	public List<Deprov> getDeprovs() {
		return deprovs;
	}
	public void setDeprovs(List<Deprov> deprovs) {
		this.deprovs = deprovs;
	}
	
	
}
