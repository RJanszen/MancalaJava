package nl.sogyo.mancala;

import java.util.HashMap;

public class Speler {

	public static HashMap<Eigenaar, Speler> spelers = new HashMap<Eigenaar, Speler>();
	protected static BordActor bord = new Bakje();
	private boolean beurt;
	private Eigenaar speler;
	private Speler tegenstander;
	
	public Speler() {
		beurt = true;
		this.speler = Eigenaar.SPELER1;
		bord = new Bakje();
		spelers.put(speler, this);
		tegenstander = new Speler(this, Eigenaar.SPELER2);
	}
	
	public Speler(Eigenaar speler) {
		beurt = true;
		this.speler = speler;
		spelers.put(speler, this);
		if (speler == Eigenaar.SPELER1) tegenstander = new Speler(this, Eigenaar.SPELER2);
		else tegenstander = new Speler(this, Eigenaar.SPELER1);
	}
	
	public Speler(Speler origin, Eigenaar speler) {
		beurt = false;
		this.speler = speler;
		this.tegenstander = origin;
		spelers.put(speler, this);
	}
	
	public BordActor getBord() {
		return bord;
	}
	
	public boolean getBeurt() {
		return beurt;
	}
	
	public Eigenaar getSpeler() {
		return speler;
	}
	
	public Speler getTegenstander() {
		return tegenstander;
	}
	
	public void setBeurt(boolean beurt) {
		this.beurt = beurt;
	}
	
	public void setSpeler(Eigenaar speler) {
		this.speler = speler;
	}
	
	public void nieuwSpel() {
		bord = new Bakje();
	}
	
	public void wisselBeurt() {
		this.beurt = false;
		tegenstander.beurt = true;
	}
	
	public boolean checkLeeg() {
		BordActor bakje = bord;
		for (int i = 0; i < 14; i++) {
			if (bakje.getEigenaar() == speler && bakje.getSteentjes() > 0 && bakje.getClass() != Kalaha.class) return false;
			bakje = bakje.getBuurman();
		}
		return true;
	}
	
	public void startBeurt(BordActor actor) {
		
		if (beurt == true && actor.getEigenaar() == this.speler && actor.getClass() == Bakje.class && actor.getSteentjes() != 0) {
			this.setBeurt(false);
			actor.startBeurt();
			if (this.getBeurt() == false) wisselBeurt();
		}
	}
}
