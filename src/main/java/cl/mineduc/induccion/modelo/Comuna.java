package cl.mineduc.induccion.modelo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class Comuna 
{
	private int idComuna;
	private String nombreComuna;
	
	public int getIdComuna() {
		return idComuna;
	}

	public void setIdComuna(int idComuna) {
		this.idComuna = idComuna;
	}

	public String getNombreComuna() {
		return nombreComuna.trim();
	}

	public void setNombreComuna(String nombreComuna) {
		this.nombreComuna = nombreComuna;
	}

	@Override
	public String toString() 
	{
		ToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
		return ToStringBuilder.reflectionToString(this);
	}
}
