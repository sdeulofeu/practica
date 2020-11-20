package PRACTICA;

import java.time.LocalDate;

public class EsportistaFederat extends Esportista
{
	private String codiFederat;
	private String club;
	
	public EsportistaFederat(String nom,String cognom1,String cognom2,char sexe,LocalDate data, String dni ,String club,String codiFederat)
	{
		super(nom, cognom1, cognom2, sexe, data,dni);				
		this.setClub(club);
		this.setCodiFederat(codiFederat);
	}

	
	public void modificaNom(String nom)
	{
		setNom(nom);
	}
	
	public void modificaCognom(String cognom)
	{
		setCognom1(cognom);
	}
	
	public void modificaCognom2(String cognom2)
	{
		setCognom2(cognom2);
	}
	
	public void modificaSexe(char sexe)
	{
		setSexe(sexe);
	}
	
	public void modificaData(LocalDate date)
	{
		setDataNaixement(date);
	}
	
	public void modificaDNI(String dni)
	{
		setDni(dni);
	}
	
	public void modificaCIub(String club)
	{
		setClub(club);
	}
	
	public void modificaCodiFederat(String codi)
	{
		setCodiFederat(codi);
	}
	
	public void show(Object[][] files, int aux_I)
	{
		files[aux_I][0] = getNom();
    	files[aux_I][1] = getCognom1();
    	files[aux_I][2] = getCognom2();
    	files[aux_I][3] = getDni();
    	files[aux_I][4] = getDataNaixement();
    	files[aux_I][5] = getCodiFederat();
    	files[aux_I][6] = getClub();
    	files[aux_I][7] = getSexe();
	}
	
	//CodiFederat
    public String getCodiFederat() 
    {
    	return codiFederat;
    }
    public void setCodiFederat(String codiFederat)
    {
    	this.codiFederat = codiFederat;
    }
    
    //CLUB
    public String getClub()
    {
    	return club;
    }
    public void setClub(String club)
    {
    	this.club = club;
    }
}
