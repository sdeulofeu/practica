package PRACTICA;

import java.time.LocalDate;

public class Esportista 
{
	private String nom;
	private String cognom1;
	private String cognom2;
	private String dni;
	private String codiFederat;
	private char  sexe;
	private LocalDate data;
	
	public Esportista(String nom,String cognom1,String cognom2,char sexe,LocalDate data, String dni)
	{
		this.setNom(nom);
		this.setCognom1(cognom1);
		this.setCognom2(cognom2);
		this.setSexe(sexe);
		this.setDataNaixement(data);
		this.setDni(dni);
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
	
	public void modificaData(LocalDate data)
	{
		setDataNaixement(data);
	}
	
	public void modificaDNI(String dni)
	{
		setDni(dni);
	}
	
	public void show(Object[][] files, int aux_I)
	{
    	files[aux_I][0] = getNom();
    	files[aux_I][1] = getCognom1();
    	files[aux_I][2] = getCognom2();
    	files[aux_I][3] = getDni();
    	files[aux_I][4] = getDataNaixement();
    	files[aux_I][5] = getSexe();
	}
	
	public String getCodiFederat() {
		return codiFederat;
	}

	public void setCodiFederat(String codiFederat) {
		this.codiFederat = codiFederat;
	}

	//NOM
	public String getNom()
    {
    	return nom;
    }
    public void setNom(String nom)
    {
    	this.nom = nom;
    }
    
    //COGNOM1
    public String getCognom1()
    {
    	return cognom1;
    }
    public void setCognom1(String cognom1)
    {
    	this.cognom1 = cognom1;
    }
    
    //COGNOM2
    public String getCognom2()
    {
    	return cognom2;
    }
    public void setCognom2(String cognom2)
    {
    	this.cognom2 = cognom2;
    }

    //DNI
    public String getDni()
    {
    	return dni;
    }
    public void setDni(String dni)
    {
    	this.dni = dni;
    }
    
    //SEXE
    public char getSexe()
    {
    	return sexe;
    }
    public void setSexe(char sexe)
    {
    	this.sexe = sexe;
    }
    
    //DATA
    public LocalDate getDataNaixement()
    {
    	return data;
    }
    public void setDataNaixement(LocalDate data)
    {
    	this.data = data;
    }   
}