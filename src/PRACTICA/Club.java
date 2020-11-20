package PRACTICA;

public class Club 
{
		String nom, poblacio;
		int codiclub;
		String anyFundacio;
		String codiPostal;
		
		public Club(String nom, String poblacio, String codiPostal, String anyFundacioClub, int codiClub)
		{
			this.setNom(nom);
			this.setPoblacio(poblacio);
			this.setCodiPostal(codiPostal);
			this.setCodiClub(codiClub);
			this.setAnyFede(anyFundacioClub);
		}
		
		public void modify(String nom, String poblacio, String codiPostal, String anyFundacioClub)
		{
			setNom(nom);
			setPoblacio(poblacio);
			setCodiPostal(codiPostal);
			setCodiClub(codiclub);
			setAnyFede(anyFundacioClub);
		}
		
		public void show(Object[][] files, int aux_I)
		{
			files[aux_I][0] =  getNom();
			files[aux_I][1] = 	getPoblacio();
			files[aux_I][2] = 	getCodiPostal();
			files[aux_I][3] = 	getCodiClub();
			files[aux_I][4] = 	getAnyFede();
		}
	
		public void modificaNom(String nom)
		{
			setNom(nom);
		}
		
		public void modificaPoblacio(String poblacio)
		{
			setPoblacio(poblacio);
		}
		
		public void modificaAny(String any)
		{
			setAnyFede(any);
		}
		
		public void modificaCodiPostal(String codiPostal)
		{
			setCodiPostal(codiPostal);
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
	    
	    //poblacio
		public String getPoblacio()
	    {
	    	return poblacio;
	    }
	    public void setPoblacio(String poblacio)
	    {
	    	this.poblacio = poblacio;
	    }
	    
	    //CODI POSTAL
		public String getCodiPostal()
	    {
	    	return codiPostal;
	    }
	    public void setCodiPostal(String codiPostal)
	    {
	    	this.codiPostal = codiPostal;
	    }
	    
	    //ANYFEDERACIO
	    public String getAnyFede()
	    {
	    	return anyFundacio;
	    }
	    public void setAnyFede(String anyFundacioClub1)
	    {
	    	this.anyFundacio = anyFundacioClub1;
	    }
	    
	    //CODI CLUB
	    public int getCodiClub()
	    {
	    	return codiclub;
	    }
	    public void setCodiClub(int codiClub2)
	    {
	    	this.codiclub = codiClub2;
	    }
}