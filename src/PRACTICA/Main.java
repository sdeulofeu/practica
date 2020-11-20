package PRACTICA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
public class Main 
{
	static	 Scanner LECTOR = new Scanner(System.in);
	 static boolean EXIT=false;
	 static	 String nom, cognom1,cognom2, dni;
	 static String nom2;
	 static double codiFederat;
	 static char sexe;
	 static boolean firstTime;
	 static String path;
	 static	 File arxiuInicial,clasificacioF,clasificacio,esportistes,esportistesF,proves,club,participant,participantF,marato,prova10k,marxaP;
	 static ImageIcon icon = new ImageIcon("png\\sport.PNG");			
	 static ImageIcon iconTick = new ImageIcon("png\\tickk.png");			
	 static ImageIcon iconError = new ImageIcon("png\\error.PNG");		
	 static String finalTime;

	 static ArrayList<Esportista> arrayEsportista = new ArrayList<Esportista>();
	 static ArrayList<EsportistaFederat> arrayEsporistaFederat = new ArrayList<EsportistaFederat>();
	 static ArrayList<Club> arrayClub = new ArrayList<Club>();
	 static ArrayList<Prova> arrayProva = new ArrayList<Prova>();
	 static ArrayList<Participant> arrayParticipant = new ArrayList<Participant>();
	 static ArrayList<Participant> arrayParticipantF = new ArrayList<Participant>();

	 //Arrays per les proves especifiques
	 static ArrayList<Marato> arrayMarato = new ArrayList<Marato>();
	 static ArrayList<Prova10000> arrayProva10k = new ArrayList<Prova10000>();
	 static ArrayList<MarxaPopular> arrayMarxaP = new ArrayList<MarxaPopular>();
	
	 static int numEsportistes;
	 static int numClubs;
	 static int numProves;
	 static int numParticipants;

	public static void main(String[] args) 
	{		
		Arxiu();	 //creem els arxius necessaris i comprovem si es la primera vegada que s'executa.
		if (firstTime) 			JOptionPane.showMessageDialog(null,"Benvingut al Gestor Definitiu\n   per a Clubs d'Atletisme ","Benvinguda",JOptionPane.INFORMATION_MESSAGE,icon);	
		else LoadData();
		
		while(!EXIT)
		{
			MenuMain();
		}
	}	
	
	//MENU PRINCIPAL
	private static  void MenuMain() 
	{
		String[] opcions = {"Dades", "Inscripció", "Proves", "Sortir"};
    	int opcio = JOptionPane.showOptionDialog(null,"Seleccioni una opció","Menú Principal",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,icon,opcions,null);		
    	if(opcio==JOptionPane.CLOSED_OPTION) System.exit(0); //Si es tenca la finestra, que es tenqui el programa
		switch(opcio)
		{
			case 0:
				MenuDades();			//Menu General de Dades
				break;
			case 1:
				MenuInscripcio();		//Menu General de Inscripcio
				break;
			case 2:
				MenuProves();			//Menu General de Proves
				break;
			case 3:
				EXIT=true; 				//Exit menu
				break;
			default:
		}
	}

	//MENUS SECUNDARIS
	private static void MenuProves() 
	{
		String[] opcions = {"Entrada Temps", "Classificació","Tornar"};
    	int opcio = JOptionPane.showOptionDialog(null,"Seleccioni una opció","Menu Proves",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,icon,opcions,null);	
    	if(opcio==JOptionPane.CLOSED_OPTION) System.exit(0);	
		switch(opcio)
		{
			case 0:
				MenuEntradaTemps();		//SubMenu d'Entrada de temps en les inscripcions
				SaveData();					
				break;
			case 1:
				MenuClassificacio();		//Menú de Classificació
				break;
		}
	}
	
	private static void MenuDades()
	{
		String[] opcions = {"Alta de Dades", "Modificació de Dades", "Consulta de Dades", "Tornar"};
    	int opcio = JOptionPane.showOptionDialog(null,"Seleccioni una opció","Menú Dades",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,icon,opcions,null);	
    	if(opcio==JOptionPane.CLOSED_OPTION) System.exit(0);
    	
		switch(opcio)
		{
			case 0:
				MenuDadesAlta();
				break;
			case 1:
				MenuDadesModif();
				break;
			case 2:
				MenuDadesConsult();
				break;			
		}
	}
	
	private static void MenuInscripcio() 
	{
		String[] opcions = {"Inscripció Prova", "Anul·lació Inscripció", "Llistar Inscripció", "Tornar"};
    	int opcio = JOptionPane.showOptionDialog(null,"Seleccioni una opció","Menu Inscripcio",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,icon,opcions,null);	
    	if(opcio==JOptionPane.CLOSED_OPTION) System.exit(0);
		switch(opcio)
		{
			case 0:
				InscripcioProva();
				SaveData();
				break;
			case 1:
				AnulacioProva();			
				SaveData();
				break;
			case 2:
				LlistaInscripcio();
				break;			
			case 3:
				System.out.println("Exiting Inscripció");
			default:
		}
	}
	
	//MENU SECUNDARI PROVES
	private static void MenuEntradaTemps()
	{
		String[] opcions = {"Sortida Participants", "Arribada Participants","Tornar"};
    	int opcio = JOptionPane.showOptionDialog(null,"Seleccioni una opció","Menu Entrada Temps Proves",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,icon,opcions,null);	
    	if(opcio==JOptionPane.CLOSED_OPTION) System.exit(0);	
    	ArrayList<String> nomProves = new ArrayList<String>();
    	Object[] array = null;
		Object seleccion = null;
		int index=0;

		switch(opcio)
		{
			case 0:
				tempsProva(opcio,nomProves,array,seleccion,index);
				break;
			case 1: 
				tempsArribadaParticipant(array, seleccion, index);		    	
				break;
		}
	}

	private static void MenuClassificacio() 
	{
		String[] opcions = {"Total", "Sexe","Categoria","Tornar"};
    	int opcio = JOptionPane.showOptionDialog(null,"Seleccioni una opció","Menu Classificació Proves",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,icon,opcions,null);	
    	if(opcio==JOptionPane.CLOSED_OPTION) System.exit(0);	

		switch(opcio)
		{
			case 0:
					showClasificacioTotal();		
				break;
			case 1:
					showClasificacioSexe();
				break;
			case 2:
					showClasificacioCategoria();
				break;
		}
	}
	
	//MENU SECUNDARI DADES
	private static void MenuDadesConsult() 
	{
		String[] opcions = {"Consulta Club", "Consulta Esportista", "Consulta Prova", "Tornar"};
    	int opcio = JOptionPane.showOptionDialog(null,"Seleccioni una opció","Menú Dades Consulta",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,icon,opcions,null);	
    	if(opcio==JOptionPane.CLOSED_OPTION) System.exit(0);
		switch(opcio)
		{
			case 0:
				ConsultaClub();
				break;
			case 1:
				ConsultaEsportista();
				break;
			case 2:
				ConsultaProva();
				break;			
		}
	}

	private static void MenuDadesModif() 
	{
		String[] opcions = {"Modificar Prova", "Modificar Esportista", "Modificar Club", "Tornar"};
    	int opcio = JOptionPane.showOptionDialog(null,"Seleccioni una opció","Menú Dades Modificació",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,icon,opcions,null);	
    	if(opcio==JOptionPane.CLOSED_OPTION) System.exit(0);
		switch(opcio)
		{
			case 0:
				ModificarProva();
				SaveData(); 	//Despres de fer canvis, ho guardem
				break;
			case 1:		//escollim gràficament quin tipus de participant vol modificar
				String[] options = new String[] {"Federats", "No Federats"};
			    int response = JOptionPane.showOptionDialog(null, "Escull un tipus d'Esportista a Modificar", "Modifica Esportista",
			        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
			        icon, options, options[0]);
			    if(response==0)
			    {
			    	ModificarEsportistaFederat(); 
			    	SaveData();
			    }
			    else if(response==1)
			    {
			    	ModificarEsportista(); 
			    	SaveData();
			    }
			    else
			    {
			    	MenuMain();
			    }			
				break;
			case 2:		
				ModificarClub();
				SaveData();
				break;			
			case 3:
				System.out.println("Exiting Modificació de Dades");
			default:
		}
	}
	
	private static void MenuDadesAlta() 
	{
		String[] opcions = {"Alta Club", "Alta Esportista", "Alta Prova", "Tornar"};
    	int opcio = JOptionPane.showOptionDialog(null,"Seleccioni una opció","Menú Dades Alta",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,icon,opcions,null);
    	if(opcio==JOptionPane.CLOSED_OPTION) System.exit(0);
		switch(opcio)
		{
			case 0:
				AltaClub();
				SaveData();
				break;
			case 1:
				AltaEsportista();
				SaveData();
				break;
			case 2:
				AltaProva();
				SaveData();
				break;			
			case 3:
				System.out.println("Exiting Alta de Dades");
			default:
		}
	}
	
	//MENU SECUNDARI INSCRIPCIO INSCRIPCIONS
	private static void InscripcioProva() 
	{
		if((arrayMarato.size()!=0)||(arrayMarxaP.size()!=0)||(arrayProva10k.size()!=0))
		{
			int x=0;
		
			String[] opcions = {"Marató", "Marxa Popular", "Prova10000", "Tornar"};
	    	int opcio = JOptionPane.showOptionDialog(null,"Seleccioni una Prova","Menu Inscripcio",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,icon,opcions,null);	
			switch(opcio)
			{
			case 0:		//MARATÓ			
				if((arrayMarato.size()!=0)) 
					InscripcioMarato(x);									
				else JOptionPane.showMessageDialog(null,"Afegeix avans alguna Marató","Inscripció Marató",JOptionPane.INFORMATION_MESSAGE,iconError);	
				break;
			case 1:		//MARXA POPULAR	
				if((arrayMarxaP.size()!=0)) 
					InscripcioMarxaP(x);	
				else 	JOptionPane.showMessageDialog(null,"Afegeix avans alguna Marxa Popular","Inscripció Marxa Popular",JOptionPane.INFORMATION_MESSAGE,iconError);				
				break;
			case 2:     //PROVA10K 
				if((arrayProva10k.size()!=0)) 
					InscripcioProva10k(x);					
				else JOptionPane.showMessageDialog(null,"Afegeix avans alguna Prova10k","Inscripció Prova10000",JOptionPane.INFORMATION_MESSAGE,iconError);	
				break;
			case 3:
				break;
			}						
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Afegeix avans alguna Prova","Inscripció Prova",JOptionPane.INFORMATION_MESSAGE,iconError);	
		}		
	}
	
	//MÈTODES DE DADES (MODIFICAR)
	private static void ModificarClub() 
	{
		//Creem una array auxiliar, per treballar amb els noms dels clubs.
		ArrayList<String> nomClubs = new ArrayList<String>();
		for(int p=0;p<arrayClub.size();p++)
		{
			nomClubs.add(arrayClub.get(p).getNom());
		}

		//Seleccionem gràficament quin Club es vol modificar
		Object[] array = nomClubs.toArray(new Object[nomClubs.size()]);
		Object seleccion = JOptionPane.showInputDialog(
				   null,
				   "Seleccioni un Club a Modificar",
				   "Modificació Club",
				   JOptionPane.QUESTION_MESSAGE,
				   null,
				   array, 
				   array[0]);
		if(seleccion==null) return;
		int index = nomClubs.indexOf(seleccion);		
		
		//Seleccionem, quin paràmetre del objecte Club, volem modificar
		String[] opcions = {"Nom Club", "Població Club", "Any Fundació","Codi Postal","Tornar"};
    	int opcio = JOptionPane.showOptionDialog(null,"Seleccioni una opció","Modificació Club",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,icon,opcions,null);	
    	Object value;
    	switch(opcio)
    	{
    		case 0:		//MODIFICAR NOM
    			value = JOptionPane.showInputDialog(null,"Nom nou: ","Modificació Club", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    			if(value==null) return;
    			arrayClub.get(index).modificaNom((String)value);
    			break;
    		case 1:	 	//MODIFICAR POBLACIO
    			value = JOptionPane.showInputDialog(null,"Població nova: ","Modificació Club", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    			if(value==null) return;
    			arrayClub.get(index).modificaPoblacio((String)value);
    			break;
    		case 2: 	//MODIFICAR ANY
    			value = JOptionPane.showInputDialog(null,"Any nou: ","Modificació Club", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    			if(value==null) return;
    			arrayClub.get(index).modificaAny((String)value);
    			break;
    		case 3: 	//MODIFICAR CODI POSTAL
    			value = JOptionPane.showInputDialog(null,"Codi Postal nou: ","Modificació Club", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    			if(value==null) return;
    			arrayClub.get(index).modificaCodiPostal((String)value);
    			break;
    		case 4:
    		default:
    			break;			
    	}
	}

	private static void ModificarEsportista() 
	{
		//Generem una array auxiliar per tal de treballar amb els noms dels esportistes i poguer escollir quin modificar
		ArrayList<String> nomEsportistes = new ArrayList<String>();
		for(int p=0;p<arrayEsportista.size();p++)
		{
			nomEsportistes.add(arrayEsportista.get(p).getNom());
		}
		
		//Seleccionem quin esportista volem seleccionar
		Object[] array = nomEsportistes.toArray(new Object[nomEsportistes.size()]);
		Object seleccion = JOptionPane.showInputDialog(
				   null,
				   "Seleccioni un Esportista a Modificar",
				   "Modificació Esportista",
				   JOptionPane.QUESTION_MESSAGE,
				   null,
				   array, 
				   array[0]);
		if(seleccion==null) return;

		int indexEsportista = nomEsportistes.indexOf(seleccion);	
		
		//Seleccionem quina caracteristica volem modificar
		String[] opcions = {"Nom", "Cognom", "Segon Cognom", "Sexe","Data Naixement","DNI","Tornar"};
    	int opcio = JOptionPane.showOptionDialog(null,"Seleccioni una opció","Modificació Esportista",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,icon,opcions,null);
    	Object value;
    	String index2;
    	switch(opcio)
    	{	
    		case 0: 	//MODIFICAR NOM
    			value = JOptionPane.showInputDialog(null,"Nom: ","Modificació Esportista", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    			if(value==null) return;
    			arrayEsportista.get(indexEsportista).modificaNom((String)value);
    			break;
    		case 1: 	//MODIFICAR COGNOM 1
    			value = JOptionPane.showInputDialog(null,"Cognom: ","Modificació Esportista", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    			if(value==null) return;
    			arrayEsportista.get(indexEsportista).modificaCognom((String)value);
    			break;
    		case 2:  	//MODIFICAR COGNOM 2
    			value = JOptionPane.showInputDialog(null,"Segon Cognom: ","Modificació Esportista", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    			if(value==null) return;
    			arrayEsportista.get(indexEsportista).modificaCognom2((String)value);    
    			break;
    		case 3:   	//MODIFICAR SEXE
    			value = JOptionPane.showInputDialog(null,"Sexe: ","Modificació Esportista", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    			if(value==null) return;
    			index2 = (String)value;
    			arrayEsportista.get(indexEsportista).modificaSexe(index2.charAt(0));
    			break;
    		case 4: 	//MODIFICAR DATA NAIXEMENT
    			value = JOptionPane.showInputDialog(null,"Data Naixement(dd/mm/yyyy): ","Modificació Esportista", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    			if(value==null) return;    			
    			String resultat=(String)value;
				String[] split = resultat.split("/");
				int dia=Integer.parseInt(split[0]);
				int mes =Integer.parseInt(split[1]);
				int any=Integer.parseInt(split[2]);			
				LocalDate data = LocalDate.of(any, mes, dia);    			
    			arrayEsportista.get(indexEsportista).modificaData(data);
    			break;
    		case 5: 	//MODIFICAR DNI
    			value = JOptionPane.showInputDialog(null,"DNI: ","Modificació Esportista", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    			if(value==null) return;
    			arrayEsportista.get(indexEsportista).modificaDNI((String)value);
    			break;
    		default:
    			break;			
    	}
	}

	private static void ModificarEsportistaFederat() 
	{
		//Generem una array auxiliar per tal de treballar amb els noms dels esportistes i poguer escollir quin modificar
		ArrayList<String> nomEsportistes = new ArrayList<String>();
		for(int p=0;p<arrayEsporistaFederat.size();p++)
		{
			nomEsportistes.add(arrayEsporistaFederat.get(p).getNom());
		}
		Object[] array = nomEsportistes.toArray(new Object[nomEsportistes.size()]);
		Object seleccion = JOptionPane.showInputDialog(
				   null,
				   "Seleccioni un Esportista a Modificar",
				   "Modificació Esportista",
				   JOptionPane.QUESTION_MESSAGE,
				   null,
				   array, 
				   array[0]);
		if(seleccion==null) return;
		int indexEsportista = nomEsportistes.indexOf(seleccion);	

		String[] opcions = {"Nom", "Cognom", "Segon Cognom", "Sexe","Data Naixement","DNI","Club","Tornar"};
    	int opcio = JOptionPane.showOptionDialog(null,"Seleccioni una opció","Modificació Esportista",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,icon,opcions,null);
    	Object value;
    	String index2;
    	switch(opcio)
    	{
    		case 0: 	//MODIFICAR NOM
    			value = JOptionPane.showInputDialog(null,"Nom: ","Modificació Esportista", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    			if(value==null) return;
    			arrayEsporistaFederat.get(indexEsportista).modificaNom((String)value);
    			break;
    		case 1: 	//MODIFICAR COGNOM
    			value = JOptionPane.showInputDialog(null,"Cognom: ","Modificació Esportista", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    			if(value==null) return;
    			arrayEsporistaFederat.get(indexEsportista).modificaCognom((String)value);
    			break;
    		case 2: 	//MODIFICAR COGNOM 2
    			value = JOptionPane.showInputDialog(null,"Segon Cognom: ","Modificació Esportista", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    			if(value==null) return;
    			arrayEsporistaFederat.get(indexEsportista).modificaCognom2((String)value);
    			break;
    		case 3:  	//MODIFICAR SEXE
    			value = JOptionPane.showInputDialog(null,"Sexe: ","Modificació Esportista", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    			if(value==null) return;
    			index2 = (String)value;
    			arrayEsporistaFederat.get(indexEsportista).modificaSexe(index2.charAt(0));
    			break;
    		case 4: 	//MODIFICAR DATA_NAIXEMENT
    			value = JOptionPane.showInputDialog(null,"Data Naixement(dd/mm/yyyy): ","Modificació Esportista", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    			if(value==null) return;
    		
				String[] split = ((String)value).split("/");
				int dia=Integer.parseInt(split[0]);
				int mes =Integer.parseInt(split[1]);
				int any=Integer.parseInt(split[2]);
				LocalDate data = LocalDate.of(any, mes, dia);
    			
    			arrayEsporistaFederat.get(indexEsportista).modificaData(data);
    			break;
    		case 5: 	//MODIFICAR DNI
    			value = JOptionPane.showInputDialog(null,"DNI: ","Modificació Esportista", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    			if(value==null) return;
    			index2 = (String)value;
    			arrayEsporistaFederat.get(indexEsportista).modificaDNI((String)value);
    			break;
    		case 6: 	//MODIFICAR CLUB
    			//Agrupem els noms dels clubs per tal de poguer seleccionar comodament un nou club dels registrats
    			ArrayList<String> nomClubs = new ArrayList<String>();
    			for(int p=0;p<arrayClub.size();p++)
    			{
    				nomClubs.add(arrayClub.get(p).getNom());
    			}
    			array = nomClubs.toArray(new Object[nomClubs.size()]);
    			seleccion = JOptionPane.showInputDialog(
    					   null,
    					   "Seleccioni un Club",
    					   "Modificació Esportsita",
    					   JOptionPane.QUESTION_MESSAGE,
    					   null,
    					   array, 
    					   array[0]);
    			if(seleccion==null) return;

    			int index = nomClubs.indexOf(seleccion);
    			String aux_club=arrayClub.get(index).getNom();
    			arrayEsporistaFederat.get(indexEsportista).modificaCIub(aux_club);
    			break;	
    	}
	}
	
	private static void ModificarProva() 
	{
		Object[] array;
		Object seleccion;
		int index=2;
		int auxii=0;
		ArrayList<String> nomProves = new ArrayList<String>();
		String[] opcions = {"Marató", "Marxa Popular", "Prova10000", "Tornar"};
    	int opcio = JOptionPane.showOptionDialog(null,"Seleccioni una Prova","Menu Modificar Prova",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,icon,opcions,null);	
		switch(opcio)
		{
			case 0:
				//MODIFICAR MARATO
				for(int p=0;p<arrayMarato.size();p++)
				{
					nomProves.add(arrayMarato.get(p).getNom());
				}
				array = nomProves.toArray(new Object[nomProves.size()]);
				seleccion = JOptionPane.showInputDialog(
						   null,
						   "Seleccioni una Prova a Modificar",
						   "Modificació Prova",
						   JOptionPane.QUESTION_MESSAGE,
						   icon,
						   array, 
						   array[0]);
				if(seleccion==null) return;

				index = nomProves.indexOf(seleccion);	
				auxii=1;
				break;
			case 1:
				//MODIFICAR MARXA
				for(int p=0;p<arrayMarxaP.size();p++)
				{
					nomProves.add(arrayMarxaP.get(p).getNom());
				}
				array = nomProves.toArray(new Object[nomProves.size()]);
				seleccion = JOptionPane.showInputDialog(
						   null,
						   "Seleccioni una Prova a Modificar",
						   "Modificació Prova",
						   JOptionPane.QUESTION_MESSAGE,
						   icon,
						   array, 
						   array[0]);
				if(seleccion==null) return;

				index = nomProves.indexOf(seleccion);	
				auxii=2;
				break;
			case 2:
				//MODIFICAR PROVA
				for(int p=0;p<arrayProva10k.size();p++)
				{
					nomProves.add(arrayProva10k.get(p).getNom());
				}
				array = nomProves.toArray(new Object[nomProves.size()]);
				seleccion = JOptionPane.showInputDialog(
						   null,
						   "Seleccioni una Prova a Modificar",
						   "Modificació Prova",
						   JOptionPane.QUESTION_MESSAGE,
						   icon,
						   array, 
						   array[0]);
				if(seleccion==null) return;

				index = nomProves.indexOf(seleccion);
				auxii=3;
				break;
		}	
		
		String[] opcions2 = {"Nom", "Any", "Codi","Data","Hora Sortida","Tornar"};
    	int opcio2 = JOptionPane.showOptionDialog(null,"Seleccioni una opció","Modificació Prova",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,icon,opcions2,null);	
    	Object value;
    	
    	switch(opcio2)
    	{
    		case 0:	//MODIFICAR NOM
    			if(auxii==1)
    			{
    				value = JOptionPane.showInputDialog(null,"Nom nou: ","Modificació Nom Marató", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    				if(value==null) return;
        			arrayMarato.get(index).modificaNom((String)value);	
        		}
    			else if(auxii==2)
    			{
    				value = JOptionPane.showInputDialog(null,"Nom nou: ","Modificació Nom Marxa Popular", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    				if(value==null) return;
    				arrayMarxaP.get(index).modificaNom((String)value);

    			}
    			else if(auxii==3)
    			{
    				value = JOptionPane.showInputDialog(null,"Nom nou: ","Modificació Nom Prova10000", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    				if(value==null) return;
    				arrayProva10k.get(index).modificaNom((String)value);
    			}
       			break;
    		case 1: //MODIFICAR ANY
    			if(auxii==1)
    			{
    				value = JOptionPane.showInputDialog(null,"Any: ","Modificació Any Marató", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    				if(value==null) return;
        			arrayMarato.get(index).modificaAny((String)value);
	
    			}
    			else if(auxii==2)
    			{
    				value = JOptionPane.showInputDialog(null,"Any: ","Modificació Any Marxa Popular", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    				if(value==null) return;
    				arrayMarxaP.get(index).modificaAny((String)value);

    			}
    			else if(auxii==3)
    			{
    				value = JOptionPane.showInputDialog(null,"Any: ","Modificació Any Prova10000", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    				if(value==null) return;
    				arrayProva10k.get(index).modificaAny((String)value);
        		}
       			break;    			
    		case 2: //MODIFICAR CODI
    			if(auxii==1)
    			{
    				value = JOptionPane.showInputDialog(null,"Codi: ","Modificació Codi Marató", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    				if(value==null) return;
        			arrayMarato.get(index).modificaCodiProva((String)value);
    			}
    			else if(auxii==2)
    			{
    				value = JOptionPane.showInputDialog(null,"Codi: ","Modificació Codi Marxa Popular", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    				if(value==null) return;
    				arrayMarxaP.get(index).modificaCodiProva((String)value);        		

    			}
    			else if(auxii==3)
    			{
    				value = JOptionPane.showInputDialog(null,"Codi: ","Modificació Codi Prova10000", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    				if(value==null) return;
    				arrayProva10k.get(index).modificaCodiProva((String)value);        		
        			}
    			break;
    		case 3: //MODIFICAR DATA
    			if(auxii==1)
    			{
    				value = JOptionPane.showInputDialog(null,"Data: ","Modificació Data Marató", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    				if(value==null) return;
        			arrayMarato.get(index).modificaData((String)value);
    			}
    			else if(auxii==2)
    			{
    				value = JOptionPane.showInputDialog(null,"Data: ","Modificació Data Marxa Popular", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    				if(value==null) return;
    				arrayMarxaP.get(index).modificaData((String)value);        		

    			}
    			else if(auxii==3)
    			{
    				value = JOptionPane.showInputDialog(null,"Data: ","Modificació Data Prova10000", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    				if(value==null) return;
    				arrayProva10k.get(index).modificaData((String)value);        		

    			}
    			break;
    		case 4: //MODIFICAR HORA SORTIDA
    			if(auxii==1)
    			{
    				value = JOptionPane.showInputDialog(null,"Hora Sortida (00h00m00s): ","Modificació Hora Sortida Marató", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    				if(value==null) return;    				
    				String resultat=(String)value;
    				String[] split = resultat.split(":");
    				int hora=Integer.parseInt(split[0]);
    				int min =Integer.parseInt(split[1]);
    				int sec=Integer.parseInt(split[2]);
    				LocalTime horaSortida = LocalTime.of(hora, min,sec);
    				
    				arrayMarato.get(index).modificaHoraSortida(horaSortida);
    			}
    			else if(auxii==2)
    			{
    				value = JOptionPane.showInputDialog(null,"Hora Sortida (00h00m00s): ","Modificació Hora Sortida Marxa Popular", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    				if(value==null) return;
    				String resultat=(String)value;
    				String[] split = resultat.split(":");
    				int hora=Integer.parseInt(split[0]);
    				int min =Integer.parseInt(split[1]);
    				int sec=Integer.parseInt(split[2]);
    				LocalTime horaSortida = LocalTime.of(hora, min,sec);
 
    				arrayMarxaP.get(index).modificaHoraSortida(horaSortida);
    			}
    			else if(auxii==3)
    			{
    				value = JOptionPane.showInputDialog(null,"Hora Sortida (00h00m00s): ","Modificació Hora Sortida Prova10000", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    				if(value==null) return;
    				String resultat=(String)value;
    				String[] split = resultat.split(":");
    				int hora=Integer.parseInt(split[0]);
    				int min =Integer.parseInt(split[1]);
    				int sec=Integer.parseInt(split[2]);
    				LocalTime horaSortida = LocalTime.of(hora, min,sec);
 
    				arrayProva10k.get(index).modificaHoraSortida(horaSortida);
    			}
    			break;		
    	}
	}

	//MÈTODES DE DADES (ALTA)
	private static void AltaProva() 
	{
		String nomProva=null,codiProva=null,any = null,participants = null,index2=null,nomClub=null;Object value;
		
		String[] opcions = {"Marató", "Prova1000", "Marxa Popular","Tornar"};
    	int opcio = JOptionPane.showOptionDialog(null,"Seleccioni una opció","Alta de Prova",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,icon,opcions,null);
    	switch(opcio)
    	{
    	case 0://Marató			
			Marato marato=new Marato(nomClub, codiProva, any, participants);
			value = JOptionPane.showInputDialog(null,"Nom:  ","Alta Marató", JOptionPane.PLAIN_MESSAGE, icon,null,"");
	    	if(value==null) return;
			index2 = (String)value;
			marato.setNom(index2);
			codiProva=GeneradorCodiProva();
			marato.setCodiProva(codiProva);
			value = JOptionPane.showInputDialog(null,"Any:  ","Alta Marató", JOptionPane.PLAIN_MESSAGE, icon,null,"");
	    	if(value==null) return;
			index2 = (String)value;
			
			marato.setAny(index2);
			marato.setParticipants("100");

			value = JOptionPane.showInputDialog(null,"Data:  ","Alta Marató", JOptionPane.PLAIN_MESSAGE, icon,null,"");
			if(value==null) return;
			index2 = (String)value;
			
			marato.setData(index2);
			value = JOptionPane.showInputDialog(null,"Hora Sortida(00h00m00s):  ","Alta Marató", JOptionPane.PLAIN_MESSAGE, icon,null,"");
			if(value==null) return;
			index2 = (String)value;
			
		
			String resultat=(String)value;
			String[] split = resultat.split(":");
			int hora=Integer.parseInt(split[0]);
			int min =Integer.parseInt(split[1]);
			int sec=Integer.parseInt(split[2]);
			LocalTime horaSortida = LocalTime.of(hora, min,sec);
			marato.setHoraSortida(horaSortida);

			arrayMarato.add(marato);
			numParticipants++;
    		break;
    	case 1://Prova10000
			Prova10000 prova10k=new Prova10000(nomClub, codiProva, any, participants);
    		value = JOptionPane.showInputDialog(null,"Nom:  ","Alta Prova10000", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    		if(value==null) return;
			index2 = (String)value;
			nomProva =index2;
			prova10k.setNom(nomProva);
			codiProva=GeneradorCodiProva();
			prova10k.setCodiProva(codiProva);
			
			value = JOptionPane.showInputDialog(null,"Any:  ","Alta Prova10000", JOptionPane.PLAIN_MESSAGE, icon,null,"");
			if(value==null) return;
			index2 = (String)value;
			prova10k.setAny(index2);

			value = JOptionPane.showInputDialog(null,"Data:  ","Alta Prova10000", JOptionPane.PLAIN_MESSAGE, icon,null,"");
			if(value==null) return;
			index2 = (String)value;
			
			prova10k.setData(index2);
			
			if(arrayClub.size()!=0)
			{
				ArrayList<String> nomClubs = new ArrayList<String>();
				for(int p=0;p<arrayClub.size();p++)
				{
					nomClubs.add(arrayClub.get(p).getNom());
				}
				Object[] array = nomClubs.toArray(new Object[nomClubs.size()]);
				Object seleccion = JOptionPane.showInputDialog(
						   null,
						   "Seleccioni un Club a Amfitrio",
						   "Alta Prova",
						   JOptionPane.QUESTION_MESSAGE,
						   null,
						   array, 
						   array[0]);
				int index = nomClubs.indexOf(seleccion);		
				prova10k.setClubAnfitrio(arrayClub.get(index).getNom());
				System.out.println("Club Host --> "+arrayClub.get(index).getNom());
				value = JOptionPane.showInputDialog(null,"Hora (00h00m00s):  ","Alta Prova10000", JOptionPane.PLAIN_MESSAGE, icon,null,"");
				if(value==null) return;
				index2 = (String)value;
				resultat=(String)value;
				split = resultat.split(":");
				hora=Integer.parseInt(split[0]);
				min =Integer.parseInt(split[1]);
				sec=Integer.parseInt(split[2]);
				horaSortida = LocalTime.of(hora, min, sec);
				System.out.println(horaSortida);
				prova10k.setHoraSortida(horaSortida);
				
				value = JOptionPane.showInputDialog(null,"Ubicació:  ","Alta Prova10000", JOptionPane.PLAIN_MESSAGE, icon,null,"");
				if(value==null) return;
				index2 = (String)value;
				prova10k.setUbicacio(index2);
				prova10k.setParticipants("150");
				arrayProva10k.add(prova10k);
				numParticipants++;
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Avans afegeix algun Club","Alta Prova10000",JOptionPane.INFORMATION_MESSAGE,iconError);	
			}

    		break;
    	case 2:
    		MarxaPopular marxa=new MarxaPopular(nomClub, codiProva, any, participants);

			value = JOptionPane.showInputDialog(null,"Nom:  ","Alta Marxa Popular", JOptionPane.PLAIN_MESSAGE, icon,null,"");
			if(value==null) return;
			index2 = (String)value;
			marxa.setNom(index2);
			
			codiProva=GeneradorCodiProva();
			marxa.setCodiProva(codiProva);
			
			value = JOptionPane.showInputDialog(null,"Any:  ","Alta Marxa Popular", JOptionPane.PLAIN_MESSAGE, icon,null,"");
			if(value==null) return;
			index2 = (String)value;
			marxa.setAny(index2);
			
			marxa.setParticipants("100");
			value = JOptionPane.showInputDialog(null,"Ubicació:  ","Alta Marxa Popular", JOptionPane.PLAIN_MESSAGE, icon,null,"");
			if(value==null) return;
			index2 = (String)value;
			marxa.setUbicacio(index2);
			value = JOptionPane.showInputDialog(null,"Data:  ","Alta Marxa Popular", JOptionPane.PLAIN_MESSAGE, icon,null,"");
			if(value==null) return;
			index2 = (String)value;
			marxa.setData(index2);
			
			value = JOptionPane.showInputDialog(null,"Hora (00h00m00s):  ","Alta Marxa Popular", JOptionPane.PLAIN_MESSAGE, icon,null,"");
			if(value==null) return;
			resultat=(String)value;
			split = resultat.split(":");
			hora=Integer.parseInt(split[0]);
			min =Integer.parseInt(split[1]);
			sec=Integer.parseInt(split[2]);
			horaSortida = LocalTime.of(hora, min,sec);			
			marxa.sethSortida(horaSortida);
			arrayMarxaP.add(marxa);
			numParticipants++;
    		break;
    	case 3:
    		break;    	
    	}
	}

	private static void AltaClub() 
	{
		Object value;
		value = JOptionPane.showInputDialog(null,"Nom: ","Alta Club", JOptionPane.PLAIN_MESSAGE, icon,null,"");
		if(value==null) return;
		String nomClub = (String)value;
		value = JOptionPane.showInputDialog(null,"Població: ","Alta Club", JOptionPane.PLAIN_MESSAGE, icon,null,"");
		if(value==null) return;
		String poblacioClub = (String)value;
		value = JOptionPane.showInputDialog(null,"Codi Postal: ","Alta Club", JOptionPane.PLAIN_MESSAGE, icon,null,"");
		if(value==null) return;
		String codiPostal = (String)value;


		int codiClub = creaComprovaCodiClub();
		value = JOptionPane.showInputDialog(null,"Any Fundació: ","Alta Club", JOptionPane.PLAIN_MESSAGE, icon,null,"");
		if(value==null) return;
		String anyFundacioClub = (String)value;
		
		numClubs++;
		
		Club e = new Club(nomClub, poblacioClub, codiPostal, anyFundacioClub, codiClub);
		arrayClub.add(e);
		JOptionPane.showMessageDialog(null,"             Club Afegit!","Afegir Club",JOptionPane.INFORMATION_MESSAGE,iconTick);	
	}

	private static void AltaEsportista() 
	{
		Object value;
		value = JOptionPane.showInputDialog(null,"DNI: ","Alta Esportista", JOptionPane.PLAIN_MESSAGE, icon,null,"");
		if(value==null) return;
		String dni = (String)value;
		if(dni.contentEquals("")) {System.out.println("Error!"); return;}
			//ComprovaDniRepetitEnElsObjectes
		value = JOptionPane.showInputDialog(null,"Data NaixementI: ","Alta Esportista", JOptionPane.PLAIN_MESSAGE, icon,null,"");
		if(value==null) return;				
		
		if(((String)value).contentEquals("")) {System.out.println("Error!"); return;}
		String[] split = ((String)value).split("/");
		int dia=Integer.parseInt(split[0]);
		int mes =Integer.parseInt(split[1]);
		int any=Integer.parseInt(split[2]);
		LocalDate data = LocalDate.of(any, mes, dia);
		
		value = JOptionPane.showInputDialog(null,"Nom Esportista: ","Alta Esportista", JOptionPane.PLAIN_MESSAGE, icon,null,"");
		if(value==null) return;
		String nom = (String)value;
		if(nom.contentEquals("")) {System.out.println("Error!"); return;}

		value = JOptionPane.showInputDialog(null,"Cognoms Esportista: ","Alta Esportista", JOptionPane.PLAIN_MESSAGE, icon,null,"");
		if(value==null) return;
		String cog = (String)value;
		if(cog.contentEquals("")) {System.out.println("Error!"); return;}

		String[] cognoms = cog.split(" ");	
		String cognom=cognoms[0]; 
		String cognom2=cognoms[1];
		value = JOptionPane.showInputDialog(null,"Sexe(H/D): ","Alta Esportista", JOptionPane.PLAIN_MESSAGE, icon,null,"");
		if(value==null) return;
		String aux = (String)value;
		if(aux.contentEquals("")) {System.out.println("Error!"); return;}
		char sexe =aux.charAt(0); 
		int option = JOptionPane.showConfirmDialog(null, "Vol federar-se?", "Alta Esportista",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,icon);
		if (option == JOptionPane.YES_OPTION) 
		{
			ArrayList<String> nomClubs = new ArrayList<String>();
			for(int p=0;p<arrayClub.size();p++)
			{
				nomClubs.add(arrayClub.get(p).getNom());
			}			
				if(nomClubs.size()!=0)
				{
					Object[] array = nomClubs.toArray(new Object[nomClubs.size()]);
					Object seleccion = JOptionPane.showInputDialog(
							   null,
							   "Seleccioni un Club a Federar-se",
							   "Alta Esportista",
							   JOptionPane.QUESTION_MESSAGE,
							   icon,
							   array, 
							   array[0]);
					String club = (String) (seleccion);
					
				
					int codiFederat=creaComprovaCodiFederat();	//Comprovar i tal
					EsportistaFederat e = new EsportistaFederat(nom, cognom, cognom2, sexe, data, dni, club, (String.valueOf(codiFederat)));
					arrayEsporistaFederat.add(e);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"   Avans afegeix algun Club","Alta Esportista",JOptionPane.INFORMATION_MESSAGE,iconError);	

				}
				numEsportistes++;
		}
		else
		{
			Esportista e = new Esportista(nom, cognom, cognom2, sexe, data, dni);
			arrayEsportista.add(e);
		}
	}
		

	//MÈTODES DE DADES (CONSULTA)
	private static void ConsultaProva() 
	{
		String[] options = new String[] {"Marató", "Marxa Popular","Prova 10000"};
	    int response = JOptionPane.showOptionDialog(null, "Escull un tipus de Prova a Consultar", "Consulta Prova",
	        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
	        icon, options, options[0]);
	    if(response==0)
	    {
	    	showMarato();
	    }
	    else if(response==1)
	    {
	    	showMarxaPopular();
	    }
	    else if(response==2)
	    {
	    	showProva10k();
	    }
	    else
	    	MenuMain();
	}
	
	private static  void ConsultaEsportista() 
	{
			String[] options = new String[] {"Federats", "No Federats"};
		    int response = JOptionPane.showOptionDialog(null, "Escull un tipus d'Esportista a Consultar", "Consulta Esportista",
		        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
		        icon, options, options[0]);
		    if(response==0)
		    {
		    	showEsportistesFederats();
		    }
		    else if(response==1)
		    {
		    	showEsportistes();
		    }
		    else
		    {
		    	MenuMain();
		    }			
	}	

	private static  void ConsultaClub() 
	{		
		if(arrayClub.size()!=0)
		{
			showClub();
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Afegeix avans algun Club","Consulta Club",JOptionPane.INFORMATION_MESSAGE,iconError);	
		}
	}
		
	//MÈTODES DE INSCRIPCIO 
	private static void InscripcioProva10k(int x) 
	{
		try {                        
			ArrayList<String> nomProves = new ArrayList<String>();
			for(int p=0;p<arrayProva10k.size();p++)
			{
				nomProves.add(arrayProva10k.get(p).getNom());
			}
			Object[] array2 = nomProves.toArray(new Object[nomProves.size()]);
			Object seleccion2 = JOptionPane.showInputDialog(
					   null,
					   "Seleccioni la Marató",
					   "Inscripció Prova",
					   JOptionPane.QUESTION_MESSAGE,
					   icon,
					   array2, 
					   array2[0]);
			if(seleccion2==null) return;
			x = nomProves.indexOf(seleccion2);
			
			int dorsal=creaComprovaDorsal();
			LocalTime tempsProva = LocalTime.of(0, 0, 0);
			
			if(arrayEsporistaFederat.size()!=0)
			{
				ArrayList<String> nomEsportistes = new ArrayList<String>();
				for(int p=0;p<arrayEsporistaFederat.size();p++)
				{
					nomEsportistes.add(arrayEsporistaFederat.get(p).getNom());
				}

				Object[] array = nomEsportistes.toArray(new Object[nomEsportistes.size()]);
				Object seleccion = JOptionPane.showInputDialog(
						   null,
						   "Seleccioni un Esportista a Inscriure's",
						   "Inscripció Prova",
						   JOptionPane.QUESTION_MESSAGE,
						   icon,
						   array, 
						   array[0]);
				if(seleccion==null) return;

				int indexNomEsportista = nomEsportistes.indexOf(seleccion);	
				LocalDate data=arrayEsporistaFederat.get(indexNomEsportista).getDataNaixement();
							
							
				LocalDate absolut= LocalDate.of(1969, 12, 31); LocalDate vetera= LocalDate.of(1969, 12, 21); 
				String categoria="";
				
				if(data.isBefore(absolut)) categoria="Absolut";		
				if(data.isAfter(vetera)) categoria="Veterà";
				
		    	Participant nouParticipant =new Participant( 
		    			arrayEsporistaFederat.get(indexNomEsportista).getNom(),
		    			arrayEsporistaFederat.get(indexNomEsportista).getCognom1(),
		    			arrayEsporistaFederat.get(indexNomEsportista).getCognom2(),
		    			arrayEsporistaFederat.get(indexNomEsportista).getSexe(), 
		    			arrayEsporistaFederat.get(indexNomEsportista).getDataNaixement(), 
		    			arrayEsporistaFederat.get(indexNomEsportista).getDni(),
		    			categoria,"",
		    			dorsal,tempsProva, "");
		    	nouParticipant.setCategoria(categoria);
		    	nouParticipant.setDorsal(dorsal);
		    	System.out.println("nouParticipant.setDorsal(dorsal) --> dorsal: "+dorsal);
		    	nouParticipant.setTempsProva(tempsProva);
		    	nouParticipant.setClub(arrayEsporistaFederat.get(indexNomEsportista).getClub());
		    	nouParticipant.setCodiProva(arrayProva10k.get(x).getCodiProva());
		    	arrayParticipantF.add(nouParticipant);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Afegeix avans algun Esportista","Inscripció Prova10000",JOptionPane.INFORMATION_MESSAGE,iconError);	
			}
			
		}catch(Exception e) 
		{JOptionPane.showMessageDialog(null,"Error Inscrivint Prova10k","Inscripció Prova10000",JOptionPane.INFORMATION_MESSAGE,iconError);	}
	}

	private static void InscripcioMarxaP(int x) 
	{
		try {						
			ArrayList<String> nomClubs = new ArrayList<String>();
			for(int p=0;p<arrayMarxaP.size();p++)
			{
				nomClubs.add(arrayMarxaP.get(p).getNom());
			}
			Object[] array2 = nomClubs.toArray(new Object[nomClubs.size()]);
			Object seleccion2 = JOptionPane.showInputDialog(null, "Seleccioni la Marxa Popular", "Inscripció Prova", JOptionPane.QUESTION_MESSAGE, icon, array2,  array2[0]);
			if(seleccion2==null) return;

			x = nomClubs.indexOf(seleccion2);
			Random rand = new Random();
			int dorsal=rand.nextInt(100);
			LocalTime tempsProva = LocalTime.of(0, 0,0);
			
			
			if(arrayEsportista.size()!=0)
			{
				ArrayList<String> nomEsportistes = new ArrayList<String>();
				
				for(int p=0;p<arrayEsportista.size();p++)
				{
					nomEsportistes.add(arrayEsportista.get(p).getNom());
				}
		    	

				Object[] array = nomEsportistes.toArray(new Object[nomEsportistes.size()]);
				Object seleccion = JOptionPane.showInputDialog(
						   null,
						   "Seleccioni un Esportista a Inscriure's",
						   "Inscripció Prova",
						   JOptionPane.QUESTION_MESSAGE,
						   icon,
						   array, 
						   array[0]);
				if(seleccion==null) return;

				int indexNomEsportista = nomEsportistes.indexOf(seleccion);	
				
				LocalDate data=arrayEsportista.get(indexNomEsportista).getDataNaixement();
				
				
				LocalDate absolut= LocalDate.of(1969, 12, 31); LocalDate vetera= LocalDate.of(1969, 12, 21); 
				String categoria="";
				
				if(data.isBefore(absolut)) categoria="Absolut";		
				if(data.isAfter(vetera)) categoria="Veterà";
				
		    	Participant nouParticipant =new Participant(
		    			arrayEsportista.get(indexNomEsportista).getNom(),
		    			arrayEsportista.get(indexNomEsportista).getCognom1(),
		    			arrayEsportista.get(indexNomEsportista).getCognom2(),
		    			arrayEsportista.get(indexNomEsportista).getSexe(), 
		    			arrayEsportista.get(indexNomEsportista).getDataNaixement(), 
		    			arrayEsportista.get(indexNomEsportista).getDni(),
		    			categoria,"",
		    			dorsal,tempsProva, "");
		    	nouParticipant.setCategoria(categoria);
		    	nouParticipant.setDorsal(dorsal);
		    	nouParticipant.setTempsProva(tempsProva);
		    	nouParticipant.setCodiProva(arrayMarxaP.get(x).getCodiProva());
		    	arrayParticipant.add(nouParticipant);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Afegeix avans algun Esportista","Inscripció Marxa Popular",JOptionPane.INFORMATION_MESSAGE,iconError);	
			}
		}catch(Exception e) 
		{JOptionPane.showMessageDialog(null,"Error Inscrivint Marxa PP","Inscripció Marxa Popular",JOptionPane.INFORMATION_MESSAGE,iconError);	}
	}

	private static void InscripcioMarato(int x) 
	{
		try {                             //MARATO
			ArrayList<String> nomClubs = new ArrayList<String>();
			for(int p=0;p<arrayMarato.size();p++)
			{
				nomClubs.add(arrayMarato.get(p).getNom());
			}
			Object[] array2 = nomClubs.toArray(new Object[nomClubs.size()]);
			Object seleccion2 = JOptionPane.showInputDialog(
					   null,
					   "Seleccioni la Marató",
					   "Inscripció Prova",
					   JOptionPane.QUESTION_MESSAGE,
					   icon,
					   array2, 
					   array2[0]);
			if(seleccion2==null) return;
			x = nomClubs.indexOf(seleccion2);
			
			int dorsal=creaComprovaDorsal();
			LocalTime tempsProva = LocalTime.of(0, 0, 0);
			
			if(arrayEsporistaFederat.size()!=0)
			{
				ArrayList<String> nomEsportistes = new ArrayList<String>();
				for(int p=0;p<arrayEsporistaFederat.size();p++)
				{
					nomEsportistes.add(arrayEsporistaFederat.get(p).getNom());
				}

				Object[] array = nomEsportistes.toArray(new Object[nomEsportistes.size()]);
				Object seleccion = JOptionPane.showInputDialog(
						   null,
						   "Seleccioni un Esportista a Inscriure's",
						   "Inscripció Prova",
						   JOptionPane.QUESTION_MESSAGE,
						   icon,
						   array, 
						   array[0]);
				if(seleccion==null) return;

				int indexNomEsportista = nomEsportistes.indexOf(seleccion);	
				LocalDate data=arrayEsporistaFederat.get(indexNomEsportista).getDataNaixement();
							
							
				LocalDate absolut= LocalDate.of(1969, 12, 31); LocalDate vetera= LocalDate.of(1969, 12, 21); 
				String categoria="";
				
				if(data.isBefore(absolut)) categoria="Absolut";		
				if(data.isAfter(vetera)) categoria="Veterà";
				
		    	Participant nouParticipant =new Participant( 
		    			arrayEsporistaFederat.get(indexNomEsportista).getNom(),
		    			arrayEsporistaFederat.get(indexNomEsportista).getCognom1(),
		    			arrayEsporistaFederat.get(indexNomEsportista).getCognom2(),
		    			arrayEsporistaFederat.get(indexNomEsportista).getSexe(), 
		    			arrayEsporistaFederat.get(indexNomEsportista).getDataNaixement(), 
		    			arrayEsporistaFederat.get(indexNomEsportista).getDni(),
		    			categoria,"",
		    			dorsal,tempsProva, "");
		    	nouParticipant.setCategoria(categoria);
		    	nouParticipant.setDorsal(dorsal);
		    	System.out.println("nouParticipant.setDorsal(dorsal) --> dorsal: "+dorsal);
		    	nouParticipant.setTempsProva(tempsProva);
		    	nouParticipant.setClub(arrayEsporistaFederat.get(indexNomEsportista).getClub());
		    	nouParticipant.setCodiProva(arrayMarato.get(x).getCodiProva());
		    	arrayParticipantF.add(nouParticipant);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Afegeix avans algun Esportista","Inscripció Marató",JOptionPane.INFORMATION_MESSAGE,iconError);	
			}
			
		}catch(Exception e) 
		{JOptionPane.showMessageDialog(null,"Error Inscrivint Marató","Inscripció Marató",JOptionPane.INFORMATION_MESSAGE,iconError);	}
	}

	private static  void LlistaInscripcio() 
	{		
		if((arrayParticipant.size()!=0)||(arrayParticipantF.size()!=0))
		{
			String[] options = new String[] {"Participants Federats", "Participants No Federats"};
		    int response = JOptionPane.showOptionDialog(null, "Escull un tipus de Participant a Consultar", "Consulta Esportista",
		        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
		        icon, options, options[0]);
		    if(response==0)
		    {
		    	showInscripcionsParticipantsFederats();
		    	//arrayParticipantF.get(0).MostrarDades();
		    }
		    else if(response==1)
		    {
		    	showInscripcionsParticipants();
		    	//arrayParticipant.get(0).MostrarDades();
		    }
		    else
		    {
		    	MenuMain();
		    }			
		}
		else
			JOptionPane.showMessageDialog(null,"Realitza alguna Inscripció","Llistar Inscripcions",JOptionPane.INFORMATION_MESSAGE,iconError);	

	}

	private static void AnulacioProva() 
	{
		System.out.println(arrayParticipant.size());
		System.out.println(arrayParticipantF.size());

		if((arrayParticipant.size()!=0)||(arrayParticipantF.size()!=0))
		{
			Object[] array;
			Object seleccion;
			int index=2;
			ArrayList<String> nomParticipantsInscrits = new ArrayList<String>();

			for(int p=0;p<arrayParticipant.size();p++)
			{
			
				if(arrayParticipant.size()>0)
				{
					nomParticipantsInscrits.add(arrayParticipant.get(p).getNom());
				}
			}
			for(int p=0;p<arrayParticipantF.size();p++)
			{
				if(arrayParticipantF.size()>0)
				{
					nomParticipantsInscrits.add(arrayParticipantF.get(p).getNom());
				}
			}
			
			array = nomParticipantsInscrits.toArray(new Object[nomParticipantsInscrits.size()]);
			
			seleccion = JOptionPane.showInputDialog(
					   null,
					   "Seleccioni un Participant",
					   "Anulació Inscripció",
					   JOptionPane.QUESTION_MESSAGE,
					   null,
					   array, 
					   array[0]);
			if(seleccion==null) return;

			index = nomParticipantsInscrits.indexOf(seleccion);	
			String nomPart=nomParticipantsInscrits.get(index);
			

			for(int p=0;p<arrayParticipantF.size();p++)
			{
				if(nomPart.compareTo(arrayParticipantF.get(p).getNom())==0)
				{
					arrayParticipantF.remove(p);
					System.out.println(nomPart+" deleted successfully");
				}

			}
			for(int p=0;p<arrayParticipant.size();p++)
			{
				if(nomPart.compareTo(arrayParticipant.get(p).getNom())==0)
				{
					arrayParticipant.remove(p);
					System.out.println(nomPart+" deleted successfully");
				}
			}
		}
		else
			JOptionPane.showMessageDialog(null,"Realitza avans una Inscripció","Anul·lar Inscripcions",JOptionPane.INFORMATION_MESSAGE,iconError);	

		
	}
 	
	//MÈTODES PER EL MENU PROVES (CLASSIFICACIO)

	private static void showClasificacioSexe() 
	{
		
	}

	private static void showClasificacioTotal() 
	{		
		String[] columnes = {"Posició","Dorsal","Nom","Sexe","Categoria","Temps"};
		
		//Emplenar la taula
		int aux_I=0;				
		Object[][] files = new Object[arrayParticipantF.size()+arrayParticipant.size()][6];
		JTable table = new JTable(files, columnes);
		for (int j = 0; j < arrayParticipantF.size(); j++) 
	    {	
			arrayParticipantF.get(j).showClassificacio(files,aux_I,j+1);
	        aux_I++;
	    }
		
		for (int j = 0; j < arrayParticipant.size(); j++) 
	    {	
			arrayParticipant.get(j).showClassificacio(files,aux_I,j+1);
	        aux_I++;
	    }
		
		//Personalitzar-la
		PersonalitzarJTable(table);
		JScrollPane jsp = new JScrollPane(table);
		 jsp.setPreferredSize(new Dimension (900, 400));
		JOptionPane.showMessageDialog(null,jsp,"CLASSIFICACIÓ PROVA ("+arrayParticipantF.size()+")",JOptionPane.INFORMATION_MESSAGE,icon);
	}

	private static void showClasificacioCategoria() 
	{
		Object[][] files = new Object[arrayParticipantF.size()][6];
		String[] columnes = {"Posició","Dorsal","Nom","Sexe","Categoria","Temps"};
		JTable table = new JTable(files, columnes);
		//Emplenar la taula
		int aux_I=0;				
		
		for (int j = 0; j < arrayParticipantF.size(); j++) 
	    {	
			arrayParticipantF.get(j).showClassificacio(files,aux_I,j);
	        aux_I++;	       
	    }
		
		//Personalitzar-la
		PersonalitzarJTable(table);
		JScrollPane jsp = new JScrollPane(table);
		 jsp.setPreferredSize(new Dimension (900, 400));
		JOptionPane.showMessageDialog(null,jsp,"CLASSIFICACIÓ PROVA ("+arrayParticipantF.size()+")",JOptionPane.INFORMATION_MESSAGE,icon);
	}

	
	//MÈTODES MENU DE PROVES (ENTRAR TEMPS)
	private static void tempsArribadaParticipant(Object[] array, Object seleccion, int index) 
	{
		try 
		{
			ArrayList<String> nomParticipants = new ArrayList<String>();
			Duration tempsRecorregut;
			for(int p=0;p<arrayParticipant.size();p++)
			{
				nomParticipants.add(arrayParticipant.get(p).getNom());
			}
			for(int p=0;p<arrayParticipantF.size();p++)
			{
				nomParticipants.add(arrayParticipantF.get(p).getNom());
			}
			
			array = nomParticipants.toArray(new Object[nomParticipants.size()]);
			seleccion = JOptionPane.showInputDialog(
					   null,
					   "Seleccioni un Participant",
					   "Entrada Temps Participant",
					   JOptionPane.QUESTION_MESSAGE,
					   icon,
					   array, 
					   array[0]);
			if(seleccion==null) return;
			index = nomParticipants.indexOf(seleccion);	
			String nomPart=nomParticipants.get(index);
			

			for(int p=0;p<arrayParticipantF.size();p++)
			{
				if(nomPart.compareTo(arrayParticipantF.get(p).getNom())==0)
				{
					Object value = JOptionPane.showInputDialog(null,"Hora d'arribada (00h00m00s): ","Entrada Temps Prova 10000", JOptionPane.PLAIN_MESSAGE, icon,null,"");
					if(value==null) return;    			
					String resultat=(String)value;
					String[] split = resultat.split(":");
					int hora=Integer.parseInt(split[0]);
					int min =Integer.parseInt(split[1]);
					int sec=Integer.parseInt(split[2]);
					LocalTime horaSortida = LocalTime.of(hora, min,sec);					
			        LocalTime HoraInicial = arrayParticipantF.get(p).getTempsProva();
			        tempsRecorregut=Duration.between(HoraInicial, horaSortida);
			        finalTime = tempsRecorregut.toHoursPart()+":"+tempsRecorregut.toMinutesPart()+":"+tempsRecorregut.toSecondsPart();
			        arrayParticipantF.get(p).setTempsProva2(finalTime);
			        //System.out.println("hora inicial: "+HoraInicial+"\nhora sortida: "+horaSortida);
			       // System.out.println("temps rec: "+finalTime);
				}
				 	
			}
			for(int p=0;p<arrayParticipant.size();p++)
			{
				if(nomPart.compareTo(arrayParticipant.get(p).getNom())==0)
				{
					Object value = JOptionPane.showInputDialog(null,"Hora d'arribada (00h00m00s): ","Entrada Temps Prova 10000", JOptionPane.PLAIN_MESSAGE, icon,null,"");
					if(value==null) return;
	    			String resultat=(String)value;
					String[] split = resultat.split(":");
					int hora=Integer.parseInt(split[0]);
					int min =Integer.parseInt(split[1]);
					int sec=Integer.parseInt(split[2]);
					LocalTime horaSortida = LocalTime.of(hora, min,sec);					
			        LocalTime HoraInicial = arrayParticipantF.get(p).getTempsProva();
			        tempsRecorregut=Duration.between(HoraInicial, horaSortida);
			        finalTime = tempsRecorregut.toHoursPart()+":"+tempsRecorregut.toMinutesPart()+":"+tempsRecorregut.toSecondsPart();
			        arrayParticipant.get(p).setTempsProva2(finalTime);
			        System.out.println("hora inicial: "+HoraInicial+"\nhora sortida: "+horaSortida);
			        System.out.println("temps rec: "+finalTime);
				}
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			JOptionPane.showMessageDialog(null,"Afegeix avans un Participant","Entrada Arribada Participant",JOptionPane.INFORMATION_MESSAGE,iconError);

		}
		
	}

	private static void tempsProva(int opcio, ArrayList<String> nomProves, Object[] array, Object seleccion, int index) 
	{
		String[] opcions2 = {"Marató", "Marxa Popular", "Prova10000", "Tornar"};
    	opcio = JOptionPane.showOptionDialog(null,"Seleccioni una Prova","Entrada Temps Prova",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,icon,opcions2,null);	
    	
    	switch(opcio)
		{
			case 0: //MARATÓ
				if(arrayMarato.size()!=0)
				{
					for(int p=0;p<arrayMarato.size();p++)
					{
						nomProves.add(arrayMarato.get(p).getNom());
					}
					array = nomProves.toArray(new Object[nomProves.size()]);
					seleccion = JOptionPane.showInputDialog(
							   null,
							   "Seleccioni una Prova",
							   "Entrada Temps Prova Marató",
							   JOptionPane.QUESTION_MESSAGE,
							   icon,
							   array, 
							   array[0]);
					if(seleccion==null) return;

					index = nomProves.indexOf(seleccion);	
					Object value = JOptionPane.showInputDialog(null,"Temps Sortida (00h00m00s): ","Entrada Temps Prova Marató", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    				if(value==null) return;
    				String resultat=(String)value;
    				String[] split = resultat.split(":");
    				int hora=Integer.parseInt(split[0]);
    				int min =Integer.parseInt(split[1]);
    				int sec = 0;
    				LocalTime horaSortida;
    				if(split.length>2) 
    				{
    					sec=Integer.parseInt(split[2]);
        				horaSortida = LocalTime.of(hora, min,sec);					

    				}
    				else
    				{
        				horaSortida = LocalTime.of(hora, min,00);					

    				}
    				
    				arrayParticipantF.get(index).modificaHoraSortida(horaSortida);

				}
				else
					JOptionPane.showMessageDialog(null,"Afegeix avans una Marató","Entrada Temps Marató",JOptionPane.INFORMATION_MESSAGE,iconError);							
				break;
			case 1: //MARXA POPULAR
				if(arrayMarxaP.size()!=0)
				{
					for(int p=0;p<arrayMarxaP.size();p++)
					{
						nomProves.add(arrayMarxaP.get(p).getNom());
					}
					array = nomProves.toArray(new Object[nomProves.size()]);
					seleccion = JOptionPane.showInputDialog(
							   null,
							   "Seleccioni una Prova",
							   "Entrada Temps Prova Marxa",
							   JOptionPane.QUESTION_MESSAGE,
							   icon,
							   array, 
							   array[0]);
					if(seleccion==null) return;

					index = nomProves.indexOf(seleccion);	
					Object value = JOptionPane.showInputDialog(null,"Temps Sortida (00h00m00s): ","Entrada Temps Prova Marxa", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    				if(value==null) return;
    				String resultat=(String)value;
    				String[] split = resultat.split(":");
    				int hora=Integer.parseInt(split[0]);
    				int min =Integer.parseInt(split[1]);
    				int sec=Integer.parseInt(split[2]);
    				LocalTime horaSortida = LocalTime.of(hora, min,sec);
 
    				arrayMarxaP.get(index).modificaHoraSortida(horaSortida);
				}
				else
					JOptionPane.showMessageDialog(null,"Afegeix avans una Marató","Entrada Temps Marató",JOptionPane.INFORMATION_MESSAGE,iconError);
				break;
			case 2: //PROVA 10K
				if(arrayProva10k.size()!=0)
				{
					for(int p=0;p<arrayProva10k.size();p++)
					{
						nomProves.add(arrayProva10k.get(p).getNom());
					}
					array = nomProves.toArray(new Object[nomProves.size()]);
					seleccion = JOptionPane.showInputDialog(
							   null,
							   "Seleccioni una Prova",
							   "Entrada Temps Prova 10000",
							   JOptionPane.QUESTION_MESSAGE,
							   icon,
							   array, 
							   array[0]);
					if(seleccion==null) return;

					index = nomProves.indexOf(seleccion);	
					Object value = JOptionPane.showInputDialog(null,"Temps Sortida (00h00m00s): ","Entrada Temps Prova 10000", JOptionPane.PLAIN_MESSAGE, icon,null,"");
    				if(value==null) return;
    				String resultat=(String)value;
    				String[] split = resultat.split(":");
    				int hora=Integer.parseInt(split[0]);
    				int min =Integer.parseInt(split[1]);
    				int sec=Integer.parseInt(split[2]);
    				LocalTime horaSortida = LocalTime.of(hora, min,sec);
 
    				arrayProva10k.get(index).modificaHoraSortida(horaSortida);
				}
				else
					JOptionPane.showMessageDialog(null,"Afegeix avans una Prova 10000","Entrada Temps Prova 10000",JOptionPane.INFORMATION_MESSAGE,iconError);
				break;
			case 3:
				break;
		}
	}

	
	//Mètode per a carregar les dades que es guarden en els arxius.
	//S'encarrega de crear els objectes que en la sessió anteriore s van crear
	//MÈTODES AUXILIARS
	private static  void LoadData() 
	{
		System.out.print("--loading esportista data [");
		//LOAD ESPORTISTES DATA
		try {
				Scanner lector = new Scanner(esportistes);
				//Si el lector troba algo a l'arxiu
				if(lector.hasNext())
				{
					numEsportistes=lector.nextInt();		 //Recull el nombre de assignatures
					lector.nextLine();
					//Bucle per anar llegint i troçejant cada linia, guardant les dades que ens interessa a les arrays adecuades	
					for(int p=0;p<numEsportistes;p++)
					{
						String linia=lector.nextLine();
						String[] parts = linia.split("#");
						
						String resultat=(String)parts[4];
						String[] split = resultat.split("-");
						int dia=Integer.parseInt(split[2]);
						int mes =Integer.parseInt(split[1]);
						int any=Integer.parseInt(split[0]);
						LocalDate data = LocalDate.of(any, mes, dia);

						Esportista e = new Esportista(parts[0], parts[1], parts[2], parts[5].charAt(0), data, parts[3]);

						
						arrayEsportista.add(e);
					}
					System.out.print(arrayEsportista.size()+"] --\n");
				}
				
				lector.close();
			}
			catch(FileNotFoundException e)
			{
				System.out.println("NULL]");
			}
			catch(Exception e)
			{
				System.out.println("Error loading esportsita data --> "+e);
			}
		
		System.out.print("--loading esportistaF data [");
		//LOAD ESPORTISTES DATA
		try {
				Scanner lector = new Scanner(esportistesF);
				//Si el lector troba algo a l'arxiu
				if(lector.hasNext())
				{
					numEsportistes=lector.nextInt();		 //Recull el nombre de assignatures
					lector.nextLine();
					
					//Bucle per anar llegint i troçejant cada linia, guardant les dades que ens interessa a les arrays adecuades	
					for(int p=0;p<numEsportistes;p++)
					{
						String linia=lector.nextLine();
						String[] parts = linia.split("#");
						String resultat=(String)parts[4];
						String[] split = resultat.split("-");
						int dia=Integer.parseInt(split[2]);
						int mes =Integer.parseInt(split[1]);
						int any=Integer.parseInt(split[0]);
						LocalDate data = LocalDate.of(any, mes, dia);
						EsportistaFederat e = new EsportistaFederat(parts[0], parts[1], parts[2], parts[7].charAt(0),data,parts[3],parts[6],parts[5]);
						arrayEsporistaFederat.add(e);
					}
					System.out.print(arrayEsporistaFederat.size()+"] --\n");

				}
				
				lector.close();
			}
			catch(FileNotFoundException e)
			{
				System.out.println("NULL]");
			}
			catch(Exception e)
			{
				System.out.println("Error loading esportsitaF data --> "+e);
			}
				
		System.out.print("--loading club data [");
		//LOAD CLUB DATA
		try {
			Scanner lector = new Scanner(club);
			//Si el lector troba algo a l'arxiu
			if(lector.hasNext())
			{
				numClubs=lector.nextInt();		 //Recull el nombre de assignatures
				lector.nextLine();
				
				//Bucle per anar llegint i troçejant cada linia, guardant les dades que ens interessa a les arrays adecuades	
				for(int p=0;p<numClubs;p++)
				{
					String linia=lector.nextLine();
					String[] parts = linia.split("#");
					
					arrayClub.add(new Club(parts[0], parts[1], parts[2], parts[4], Integer.parseInt(parts[3])));
				}	
				System.out.print(arrayClub.size()+"] --\n");

			}
			
			lector.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("NULL]");
		}
		catch(Exception e)
		{
			System.out.println("Error loading club data --> "+e);
		}		
					
		System.out.print("--loading Marato data [");
		try {
			Scanner lector = new Scanner(marato);
			//Si el lector troba algo a l'arxiu
			if(lector.hasNext())
			{
				numProves=lector.nextInt();		 //Recull el nombre de assignatures
				lector.nextLine();
				numParticipants+=numProves;
				
				//Bucle per anar llegint i troçejant cada linia, guardant les dades que ens interessa a les arrays adecuades	
				for(int p=0;p<numProves;p++)
				{
					String linia=lector.nextLine();
					String[] parts = linia.split("#");					
					Marato ep = new Marato(parts[0],parts[1],parts[2],parts[5]);	
					
					String resultat=(String)parts[3];
    				String[] split = resultat.split(":");
    				int hora=Integer.parseInt(split[0]);
    				int min =Integer.parseInt(split[1]);
    				int sec = 0;
    				LocalTime horaSortida;
    				if(split.length>2) 
    				{
    					sec=Integer.parseInt(split[2]);
        				horaSortida = LocalTime.of(hora, min,sec);					

    				}
    				else
    				{
        				horaSortida = LocalTime.of(hora, min,00);					

    				}
    				ep.setHoraSortida(horaSortida);
					ep.setData(parts[4]);
					arrayMarato.add(ep);
				}
				System.out.print(arrayMarato.size()+"] --\n");
			}
						
			lector.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("NULL]");
		}
		catch(Exception e)
		{
			System.out.println("Error loading Marato data --> "+e);
		}
		
		System.out.print("--loading Marxa PP data [");
		try {
			Scanner lector = new Scanner(marxaP);
			//Si el lector troba algo a l'arxiu
			if(lector.hasNext())
			{
				numProves=lector.nextInt();		 //Recull el nombre de assignatures
				lector.nextLine();
				numParticipants+=numProves;
				
				//Bucle per anar llegint i troçejant cada linia, guardant les dades que ens interessa a les arrays adecuades	
				for(int p=0;p<numProves;p++)
				{
					String linia=lector.nextLine();
					String[] parts = linia.split("#");					
					MarxaPopular ep = new MarxaPopular(parts[0],parts[1],parts[2],parts[3]);	
					
    				String resultat=(String)parts[6];
    				String[] split = resultat.split(":");
    				int hora=Integer.parseInt(split[0]);
    				int min =Integer.parseInt(split[1]);
    				int sec = 0;
    				LocalTime horaSortida;
    				if(split.length>2) 
    				{
    					sec=Integer.parseInt(split[2]);
        				horaSortida = LocalTime.of(hora, min,sec);					

    				}
    				else
    				{
        				horaSortida = LocalTime.of(hora, min,00);					

    				}
 
					
					ep.setUbicacio(parts[4]);
					ep.setData(parts[5]);
					ep.sethSortida(horaSortida);
					arrayMarxaP.add(ep);
				}
				System.out.print(arrayMarxaP.size()+"] --\n");
			}			
			lector.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("NULL]");
		}
		catch(Exception e)
		{
			System.out.println("Error loading Marxa data --> "+e);
		}
		
		System.out.print("--loading Prova10k data [");
		try {
			Scanner lector = new Scanner(prova10k);
			//Si el lector troba algo a l'arxiu
			if(lector.hasNext())
			{
				numProves=lector.nextInt();		 //Recull el nombre de assignatures
				lector.nextLine();
				numParticipants+=numProves;

				//Bucle per anar llegint i troçejant cada linia, guardant les dades que ens interessa a les arrays adecuades	
				for(int p=0;p<numProves;p++)
				{
					String linia=lector.nextLine();
					String[] parts = linia.split("#");					
					Prova10000 ep = new Prova10000(parts[0],parts[1],parts[2],parts[3]);	
    				String resultat=parts[7];

    				String[] split = resultat.split(":");
    				int hora=Integer.parseInt(split[0]);
    				int min =Integer.parseInt(split[1]);
    				int sec = 0;
    				LocalTime horaSortida;
    				if(split.length>2) 
    				{
    					sec=Integer.parseInt(split[2]);
        				horaSortida = LocalTime.of(hora, min,sec);					

    				}
    				else
    				{
        				horaSortida = LocalTime.of(hora, min,00);					

    				}
    		
					ep.setUbicacio(parts[4]);
					ep.setHoraSortida(horaSortida);
					ep.setData(parts[6]);

					ep.setClubAnfitrio(parts[5]);
					arrayProva10k.add(ep);
				}
				System.out.print(arrayProva10k.size()+"] --\n");
			}
			
			lector.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("NULL]");
		}
		catch(Exception e)
		{
			System.out.println("Error loading Prova10k data --> "+e);
		}
		
		System.out.print("--loading participants data [");
		try {
			Scanner lector = new Scanner(participant);
			//Si el lector troba algo a l'arxiu
			if(lector.hasNext())
			{
				numProves=lector.nextInt();		 //Recull el nombre de assignatures
				lector.nextLine();
				//Bucle per anar llegint i troçejant cada linia, guardant les dades que ens interessa a les arrays adecuades	
				for(int p=0;p<numProves;p++)
				{
					String linia=lector.nextLine();
					String[] parts = linia.split("#");		
					String resultat=(String)parts[4];
					String[] split = resultat.split("-");
					int dia=Integer.parseInt(split[2]);
					int mes =Integer.parseInt(split[1]);
					int any=Integer.parseInt(split[0]);
					LocalDate data = LocalDate.of(any, mes, dia);
					Participant e = new Participant(parts[0],parts[1],parts[2],parts[3].charAt(0),
							data,parts[5],parts[6],"",Long.parseLong(parts[7]),
							null, parts[9]);
					
					resultat=(String)parts[8];
					split = resultat.split(":");
    				int hora=Integer.parseInt(split[0]);
    				int min =Integer.parseInt(split[1]);
    				int sec = 0;
    				LocalTime horaSortida;
    				if(split.length>2) 
    				{
    					sec=Integer.parseInt(split[2]);
        				horaSortida = LocalTime.of(hora, min,sec);					

    				}
    				else
    				{
        				horaSortida = LocalTime.of(hora, min,00);					

    				}
					
					arrayParticipant.add(e);
					arrayParticipant.get(p).setCategoria(parts[6]);
					arrayParticipant.get(p).setCodiProva(parts[9]);
					arrayParticipant.get(p).setTempsProva(horaSortida);
				}
				
				System.out.print(arrayParticipant.size()+"] --\n");				
			}					
			lector.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("NULL]");
		}
		catch(Exception e)
		{
			System.out.println("Error loading participants --> "+e);
		}
		
		System.out.print("--loading participants F data [");
		try {
			Scanner lector = new Scanner(participantF);
			//Si el lector troba algo a l'arxiu
			if(lector.hasNext())
			{
				numProves=lector.nextInt();		 //Recull el nombre de assignatures
				lector.nextLine();
				//Bucle per anar llegint i troçejant cada linia, guardant les dades que ens interessa a les arrays adecuades	
				for(int p=0;p<numProves;p++)
				{
					String linia=lector.nextLine();
					String[] parts = linia.split("#");
					String resultat=(String)parts[4];
					String[] split = resultat.split("-");
					int dia=Integer.parseInt(split[2]);
					int mes =Integer.parseInt(split[1]);
					int any=Integer.parseInt(split[0]);
					
					LocalDate data = LocalDate.of(any, mes, dia);
					Participant e = new Participant(parts[0],parts[1],parts[2],parts[3].charAt(0),
							data,parts[5],parts[6],parts[7],0,
							null, parts[10]);
					
					arrayParticipantF.add(e);
					resultat=(String)parts[9];

					split = resultat.split(":");
    				int hora=Integer.parseInt(split[0]);
    				int min =Integer.parseInt(split[1]);
    				int sec = 0;
    				LocalTime horaSortida;
    				if(split.length>2) 
    				{
    					sec=Integer.parseInt(split[2]);
        				horaSortida = LocalTime.of(hora, min,sec);					

    				}
    				else
    				{
        				horaSortida = LocalTime.of(hora, min,00);					

    				}

					arrayParticipantF.get(p).setCategoria(parts[6]);
					arrayParticipantF.get(p).setCodiProva(parts[10]);
					arrayParticipantF.get(p).setClub(parts[7]);
					arrayParticipantF.get(p).setDorsal(Long.parseLong(parts[8]));
					arrayParticipantF.get(p).setTempsProva(horaSortida);
				}
		
				System.out.print(arrayParticipantF.size()+"] --\n");
				
			}						
			lector.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("NULL]");
		}
		catch(Exception e)
		{
			System.out.println("Error loading participants F data --> "+e);
		}
	}
																	
	//Mètode per a guardar les dades locals a els arxius per tal de poguer rescatar la informació en la seguent 
	//sessió utilitzant el LoadData.
	private static void SaveData()
	{
			//Guardant Esportistes
			try {
				PrintStream escriptor = new PrintStream(esportistes);
				System.out.println("\n\nGuardant els esportistes ("+arrayEsportista.size()+")");
				escriptor.println(arrayEsportista.size());
				for(int p=0;p<arrayEsportista.size();p++)
				{
					escriptor.print(arrayEsportista.get(p).getNom());
					escriptor.print("#");
					escriptor.print(arrayEsportista.get(p).getCognom1());
					escriptor.print("#");
					escriptor.print(arrayEsportista.get(p).getCognom2());
					escriptor.print("#");
					escriptor.print(arrayEsportista.get(p).getDni());
					escriptor.print("#");
					escriptor.print(arrayEsportista.get(p).getDataNaixement());
					escriptor.print("#");
					escriptor.print(arrayEsportista.get(p).getSexe()+"\n");
				}				
				escriptor.close();
			}
			catch(Exception e) 
			{System.out.println("Error Guardant els Esportistes "+e);}
		
			//Guardant Esportistes Federats
			try {
				PrintStream escriptor = new PrintStream(esportistesF);
				System.out.println("Guardant els esportistesF ("+arrayEsporistaFederat.size()+")");
				escriptor.println(arrayEsporistaFederat.size());
				for(int p=0;p<arrayEsporistaFederat.size();p++)
				{
					escriptor.print(arrayEsporistaFederat.get(p).getNom());
					escriptor.print("#");
					escriptor.print(arrayEsporistaFederat.get(p).getCognom1());
					escriptor.print("#");
					escriptor.print(arrayEsporistaFederat.get(p).getCognom2());
					escriptor.print("#");
					escriptor.print(arrayEsporistaFederat.get(p).getDni());
					escriptor.print("#");
					escriptor.print(arrayEsporistaFederat.get(p).getDataNaixement());
					escriptor.print("#");
					escriptor.print(arrayEsporistaFederat.get(p).getCodiFederat());
					escriptor.print("#");
					escriptor.print(arrayEsporistaFederat.get(p).getClub());
					escriptor.print("#");
					escriptor.print(arrayEsporistaFederat.get(p).getSexe()+"\n");
				}				
				escriptor.close();
			}
			catch(Exception e) 
			{System.out.println("Error Guardant els EsportistesF "+e);}
		

		
		//Guardant Club
		try {
			PrintStream escriptor = new PrintStream(club);
			System.out.println("Guardant els Clubs ("+arrayClub.size()+")");
			escriptor.println(arrayClub.size());
			for(int p=0;p<arrayClub.size();p++)
			{
				escriptor.print(arrayClub.get(p).getNom());
				escriptor.print("#");
				escriptor.print(arrayClub.get(p).getPoblacio());
				escriptor.print("#");
				escriptor.print(arrayClub.get(p).getCodiPostal());
				escriptor.print("#");
				escriptor.print(arrayClub.get(p).getCodiClub());
				escriptor.print("#");
				escriptor.print(arrayClub.get(p).getAnyFede()+"\n");
			}				
			escriptor.close();
		}
		catch(Exception e) 
		{System.out.println("Error Guardant els clubs "+e);}
		
		//GUARDANT PROVES
			//Guardant Maratons
		try {
			PrintStream escriptor = new PrintStream(marato);
			System.out.println("Guardant les Maratons ("+arrayMarato.size()+")");
			escriptor.println(arrayMarato.size());
			for(int p=0;p<arrayMarato.size();p++)
			{
				escriptor.print(arrayMarato.get(p).getNom());
				escriptor.print("#");
				escriptor.print(arrayMarato.get(p).getCodiProva());
				escriptor.print("#");
				escriptor.print(arrayMarato.get(p).getAny());
				escriptor.print("#");
				escriptor.print(arrayMarato.get(p).getHoraSortida());
				escriptor.print("#");
				escriptor.print(arrayMarato.get(p).getData());
				escriptor.print("#");
				escriptor.print(arrayMarato.get(p).getParticipants()+"\n");
			}				
			escriptor.close();
		}
		catch(Exception e) 
		{System.out.println("Error Guardant les maratons "+e);}
		
		
			//Guardant Prova10000
		try {
			PrintStream escriptor = new PrintStream(prova10k);
			System.out.println("Guardant Proves10K ("+arrayProva10k.size()+")");
			escriptor.println(arrayProva10k.size());
			for(int p=0;p<arrayProva10k.size();p++)
			{
				escriptor.print(arrayProva10k.get(p).getNom());
				escriptor.print("#");
				escriptor.print(arrayProva10k.get(p).getCodiProva());
				escriptor.print("#");
				escriptor.print(arrayProva10k.get(p).getAny());
				escriptor.print("#");
				escriptor.print(arrayProva10k.get(p).getParticipants());
				escriptor.print("#");
				escriptor.print(arrayProva10k.get(p).getUbicacio());
				escriptor.print("#");
				escriptor.print(arrayProva10k.get(p).getClubAnfitrio());
				escriptor.print("#");
				escriptor.print(arrayProva10k.get(p).getData());
				escriptor.print("#");
				escriptor.print((arrayProva10k.get(p).getHoraSortida())
						/*+(arrayProva10k.get(p).getHoraSortida().getSecond()*/+"\n");
			}				
			escriptor.close();
		}
		catch(Exception e) 
		{System.out.println("Error Guardant les Proves 10K "+e);}
		
		//Guardant MarxaPopular
		try {
			PrintStream escriptor = new PrintStream(marxaP);
			System.out.println("Guardant Marxes PPs ("+arrayMarxaP.size()+")");
			escriptor.println(arrayMarxaP.size());
			for(int p=0;p<arrayMarxaP.size();p++)
			{
				escriptor.print(arrayMarxaP.get(p).getNom());
				escriptor.print("#");
				escriptor.print(arrayMarxaP.get(p).getCodiProva());
				escriptor.print("#");
				escriptor.print(arrayMarxaP.get(p).getAny());
				escriptor.print("#");
				escriptor.print(arrayMarxaP.get(p).getParticipants());
				escriptor.print("#");
				escriptor.print(arrayMarxaP.get(p).getUbicacio());
				escriptor.print("#");
				escriptor.print(arrayMarxaP.get(p).getData());
				escriptor.print("#");
				escriptor.print(arrayMarxaP.get(p).gethSortida()+"\n");
			}				
			escriptor.close();
		}
		catch(Exception e) 
		{System.out.println("Error Guardant les Marxes PPs "+e);}
		
			//Guardant Participants
				try {
					PrintStream escriptor = new PrintStream(participant);
					System.out.println("Guardant els Participants ("+arrayParticipant.size()+")");
					escriptor.println(arrayParticipant.size());
					for(int p=0;p<arrayParticipant.size();p++)
					{
						escriptor.print(arrayParticipant.get(p).getNom());
						escriptor.print("#");
						escriptor.print(arrayParticipant.get(p).getCognom1());
						escriptor.print("#");
						escriptor.print(arrayParticipant.get(p).getCognom2());
						escriptor.print("#");
						escriptor.print(arrayParticipant.get(p).getSexe());
						escriptor.print("#");
						escriptor.print(arrayParticipant.get(p).getDataNaixement());
						escriptor.print("#");
						escriptor.print(arrayParticipant.get(p).getDni());
						escriptor.print("#");
						escriptor.print(arrayParticipant.get(p).getCategoria());
						escriptor.print("#");
						escriptor.print(arrayParticipant.get(p).getDorsal());
						System.out.println("dorsal (saving prticipant) "+arrayParticipant.get(p).getDorsal());
						escriptor.print("#");
						escriptor.print(arrayParticipant.get(p).getTempsProva());
						escriptor.print("#");
						escriptor.print(arrayParticipant.get(p).getCodiProva()+"\n");
						
					}				
					escriptor.close();
				}
				catch(Exception e) 
				{System.out.println("Error Guardant els Participants Normals"+e);}
				
				
				//Guardant ParticipantsF
				try {
					PrintStream escriptor = new PrintStream(participantF);
					System.out.println("Guardant els ParticipantsF ("+arrayParticipantF.size()+")");
					escriptor.println(arrayParticipantF.size());
					for(int p=0;p<arrayParticipantF.size();p++)
					{
						escriptor.print(arrayParticipantF.get(p).getNom());
						escriptor.print("#");
						escriptor.print(arrayParticipantF.get(p).getCognom1());
						escriptor.print("#");
						escriptor.print(arrayParticipantF.get(p).getCognom2());
						escriptor.print("#");
						escriptor.print(arrayParticipantF.get(p).getSexe());
						escriptor.print("#");
						escriptor.print(arrayParticipantF.get(p).getDataNaixement());
						escriptor.print("#");
						escriptor.print(arrayParticipantF.get(p).getDni());
						escriptor.print("#");
						escriptor.print(arrayParticipantF.get(p).getCategoria());
						escriptor.print("#");
						escriptor.print(arrayParticipantF.get(p).getClub());
						escriptor.print("#");
						escriptor.print(arrayParticipantF.get(p).getDorsal());
						escriptor.print("#");
						escriptor.print(arrayParticipantF.get(p).getTempsProva());
						escriptor.print("#");
						escriptor.print(arrayParticipantF.get(p).getCodiProva()+"\n");
												
					}				
					escriptor.close();
				}
				catch(Exception e) 
				{System.out.println("Error Guardant els Participants F"+e);}												
	}

	private static  void Arxiu() 
	{
		 try 
		 {
		      arxiuInicial = new File("practicaFile.txt");
		      esportistes = new File("esportistes.txt");
		      proves = new File("proves.txt");
		      club = new File("club.txt");
		      participant = new File("participant.txt");
		      participantF = new File("participantf.txt");
		      clasificacio = new File("clasificacioF.txt");
		      clasificacioF = new File("clasificacio.txt");

		      marato=new File("marato.txt");
		      prova10k=new File("prova10k.txt");
		      marxaP=new File("marxaP.txt");
		      esportistesF=new File("esportistesF.txt");
		      if (arxiuInicial.createNewFile()){firstTime=true;} 
		      else  firstTime=false;
		 } 
		 catch (IOException e) 
		 {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
	}
	
	private static void PersonalitzarJTable(JTable table) 
	{
		DefaultTableCellRenderer defaultRenderer = (DefaultTableCellRenderer) table.getDefaultRenderer(Object.class);
		JTableHeader header = table.getTableHeader();
		defaultRenderer.setHorizontalAlignment(JLabel.CENTER);		     
		defaultRenderer.setBackground(Color.LIGHT_GRAY);		
        header.setForeground(Color.yellow);
        header.setBackground(Color.black);
        header.setFont(new Font("Microsoft JhengHei", Font.BOLD,16));
        table.setGridColor(Color.BLACK);
	}

	private static void showEsportistes() 
	{
		if(arrayEsportista.size()!=0)
		{
			Object[][] files = new Object[arrayEsportista.size()][6];
			String[] columnes = {"Nom","1r Cognom","2n Cognom","DNI","DataNaix","Sexe"};
			JTable table = new JTable(files, columnes);
			//Emplenar la taula
			int aux_I=0;	
			for (int j = 0; j < arrayEsportista.size(); j++) 
	        {	
	        	arrayEsportista.get(j).show(files,aux_I);
	            aux_I++;
	        }
			
			//Personalitzar-la
			PersonalitzarJTable(table);
			JScrollPane jsp = new JScrollPane(table);
			 jsp.setPreferredSize(new Dimension (900, 400));
			JOptionPane.showMessageDialog(null,jsp,"LLISTAR ESPORTISTES FED ("+arrayEsportista.size()+")",JOptionPane.INFORMATION_MESSAGE,icon);
		}
		else
			JOptionPane.showMessageDialog(null,"Afegeix avans algun Esportista!","Consulta Esportista",JOptionPane.INFORMATION_MESSAGE,iconError);
				
	}

	private static void showEsportistesFederats() 
	{
		if(arrayEsporistaFederat.size()!=0)
		{
			Object[][] files = new Object[arrayEsporistaFederat.size()][8];
			String[] columnes = {"Nom","1r Cognom","2n Cognom","DNI","DataNaix","codi Federat","club","Sexe"};
			JTable table = new JTable(files, columnes);
			//Emplenar la taula
			int aux_I=0;	
			for (int j = 0; j < arrayEsporistaFederat.size(); j++) 
	        {	
	        	arrayEsporistaFederat.get(j).show(files,aux_I);
	            aux_I++;
	        }
			
			//Personalitzar-la
			PersonalitzarJTable(table);
			JScrollPane jsp = new JScrollPane(table);
			 jsp.setPreferredSize(new Dimension (900, 400));
			JOptionPane.showMessageDialog(null,jsp,"LLISTAR ESPORTISTES FED ("+arrayEsporistaFederat.size()+")",JOptionPane.INFORMATION_MESSAGE,icon);
		}
		else
			JOptionPane.showMessageDialog(null,"Afegeix avans algun Esportista!","Consulta Esportista",JOptionPane.INFORMATION_MESSAGE,iconError);	

				
		
	}
	
	private static void showProva10k() 
	{
		if(arrayProva10k.size()!=0)
		{
			Object[][] files = new Object[arrayProva10k.size()][8];
			String[] columnes = {"Nom","Codi Prova","Any","Participants","Ubicació","Club Host","Data","Hora Sortida"};
			JTable table = new JTable(files, columnes);
			int aux_I=0;	
			for (int j = 0; j < arrayProva10k.size(); j++) 
		    {	
				arrayProva10k.get(j).show(files,aux_I);
		        aux_I++;
		    }
			PersonalitzarJTable(table);
			JScrollPane jsp = new JScrollPane(table);
			 jsp.setPreferredSize(new Dimension (900, 400));
			JOptionPane.showMessageDialog(null,jsp,"LLISTAR PROVA 10K ("+arrayProva10k.size()+")",JOptionPane.INFORMATION_MESSAGE,icon);	
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Afegeix avans alguna Prova10000!","Consulta Prova10000",JOptionPane.INFORMATION_MESSAGE,iconError);	
		}
	}
	
	private static void showClub() 
	{
		Object[][] files = new Object[arrayClub.size()][5];
		String[] columnes = {"Nom","Ubicació","Codi Postal","Codi Club","Any Fundació"};
		JTable table = new JTable(files, columnes);
		//Emplenar la taula
		int aux_I=0;				
		
		for (int j = 0; j < arrayClub.size(); j++) 
	    {	
			arrayClub.get(j).show(files,aux_I);
	        aux_I++;
	    }
		
		//Personalitzar-la
		PersonalitzarJTable(table);
		JScrollPane jsp = new JScrollPane(table);
		 jsp.setPreferredSize(new Dimension (900, 400));
		JOptionPane.showMessageDialog(null,jsp,"LLISTAR CLUBS ("+arrayClub.size()+")",JOptionPane.INFORMATION_MESSAGE,icon);
	}

	private static void showMarxaPopular() 
	{
		if(arrayMarxaP.size()!=0)
		{
			Object[][] files = new Object[arrayMarxaP.size()][7];
			String[] columnes = {"Nom","Codi Prova","Any","Participants","Ubicació","Data","Hora Sortida"};
			JTable table = new JTable(files, columnes);
			//Emplenar la taula
			int aux_I=0;	
			for (int j = 0; j < arrayMarxaP.size(); j++) 
		    {	
				arrayMarxaP.get(j).show(files,aux_I);
		        aux_I++;
		    }			
			PersonalitzarJTable(table);
			JScrollPane jsp = new JScrollPane(table);
			 jsp.setPreferredSize(new Dimension (900, 400));
			JOptionPane.showMessageDialog(null,jsp,"LLISTAR MARXES POPULARS ("+arrayMarxaP.size()+")",JOptionPane.INFORMATION_MESSAGE,icon);	
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Afegeix avans alguna Marxa Popular!","Consulta Marxa Popular",JOptionPane.INFORMATION_MESSAGE,iconError);	
		}
	}

	private static void showMarato() 
	{
		if(arrayMarato.size()!=0)
		{
			Object[][] files = new Object[arrayMarato.size()][6];
			String[] columnes = {"Nom","Codi Prova","Any","Participants","Data","Hora Sortida"};
			JTable table = new JTable(files, columnes);
			//Emplenar la taula
			int aux_I=0;	
			for (int j = 0; j < arrayMarato.size(); j++) 
		    {	
				arrayMarato.get(j).show(files,aux_I);		    	
		        aux_I++;
		    }
			//Personalitzar-la
			PersonalitzarJTable(table);
			JScrollPane jsp = new JScrollPane(table);
			 jsp.setPreferredSize(new Dimension (900, 400));
			JOptionPane.showMessageDialog(null,jsp,"LLISTAR MARATONS ("+arrayMarato.size()+")",JOptionPane.INFORMATION_MESSAGE,icon);	
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Afegeix avans alguna Marató!","Consulta Marató",JOptionPane.INFORMATION_MESSAGE,iconError);	
		}
	}
	
	private static void showInscripcionsParticipants() 
	{
		if(arrayParticipant.size()!=0)
		{
			Object[][] files = new Object[arrayParticipant.size()][10];
			String[] columnes = {"Codi Prova","Nom","Cognom","Sexe","Data Naix","DNI","Categoria","Dorsal","Temps"};
			JTable table = new JTable(files, columnes);
			//Emplenar la taula			//;getCategoria();this.getClub();this.getDorsal();this.getTempsProva();this.getCodiProva();
			int aux_I=0;	
			for (int j = 0; j < arrayParticipant.size(); j++) 
		    {	
				arrayParticipant.get(j).show(files,aux_I);
		        aux_I++;
		    }
			//Personalitzar-la
			PersonalitzarJTable(table);
			JScrollPane jsp = new JScrollPane(table);
			 jsp.setPreferredSize(new Dimension (900, 400));
			JOptionPane.showMessageDialog(null,jsp,"LLISTAR Participants ("+arrayParticipant.size()+")",JOptionPane.INFORMATION_MESSAGE,icon);
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Realitza avans una Inscripció","Llistar Inscripcions",JOptionPane.INFORMATION_MESSAGE,iconError);	
		}
	}

	private static void showInscripcionsParticipantsFederats() 
	{
		if(arrayParticipantF.size()!=0)
		{
			Object[][] files = new Object[arrayParticipantF.size()][10];
			String[] columnes = {"Codi Prova","Nom","Cognom","Sexe","Data Naix","DNI","Categoria","Club","Dorsal","Temps"};
			JTable table = new JTable(files, columnes);
			//Emplenar la taula			//;getCategoria();this.getClub();this.getDorsal();this.getTempsProva();this.getCodiProva();
			int aux_I=0;	
			for (int j = 0; j < arrayParticipantF.size(); j++) 
		    {	
				arrayParticipantF.get(j).showFed(files,aux_I);
		        aux_I++;
		    }
			//Personalitzar-la
			PersonalitzarJTable(table);
			JScrollPane jsp = new JScrollPane(table);
			 jsp.setPreferredSize(new Dimension (900, 400));
			JOptionPane.showMessageDialog(null,jsp,"LLISTAR ParticipantsF ("+arrayParticipantF.size()+")",JOptionPane.INFORMATION_MESSAGE,icon);
		}
		else
			JOptionPane.showMessageDialog(null,"Realitza avans una Inscripció","Llistar Inscripcions",JOptionPane.INFORMATION_MESSAGE,iconError);	

	}

	//Mètode que retrorna un codi acceptable per la prova tipus (AAAAPPPP)
	//Es forma a partir del nombre de participants actuals.
	private static String GeneradorCodiProva() 
	{
		String str = String.format("%04d", numParticipants+1);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		return (year+str);
	}
	
	//Mètode que retorna el codi de federació unic per a l'esportista federat.
	private static int creaComprovaCodiFederat() 
	{
		Random rand = new Random();
		int num = rand.nextInt(1000);
		for (int p=0;p<arrayEsporistaFederat.size();p++)
		{
			if(num==Integer.parseInt(arrayEsporistaFederat.get(p).getCodiFederat())) //Si coincideix amb algun altre, torna a generar-ne 
			{
				creaComprovaCodiFederat();
			}
		}
		return num;
	}
	
	//Mètode per a crear i comprovar el codi unic del club creat
	private static int creaComprovaCodiClub() 
	{
		Random rand = new Random();
		int num = rand.nextInt(100);
		for (int p=0;p<arrayClub.size();p++)
		{
			if(num==arrayClub.get(p).getCodiClub())
			{
				creaComprovaCodiClub();
			}
		}
		return num;
	}

	//Mètode per a crear i comprovar el dorsal unic de participant
	private static int creaComprovaDorsal() 
	{
		Random rand = new Random();
		int num = rand.nextInt(100);
		for (int p=0;p<arrayParticipantF.size();p++)
		{
			if(num==arrayParticipantF.get(p).getDorsal())
			{
				creaComprovaDorsal();
			}
		}

		return num;
	}
}