package PRACTICA;

import java.time.LocalTime;

public class Marato extends Prova
{
	private String data;
	private LocalTime horaSortida;
	
	public Marato(String nom, String codiProva, String any, String participants)
	{
		super(nom, codiProva, any, participants);
		this.getData();
		this.getHoraSortida();
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
	
	public void modificaHoraSortida(LocalTime hSortida)
	{
		setHoraSortida(hSortida);
	}
	
	public void show(Object[][] files, int aux_I)
	{
		files[aux_I][0] = getNom();
		files[aux_I][1] = getCodiProva();
		files[aux_I][2] = getAny();
		files[aux_I][3] = getParticipants();
		files[aux_I][4] = getData();
		files[aux_I][5] = getHoraSortida();
	}

	
	public LocalTime getHoraSortida() 
	{
		return horaSortida;
	}
	public void setHoraSortida(LocalTime hSortida) 
	{
		this.horaSortida = hSortida;
	}
	public String getData() 
	{
		return data;
	}
	public void setData(String string) 
	{
		this.data = string;
	}
}
