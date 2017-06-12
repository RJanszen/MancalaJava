package nl.sogyo.mancala;

public class BordActor {

	protected static int actorTeller = 0;
	
	protected int steentjes;
	protected BordActor buurman;
	protected Eigenaar eigenaar;
	protected int index;
	
	public int getSteentjes() {
		return steentjes;
	}
	
	public BordActor getBuurman() {
		return buurman;
	}
	
	public Eigenaar getEigenaar() {
		return eigenaar;
	}
	
	public int getActorTeller() {
		return actorTeller;
	}
	
	public int getIndex() {
		return index;
	}
	
	public Bakje getBakje(Eigenaar speler, int index) {
		BordActor bordActor = this;
		for (int i = 0; i < 14; i++) {
			if (bordActor.index == index && bordActor.getEigenaar() == speler && bordActor.getClass() == Bakje.class) {
				return (Bakje) bordActor;
			}
			bordActor = bordActor.getBuurman();
		}
		return null;
	}
	
	public Kalaha getKalaha(Eigenaar speler) {
		BordActor bordActor = this;
		for (int i = 0; i < 14; i++) {
			if (bordActor.getEigenaar() == speler && bordActor.getClass() == Kalaha.class) {
				return (Kalaha) bordActor;
			}
			bordActor = bordActor.getBuurman();
		}
		return null;
	}
	
	public void startBeurt() {}
	public void geefDoor(int steentjesInHand, Eigenaar speler) {}
	public void geefDoor(int steentjesDoorgeven, Bakje bakje) {}
}