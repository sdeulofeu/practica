package PRACTICA;

public class Prova 
{
	String nom,codiProva;
	String any;
	String participants;
	
	public Prova(String nom, String codiProva, String any, String participants)
	{
		this.setNom(nom);
		this.setCodiProva(codiProva);
		this.setAny(any);
		this.setParticipants(participants);
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
    
    //CODI PROVA
  	public String getCodiProva()
      {
      	return codiProva;
      }
      public void setCodiProva(String codiProva)
      {
      	this.codiProva = codiProva;
      }
      
      //ANY
      public String getAny()
      {
      	return any;
      }
      public void setAny(String any)
      {
      	this.any = any;
      }
      
      //PARTICIPANTS
      public String getParticipants()
      {
      	return participants;
      }
      public void setParticipants(String participants)
      {
      	this.participants = participants;
      }

}
