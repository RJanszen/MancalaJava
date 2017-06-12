package nl.sogyo.mancala;

import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

public class Mancala {

	JButton bakjes[] = new JButton[14];
	Speler[] spelers = new Speler[2];
	HashMap<JButton, BordActor> actors = new HashMap<JButton, BordActor>();

	public Mancala() {
		JFrame myFrame = new JFrame("Mancala 1.0");
		myFrame.setSize(500, 160);
		myFrame.setResizable(false);
		myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon("./src/bead.jpg");
		myFrame.setIconImage(img.getImage());
		myFrame.setLocationRelativeTo(null);

		newGame();
		buttons(myFrame);
	}

	private void buttons(JFrame myFrame) {

		BordActor bakje = Speler.bord;
		for (int i = 0; i < 14; i++) {
			bakjes[i] = new JButton(Integer.toString(bakje.getSteentjes()));
			actors.put(bakjes[i], bakje);
			bakje = bakje.getBuurman();
		}

		int x = 60;
		for (int i = 0; i < bakjes.length; i++) {
			if (i < 6) {
				bakjes[i].setBounds((70 + x * i), 70, 50, 50);
			} else if (i == 6) {
				bakjes[i].setBounds(430, 10, 50, 110);
				bakjes[i].setEnabled(false);
			} else if (i > 6 && i < 13) {
				bakjes[i].setBounds((70 + x * (6 - (i - 6))), 10, 50, 50);
			} else if (i == 13) {
				bakjes[i].setBounds(10, 10, 50, 110);
				bakjes[i].setEnabled(false);
			}
			myFrame.add(bakjes[i]);
		}

		for (JButton button : bakjes) {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					spelers[0].startBeurt(actors.get(button));
					spelers[1].startBeurt(actors.get(button));
					buttonUpdate(bakjes);

					checkBeurt();
					checkEind();
				}
			});
		}
		myFrame.setLayout(null);
		myFrame.setVisible(true);
	}

	public void buttonUpdate(JButton bakjes[]) {

		for (JButton button : bakjes) {
			button.setText(Integer.toString(actors.get(button).getSteentjes()));
		}
	}

	private void checkBeurt() {

		for (JButton button : bakjes) {
			if (actors.get(button).getClass() == Kalaha.class)
				continue;
			if (spelers[0].getBeurt() == true && actors.get(button).getEigenaar() == spelers[0].getSpeler()) {
				button.setEnabled(true);
				continue;
			}
			if (spelers[1].getBeurt() == true && actors.get(button).getEigenaar() == spelers[1].getSpeler()) {
				button.setEnabled(true);
				continue;
			}
			button.setEnabled(false);
		}
	}

	private void checkEind() {

		if (spelers[0].checkLeeg() == true && spelers[0].getBeurt() == true) endGame();
		if (spelers[1].checkLeeg() == true && spelers[1].getBeurt() == true) endGame();
		
	}

	private void newGame() {
		spelers[0] = new Speler();
		spelers[1] = spelers[0].getTegenstander();
	}

	private void endGame() {
		for (JButton bakje : bakjes) {
			bakje.setEnabled(false);
		}
		int speler1Score = Speler.bord.getKalaha(Eigenaar.SPELER1).getSteentjes();
		int speler2Score = Speler.bord.getKalaha(Eigenaar.SPELER2).getSteentjes();
		
		JOptionPane.showMessageDialog(null, "Speler " + ( speler1Score > speler2Score ?  1 : 2 ) + " wint!");
	}

	public static void main(String[] args) {

		new Mancala();
	}
}