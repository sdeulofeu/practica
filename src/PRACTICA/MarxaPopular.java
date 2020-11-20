package PRACTICA;

import java.time.LocalTime;

public class MarxaPopular extends Prova 
{

	private String ubicacio;
	private String data;
	private LocalTime hSortida;
	//Nomès poden participar gent que no està federada
	//Codi Prova, ubicació, data, hora de sortida.
	public MarxaPopular(String nom, String codiProva, String any, String participants) 
	{
		super(nom, codiProva, any, participants);
		this.getUbicacio();
		this.getData();
		this.gethSortida();
	}
	
	public void modify(String nom, String any, String codiProva, String data,LocalTime hSortida) 
	{
		setNom(nom);
		setAny(any);
		setCodiProva(codiProva);
		setData(data);
		sethSortida(hSortida);
	}
	
	public void modificaNom(String nom)
	{
		setNom(nom);
	}
	
	public void modificaCodiProva(String codi)
	{
		setCodiProva(codi);
	}
	
	public void modificaAny(String any)
	{
		setAny(any);
	}
	
	public void modificaData(String data)
	{
		setData(data);
	}
	
	public void modificaHoraSortida(LocalTime hSortida )
	{
		sethSortida(hSortida);
	}
	
	public void show(Object[][] files, int aux_I)
	{
		files[aux_I][0] = getNom();
    	files[aux_I][1] = getCodiProva();
    	files[aux_I][2] = getAny();
    	files[aux_I][3] = getParticipants();
    	files[aux_I][4] = getUbicacio();
    	files[aux_I][5] = getData();
    	files[aux_I][6] = gethSortida();
	}
	
	public LocalTime gethSortida() {
		return hSortida;
	}
	public void sethSortida(LocalTime hSortida) {
		this.hSortida = hSortida;
	}
	public String getUbicacio() {
		return ubicacio;
	}
	public void setUbicacio(String ubicacio) {
		this.ubicacio = ubicacio;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

}
