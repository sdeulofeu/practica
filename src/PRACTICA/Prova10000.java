package PRACTICA;
import java.time.LocalTime;

public class Prova10000 extends Prova
{
	private String clubAnfitrio;
	private String ubicacio;
	private String data;
	private LocalTime horaSortida;
	
	public Prova10000(String nom, String codiProva, String any, String participants) 
	{
		super(nom, codiProva, any, participants);
		this.getUbicacio();
		this.getClubAnfitrio();
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
    	files[aux_I][4] = getUbicacio();
    	files[aux_I][5] = getClubAnfitrio();
    	files[aux_I][6] = getData();
    	files[aux_I][7] = getHoraSortida();
	}

	public String getClubAnfitrio() {
		return clubAnfitrio;
	}

	public void setClubAnfitrio(String clubAnfitrio) {
		this.clubAnfitrio = clubAnfitrio;
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

	public LocalTime getHoraSortida() {
		return horaSortida;
	}

	public void setHoraSortida(LocalTime hSortida) {
		this.horaSortida = hSortida;
	}
}
