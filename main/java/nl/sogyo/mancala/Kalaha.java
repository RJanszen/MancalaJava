package nl.sogyo.mancala;

public class Kalaha extends BordActor {

	public Kalaha() {
		actorTeller++;
		steentjes = 0;
		eigenaar = Eigenaar.SPELER1;
		buurman = new Bakje(1, Eigenaar.SPELER2);
	}
	
	public Kalaha(int steentjes) {
		this();
		this.steentjes = steentjes;
	}
	
	public Kalaha(Eigenaar speler) {
		this();
		this.eigenaar = speler;
	}
	
	public Kalaha(Bakje origine, Eigenaar speler, int indexBakje) {
		actorTeller++;
		steentjes = 0;
		eigenaar = speler;
		index = indexBakje / 6;
		Eigenaar volgendBakje;
		if (eigenaar == Eigenaar.SPELER1) volgendBakje = Eigenaar.SPELER2;
		else volgendBakje = Eigenaar.SPELER1;
		if (actorTeller == 14) buurman = origine;
		else buurman = new Bakje(origine, volgendBakje, 1);
	}
	
	public void ontvangSteentjes(int steentjes) {
		this.steentjes += steentjes;
	}
	
	public void geefDoor(int steentjesInHand, Eigenaar speler) {
		Bakje origineDummy = (Bakje) this.getBakje(speler, 1);
		this.geefDoor(steentjesInHand, origineDummy);
	}
	
	public void geefDoor(int steentjesInHand, Bakje origine) {
		// Laatste steentje krijgen -> TOEVOEGEN
		if (steentjesInHand == 1 && origine.getEigenaar() == eigenaar) {
			steentjes++; // + NIEUWE BEURT
			if (!Speler.spelers.isEmpty()) Speler.spelers.get(eigenaar).setBeurt(true);
			// Meerdere steentjes krijgen -> 1 TOEVOEGEN EN REST DOORGEVEN
		} else if (steentjesInHand > 1 && origine.getEigenaar() == eigenaar) {
			steentjes++;
			steentjesInHand--;
			buurman.geefDoor(steentjesInHand, origine);
			// Willekeurig aantal steentjes krijgen van tegenstandige origine -> DOORGEVEN
		} else if (origine.getEigenaar() != eigenaar) {
			buurman.geefDoor(steentjesInHand, origine);
		}
	}
	
	@Override
	public String toString() {
		return "Kalaha\t[steentjes=" + steentjes + ", eigenaar=" + eigenaar + ", index#=" + index + "]";
	}
	
}
