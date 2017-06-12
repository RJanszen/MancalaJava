package nl.sogyo.mancala;

public class Bakje extends BordActor {

	public Bakje() {
		actorTeller = index = 1;
		steentjes = 4;
		eigenaar = Eigenaar.SPELER1; // rfr
		buurman = new Bakje(this, eigenaar, 2);
	}

	public Bakje(int steentjes) {
		this(1, Eigenaar.SPELER1);
		this.steentjes = steentjes;
	}

	public Bakje(int indexBakje, Eigenaar speler) {
		actorTeller = 1;
		steentjes = 4;
		eigenaar = speler;
		index = indexBakje;
		if (indexBakje % 6 == 0)
			buurman = new Kalaha(this, speler, indexBakje);
		else
			buurman = new Bakje(this, eigenaar, indexBakje + 1);
	}

	public Bakje(Bakje origine, Eigenaar speler, int indexBakje) {
		actorTeller++;
		steentjes = 4;
		this.eigenaar = speler;
		index = indexBakje;

		// Zet eerst gemaakte bakje als buurman wanneer er een volledig rondje
		// is gemaakt (14 Bord Actoren verder)
		if (actorTeller == 14)
			buurman = origine;
		else if (indexBakje == 6 || indexBakje == 12)
			buurman = new Kalaha(origine, speler, indexBakje);
		else
			buurman = new Bakje(origine, speler, indexBakje + 1);
	}

	public void startBeurt() {
		if (steentjes > 0) {
			int steentjesDoorgeven = steentjes;
			steentjes = 0;
			buurman.geefDoor(steentjesDoorgeven, this);
		}
	}

	public void geefDoor(int steentjesInHand, Bakje origine) {

		// Laatste steentje krijgen met gevuld bakje -> TOEVOEGEN
		if (steentjesInHand == 1 && (steentjes > 0 || this.getOverBuurman().steentjes == 0)) {
			steentjes++;
			// Laatste steentje krijgen met leeg bakje van je eigen beurt ->
			// STELEN
		} else if (steentjesInHand == 1 && steentjes == 0 && origine.getEigenaar() == eigenaar) {
			steentjes++;
			steelSteentjes();
			steentjes = 0;
			if (Speler.spelers.size() != 0)
				Speler.spelers.get(origine.getEigenaar()).setBeurt(true);
			// Laatste steentje krijgen met leeg bakje van andere beurt ->
			// TOEVOEGEN
		} else if (steentjesInHand == 1 && steentjes == 0 && origine.getEigenaar() != eigenaar) {
			steentjes++;
			// Hand is groter dan 1 -> 1 NEMEN EN REST DOORGEVEN
		} else if (steentjesInHand > 1) {
			steentjes++;
			steentjesInHand--;
			buurman.geefDoor(steentjesInHand, origine);
		}
	}

	public Bakje getOverBuurman() {
		BordActor actor = this.getBuurman();
		int indexOverBuurman = 7 - index;
		for (int i = 0; i < 12; i++) {
			if (actor.index == indexOverBuurman && actor.getEigenaar() != this.getEigenaar()) {
				return (Bakje) actor;
			}
			actor = actor.getBuurman();
		}
		return null;
	}

	public void steelSteentjes() {

		// Zoek overbuurman en steel steentjes
		int gestolenSteentjes = getSteentjes() + getOverBuurman().getSteentjes();

		// Zoek eigen Kalaha en zet steentjes hierin
		this.getKalaha(this.eigenaar).ontvangSteentjes(gestolenSteentjes);
		this.steentjes = getOverBuurman().steentjes = 0;
	}

	@Override
	public String toString() {
		return "Bakje\t[steentjes=" + steentjes + ", eigenaar=" + eigenaar + ", index#=" + index + "]";
	}
}
