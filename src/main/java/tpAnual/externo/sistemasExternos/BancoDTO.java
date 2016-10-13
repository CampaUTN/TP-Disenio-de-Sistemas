package tpAnual.externo.sistemasExternos;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class BancoDTO {

	@Embedded
	private String[] servicios;
	private String banco;
	private String y;
	private String x;

	@JsonCreator
    BancoDTO(@JsonProperty("servicios") String[] servicios, @JsonProperty("banco") String banco,
    		@JsonProperty("y") String y, @JsonProperty("x") String x) {
        this.x = x;
        this.y = y;
        this.servicios = servicios;
        this.banco = banco;
	}
	
	public BancoDTO(){}
	public String[] getServicios() {
		return servicios;
	}

	public void setServicios(String[] servicios) {
		this.servicios = servicios;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}
}