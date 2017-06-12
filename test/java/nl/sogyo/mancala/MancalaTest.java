package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class MancalaTest {
	
	
	// --- BAKJE --- \\

    @Test
    public void beurtStartenOpGevuldBakjeEnLeeghalen_telEigenStenen() {
    	Bakje testBakje = new Bakje();
    	testBakje.startBeurt();
        Assert.assertEquals(0, testBakje.getSteentjes());
    }
    
    @Test
    public void beurtStartenOpLeegBakje_telEigenStenen() {
    	Bakje testBakje = new Bakje(0);
    	testBakje.startBeurt();
    	Assert.assertEquals(0, testBakje.getSteentjes());
    }
    
    @Test
    public void beurtStartenOpLeegBakje_telStenenBuurman() {
    	Bakje testBakje = new Bakje(0);
    	testBakje.startBeurt();
    	Assert.assertEquals(4, testBakje.getBuurman().getSteentjes());
    }
    
    @Test
    public void beurtStartenOpGevuldBakjeEnDoorgevenAanBuurman_telBuurmanStenen() {
    	Bakje testBakje = new Bakje();
    	testBakje.startBeurt();
    	Assert.assertEquals(5, testBakje.getBuurman().getSteentjes());
    }
    
    @Test
    public void handMet3SteentjesDoorgeven_telEigenStenen() {
    	Bakje testBakje = new Bakje();
    	testBakje.geefDoor(3, testBakje);
    	Assert.assertEquals(5, testBakje.getSteentjes());
    }
    
    @Test
    public void handMet3SteentjesDoorgeven_telBuurmanStenen() {
    	Bakje testBakje = new Bakje();
    	testBakje.geefDoor(3, testBakje);
    	Assert.assertEquals(5, testBakje.getBuurman().getSteentjes());
    }
    
    @Test
    public void laatsteSteentjeGekregenEnNietLeeg_telBuurmanStenen() {
    	Bakje testBakje = new Bakje();
    	testBakje.geefDoor(1, testBakje);
    	Assert.assertEquals(4, testBakje.getBuurman().getSteentjes());
    }
    
    @Test
    public void laatsteSteentjeGekregenEnLeegEnEigenBeurt_telEigenStenen() {
    	Bakje testBakje = new Bakje(0);
    	testBakje.geefDoor(1, testBakje);
    	Assert.assertEquals(0, testBakje.getSteentjes());
    }
    
    @Test
    public void laatsteSteentjeGekregenEnLeegEnAnderBeurt_telEigenStenen() {
    	Bakje testBakje = new Bakje(3);
    	testBakje.geefDoor(1, testBakje);
    	Assert.assertEquals(4, testBakje.getSteentjes());
    }
    
    // --- KALAHA --- \\
    
    @Test
    public void kalaha3SteentjesDoorgevenEigenBeurt_telStenen() {
    	Kalaha testKalaha = new Kalaha();
    	testKalaha.geefDoor(3, Eigenaar.SPELER1);
    	Assert.assertEquals(1, testKalaha.getSteentjes());
    }
    
    @Test
    public void kalaha3SteentjesDoorgevenAndereBeurt_telStenen() {
    	Kalaha testKalaha = new Kalaha();
    	testKalaha.geefDoor(3, Eigenaar.SPELER2);
    	Assert.assertEquals(0, testKalaha.getSteentjes());
    }
    
    @Test
    public void kalaha3SteentjesDoorgevenAndereBeurt_telStenenBuurman() {
    	Kalaha testKalaha = new Kalaha();
    	testKalaha.geefDoor(3, Eigenaar.SPELER2);
    	Assert.assertEquals(5, testKalaha.getBuurman().getSteentjes());
    }
    
    @Test
    public void kalaha1SteentjeDoorgevenEigenBeurt_telStenen() {
    	Kalaha testKalaha = new Kalaha();
    	testKalaha.geefDoor(1, Eigenaar.SPELER1);
    	Assert.assertEquals(1, testKalaha.getSteentjes());
    }
    
    @Test
    public void kalaha1SteentjeDoorgevenEigenBeurt_telStenenBuurman() {
    	Kalaha testKalaha = new Kalaha();
    	testKalaha.geefDoor(1, Eigenaar.SPELER1);
    	Assert.assertEquals(4, testKalaha.getBuurman().getSteentjes());
    }
    
    @Test
    public void kalaha1SteentjeDoorgevenAndereBeurt_telStenen() {
    	Kalaha testKalaha = new Kalaha();
    	testKalaha.geefDoor(1, Eigenaar.SPELER2);
    	Assert.assertEquals(0, testKalaha.getSteentjes());
    }
    
    @Test
    public void kalaha1SteentjeDoorgevenAndereBeurt_telStenenBuurman() {
    	Kalaha testKalaha = new Kalaha();
    	testKalaha.geefDoor(1, Eigenaar.SPELER2);
    	Assert.assertEquals(5, testKalaha.getBuurman().getSteentjes());
    }
    
    @Test
    public void kalahaOntvang3Steentjes() {
    	Kalaha testKalaha = new Kalaha();
    	testKalaha.ontvangSteentjes(3);
    	Assert.assertEquals(3, testKalaha.getSteentjes());
    }
    
    // --- VIRTUEEL BORD / VOLGORDE --- \\
    
    @Test
    public void bakjeGeefDoor3Steentjes_telBuurmannen1en2() {
    	Bakje testBakje = new Bakje();
    	testBakje.geefDoor(3, testBakje);
    	Assert.assertEquals(5, testBakje.getBakje(Eigenaar.SPELER1, 2).getSteentjes());
    	Assert.assertEquals(5, testBakje.getBakje(Eigenaar.SPELER1, 3).getSteentjes());
    }
    
    @Test
    public void bakjeGeefDoor3Steentjes_telBuurman3() {
    	Bakje testBakje = new Bakje();
    	testBakje.geefDoor(3, testBakje);
    	Assert.assertEquals(4, testBakje.getBakje(Eigenaar.SPELER1, 4).getSteentjes());
    }
    
    @Test
    public void bakjeGeefDoor15Steentjes_telEigen() {
    	Bakje testBakje = new Bakje(1, Eigenaar.SPELER1);
    	testBakje.steentjes = 15;
    	testBakje.startBeurt();
    	Assert.assertEquals(1, testBakje.getSteentjes());
    }
    
    @Test
    public void bakjeGeefDoor14Steentjes_telEigenEnOverBuurman() {
    	Bakje testBakje = new Bakje(1, Eigenaar.SPELER1);
    	testBakje.steentjes = 13;
    	testBakje.startBeurt();
    	Assert.assertEquals(0, testBakje.getSteentjes());
    	Assert.assertEquals(0, testBakje.getOverBuurman().getSteentjes());
    }
    
    @Test
    public void bakjeGeefDoorLangsEigenKalaha_telKalaha() {
    	Bakje testBakje = new Bakje(6, Eigenaar.SPELER1);
    	testBakje.geefDoor(3, testBakje);
    	Assert.assertEquals(1, testBakje.getBuurman().getSteentjes());
    }
    
    @Test
    public void bakjeGeefDoorLangsAnderKalaha_telKalaha() {
    	Bakje testBakje = new Bakje(6, Eigenaar.SPELER1);
    	testBakje.geefDoor(8, testBakje);
    	Assert.assertEquals(0, testBakje.getKalaha(Eigenaar.SPELER2).getSteentjes());
    }
    
    // --- STELEN --- \\\
    
    @Test
    public void leegBakjeSteel_telEigen() {
    	Bakje testBakje = new Bakje(1, Eigenaar.SPELER1);
    	testBakje.getBuurman().startBeurt();
    	testBakje.geefDoor(2, testBakje);
    	Assert.assertEquals(0, testBakje.getBuurman().getSteentjes());
    }
    
    @Test
    public void leegBakjeSteel_telOverBuurman() {
    	Bakje testBakje = new Bakje(1, Eigenaar.SPELER1);
    	testBakje.getBuurman().startBeurt();
    	testBakje.geefDoor(2, testBakje);
    	Assert.assertEquals(0, testBakje.getBakje(Eigenaar.SPELER1, 2).getOverBuurman().getSteentjes());
    }
    
    @Test
    public void leegBakjeSteel_telEigenKalaha() {
    	Bakje testBakje = new Bakje(1, Eigenaar.SPELER1);
    	testBakje.getBuurman().startBeurt();
    	testBakje.geefDoor(2, testBakje);
    	Assert.assertEquals(5, testBakje.getKalaha(Eigenaar.SPELER1).getSteentjes());
    }
    
    @Test
    public void leegBakjeSteel_telAndereKalaha() {
    	Bakje testBakje = new Bakje();
    	testBakje.getBuurman().startBeurt();
    	testBakje.geefDoor(2, testBakje);
    	Assert.assertEquals(0, testBakje.getKalaha(Eigenaar.SPELER2).getSteentjes());
    }
    
    /// --- SPELER --- \\\
    
    @Test
    public void speler1KiestEigenGevuldBakje_telBakje() {
    	Speler speler1 = new Speler();
		speler1.startBeurt(Speler.bord.getBakje(Eigenaar.SPELER1, 1));
		Assert.assertEquals(0, Speler.bord.getBakje(Eigenaar.SPELER1, 1).getSteentjes());
    }
    
    @Test
    public void speler1KiestEigenGevuldBakje_checkBeurt() {
    	Speler speler1 = new Speler();
		speler1.startBeurt(Speler.bord.getBakje(Eigenaar.SPELER1, 1));
		Assert.assertFalse(speler1.getBeurt());
    }
    
    @Test
    public void speler1KiestAndereSpelerGevuldBakje_telAnderBakje() {
    	Speler speler1 = new Speler();
		speler1.startBeurt(Speler.bord.getBakje(Eigenaar.SPELER2, 1));
		Assert.assertEquals(4, Speler.bord.getBakje(Eigenaar.SPELER2, 1).getSteentjes());
    }
    
    @Test
    public void speler1KiestAndereSpelerGevuldBakje_checkBeurt() {
    	Speler speler1 = new Speler();
		speler1.startBeurt(Speler.bord.getBakje(Eigenaar.SPELER2, 1));
		Assert.assertTrue(speler1.getBeurt());
    }
    
    @Test
    public void speler1KiestKalahaGevuld_telKalaha() {
    	Speler speler1 = new Speler();
    	Speler.bord.getKalaha(Eigenaar.SPELER1).steentjes = 3;
		speler1.startBeurt(Speler.bord.getKalaha(Eigenaar.SPELER1));
		Assert.assertEquals(3, Speler.bord.getKalaha(Eigenaar.SPELER1).getSteentjes());
    }
    
    @Test
    public void speler1KiestKalaha_checkBeurt() {
    	Speler speler1 = new Speler();
    	speler1.startBeurt(Speler.bord.getKalaha(Eigenaar.SPELER1));
		Assert.assertTrue(speler1.getBeurt());
    }
    
    @Test
    public void speler1EindigtOpLeegEigen_telLeegBakjeEnOverBuurmanEnKalaha() {
    	Speler speler1 = new Speler();
    	Speler.bord.getBakje(Eigenaar.SPELER1, 1).steentjes = 1;
    	Speler.bord.getBakje(Eigenaar.SPELER1, 2).startBeurt();
		speler1.startBeurt(Speler.bord.getBakje(Eigenaar.SPELER1, 1));
		Assert.assertEquals(0, Speler.bord.getBakje(Eigenaar.SPELER1, 2).steentjes);
		Assert.assertEquals(0, Speler.bord.getBakje(Eigenaar.SPELER1, 2).getOverBuurman().steentjes);
		Assert.assertEquals(5, Speler.bord.getKalaha(Eigenaar.SPELER1).steentjes);
    }
    
    @Test
    public void speler1EindigtOpLeegEigen_checkBeurt() {
    	Speler speler1 = new Speler();
    	Speler.bord.getBakje(Eigenaar.SPELER1, 1).steentjes = 1;
    	Speler.bord.getBakje(Eigenaar.SPELER1, 2).startBeurt();
		speler1.startBeurt(Speler.bord.getBakje(Eigenaar.SPELER1, 1));
		Assert.assertTrue(speler1.getBeurt());
    }
    
    @Test
    public void speler1EindigtOpLeegEigenEnOverBuurmanIsLeeg_telEigen() {
    	Speler speler1 = new Speler();
    	Speler.bord.getBakje(Eigenaar.SPELER1, 1).steentjes = 1;
    	Speler.bord.getBakje(Eigenaar.SPELER1, 2).startBeurt();
    	Speler.bord.getBakje(Eigenaar.SPELER2, 5).steentjes = 0;
    	speler1.startBeurt(Speler.bord.getBakje(Eigenaar.SPELER1, 1));
    	Assert.assertEquals(1, Speler.bord.getBakje(Eigenaar.SPELER1, 2).getSteentjes());
    }
    
    @Test
    public void speler1Beurt_wisselBeurt_checkSpeler1en2Beurt() {
    	Speler speler1 = new Speler();
    	speler1.wisselBeurt();
    	Assert.assertFalse(speler1.getBeurt());
    	Assert.assertTrue(speler1.getTegenstander().getBeurt());
    }
    
    @Test
    public void speler1AlleBakjes4_checkEindeBeurt() {
    	Speler speler1 = new Speler();
    	Assert.assertFalse(speler1.checkLeeg());
    }
    
    @Test
    public void speler1AlleBakjesLeeg_checkEindeBeurt() {
    	Speler speler1 = new Speler();
    	BordActor bakje = speler1.getBord();
    	for (int i = 0; i < 6; i++) {
    		bakje.steentjes = 0;
    		bakje = bakje.getBuurman();
    	}
    	Assert.assertTrue(speler1.checkLeeg());
    }
    
    /// --- TESTPRINTER --- \\\
    
//    @Test
//    public void testPrinter() {
//    	//\\\\\\\\INIT + EXECUTE BELOW\\\\\\\\\\\\
//    	BordActor testBakje = new Bakje(1, Eigenaar.SPELER1);
//    	testBakje.steentjes = 1;
//    	testBakje.getBuurman().startBeurt();
//    	testBakje.startBeurt();
//    	//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
//    	System.out.println(testBakje.toString());
//    	BordActor loopBakje = testBakje.getBuurman();
//    	while (loopBakje != testBakje) {
//    		System.out.println(loopBakje.toString());
//    		loopBakje = loopBakje.getBuurman();
//    	}
//    }
}