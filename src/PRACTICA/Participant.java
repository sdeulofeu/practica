package PRACTICA;

import java.time.LocalDate;
import java.time.LocalTime;


public class Participant extends Esportista
{
	private String categoria;
	private String club;
	private String codiProva;
	private long dorsal;
	private LocalTime tempsProva;
	private String tempsProva2;
	int pos;
	public Participant(String nom,	String cognom1,String cognom2,char sexe, LocalDate data, String dni, String categoria, String club,long dorsal,LocalTime tempsProva2,String codiProva)
	{
		super(nom, cognom1, cognom2, sexe, data, dni);
		this.getCategoria();
		this.getClub();
		this.getDorsal();
		this.getTempsProva();
		this.getCodiProva();
	}
	
	public void show(Object[][] files, int aux_I)
	{
		files[aux_I][0] = getCodiProva();
		files[aux_I][1] = getNom();
		files[aux_I][2] = getCognom1();
		files[aux_I][3] = getSexe();
		files[aux_I][4] = getDataNaixement();
		files[aux_I][5] = getDni();
		files[aux_I][6] = getCategoria();
		files[aux_I][7] = getDorsal();
		files[aux_I][8] = getTempsProva();
	}
	
	public void showFed(Object[][] files, int aux_I)
	{
		files[aux_I][0] = getCodiProva();
    	files[aux_I][1] = getNom();
    	files[aux_I][2] = getCognom1();
    	files[aux_I][3] = getSexe();
    	files[aux_I][4] = getDataNaixement();
    	files[aux_I][5] = getDni();
    	files[aux_I][6] = getCategoria();
    	files[aux_I][7] = getClub();
    	files[aux_I][8] = getDorsal();
    	files[aux_I][9] = getTempsProva();
	}
	
	public void showClassificacio(Object[][] files, int aux_I, int j)
	{
    	files[aux_I][0] = getPosicio();
    	files[aux_I][1] = getDorsal();
    	files[aux_I][2] = getNom();
    	files[aux_I][3] = getSexe();
    	files[aux_I][4] = getCategoria();
    	files[aux_I][5] = getTempsProva2();
	}
	
	public void TempsArribada(LocalTime temps)
	{
		setTempsProva(temps);
	}

	public String getCodiProva() {
		return codiProva;
	}

	public void setCodiProva(String codiProva) {
		this.codiProva = codiProva;
	}

	public LocalTime getTempsProva() {
		return tempsProva;
	}
	
	public void setTempsProva(LocalTime tempsRecorregut) {
		this.tempsProva = tempsRecorregut;
	}
	
	public String getTempsProva2() {
		return tempsProva2;
	}
	
	public void setTempsProva2(String tempsRecorregut) {
		this.tempsProva2 = tempsRecorregut;
	}
	
	public int getPosicio() {
		return pos;
	}
	
	public void setPosicio(int pos) {
		this.pos = pos;
	}

	public String getCategoria()
    {
    	return categoria;
    }
    public void setCategoria(String categoria)
    {
    	this.categoria = categoria;
    }
    
    public String getClub()
    {
    	return club;
    }
    public void setClub(String club)
    {
    	this.club = club;
    }
    
    //DORSAL
    public long getDorsal()
    {
    	return dorsal;
    }
    public void setDorsal(long d)
    {
    	this.dorsal = d;
    }

	public void modificaHoraSortida(LocalTime horaSortida) 
	{
		setTempsProva(horaSortida);
	}
    

}
//      Esportista               (Nom, cognom1, cognom2, sexe, data, dni)
//		 EsportistaFederat   (Nom, cognom1, cognom2, sexe, data, dni, club,codi federat)
//	     Participant              (Nom, cognom1, cognom2, sexe, categoria, club,dorsal,tempsProva.



