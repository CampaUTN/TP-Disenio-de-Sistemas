package tpAnual.externo.sistemasExternos;

public class ServicioDTO {
	String nombre;
	int diaSemana;
	int horaDesde;
	int minutosDesde;
	int horaHasta;
	int minutosHasta;
	
	public ServicioDTO(String nom, int dia, int horaD, int minD, int horaH, int minH){
		nombre = nom;
		diaSemana = dia;
		horaDesde = horaD;
		minutosDesde = minD;
		horaHasta = horaH;
		minutosHasta = minH;
	}
	
	public int getDia(){
		return diaSemana;
	}
	public String getNombre(){
		return nombre;
	}
	public int getHoraD(){
		return horaDesde;
	}
	public int getMinD(){
		return minutosDesde;
	}
	public int getHoraH(){
		return horaHasta;
	}
	public int getMinH(){
		return minutosHasta;
	}
}
