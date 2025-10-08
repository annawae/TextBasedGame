package frames;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GameFrame extends JFrame {
	private Charakter spieler;
	JButton firstButton, secondButton, thirdButton;
	JLabel text;
	Random random = new Random();
	JLabel spielerHp;
	JLabel spielerEp;
	
	public GameFrame(Charakter spieler) {
		this.spieler = spieler;
		this.setSize(1000,650);
		this.setLayout(new BorderLayout(20,20));
		JPanel spielerAnzeige = new JPanel();
		this.add(spielerAnzeige,BorderLayout.NORTH);
		spielerAnzeige.setLayout(new BoxLayout(spielerAnzeige, BoxLayout.X_AXIS));
		
		
		JLabel spielerCharakter = new JLabel(spieler.getName() + " | ");
		spielerAnzeige.add(spielerCharakter);
		spielerCharakter.setFont(new Font("Arial", Font.BOLD, 24));
		spielerHp = new JLabel("Lebenspunkte: " + spieler.getHp() + " | ");
		spielerAnzeige.add(spielerHp);
		spielerHp.setFont(new Font("Arial", Font.PLAIN, 24));
		spielerEp = new JLabel("Erfahrung: " + spieler.getEp() + " | ");
		spielerAnzeige.add(spielerEp);
		spielerEp.setFont(new Font("Arial", Font.PLAIN, 24));
		JLabel spielerWeapon= new JLabel("Waffe: " + spieler.getWeapon() + " | ");
		spielerAnzeige.add(spielerWeapon);
		spielerWeapon.setFont(new Font("Arial", Font.PLAIN, 24));
		
		JPanel textContainer = new JPanel();
		this.add(textContainer, BorderLayout.CENTER);
		text = new JLabel("<html>Du befindest dich auf dem Dorfplatz. <br>Entscheide, was du als nächstes tun möchtest. <br>Gehe dich im Laden ausrüsten, verlasse das Dorf oder bleibe noch etwas, <br>um zu Kräften zu kommen.</html>");
		
		textContainer.add(text);
		text.setFont(new Font("Arial", Font.PLAIN, 24));
		
		JPanel buttons = new JPanel();
		this.add(buttons, BorderLayout.SOUTH);
		firstButton = new JButton("Verlasse das Dorf");
		buttons.add(firstButton);
		secondButton = new JButton("Betritt den Dorfladen");
		buttons.add(secondButton);
		thirdButton = new JButton("Ruhe dich aus.");
		buttons.add(thirdButton);
		
		firstButton.addActionListener(e -> 
			setScene("draußen")
		);
		secondButton.addActionListener(e ->
			setScene("laden")
		);
		thirdButton.addActionListener(e -> {
			if(spieler.getHp()<100) {
			spieler.setHp(spieler.getHp()+10);
			}
			else {
				text.setText("Du hast bereits maximales Leben.");
				
			}
			aktualisiereAnzeige();
		});
		
		
		
		this.setVisible(true);
	}
	
	private void setScene(String ort) {
        for (JButton b : new JButton[]{firstButton, secondButton, thirdButton}) {
            for (java.awt.event.ActionListener al : b.getActionListeners()) {
                b.removeActionListener(al);
            }
        }
        
		switch(ort) {
		case "draußen":
			text.setText("Wohin möchtest du gehen?");
			firstButton.setText("Den Wald betreten");
			secondButton.setText("Auf dem Weg bleiben");
			thirdButton.setText("Gehe zurück ins Dorf");
			firstButton.addActionListener(e -> setScene("wald"));
			secondButton.addActionListener(e -> setScene("dragon"));
			thirdButton.addActionListener(e -> setScene("dorf"));
			break;
		case "wald":
			secondButton.setVisible(true);
			thirdButton.setVisible(true);
			text.setText("Triff eine Wahl");
			firstButton.setText("Wolf bekämpfen");
			secondButton.setText("Goblins bekämpfen");
			thirdButton.setText("Gehe zurück Richtung Dorf");
			firstButton.addActionListener(e -> setScene("wolf"));
			thirdButton.addActionListener(e -> setScene("dorf"));
			secondButton.addActionListener(e -> setScene("goblins"));
			break;
		case "wolf":
			Enemy wolf = new Enemy("Wolf", 30, 1);
			text.setText("Der Wolf hat 30 Lebenspunkte.");
			firstButton.setText("Angriff!");
			secondButton.setText("Ausweichen");
			thirdButton.setText("Renn weg.");
			firstButton.addActionListener(e -> fight(wolf, spieler));
			thirdButton.addActionListener(e -> setScene("wald"));
			
		break;

		case "dorf":
			firstButton.setText("Verlasse das Dorf");
			secondButton.setText("Gehe in den Dorfladen");
			thirdButton.setText("Ruh dich aus.");
			
			firstButton.addActionListener(e -> 
			setScene("draußen")
			);
		secondButton.addActionListener(e ->
			setScene("laden")
			);
		thirdButton.addActionListener(e -> {
			if(spieler.getHp()<100) {
			spieler.setHp(spieler.getHp()+10);
			}
			else {
				text.setText("Du hast bereits maximales Leben.");
				
			}
			aktualisiereAnzeige();
		});
		text.setText("<html>Du befindest dich auf dem Dorfplatz. <br>Entscheide, was du als nächstes tun möchtest. <br>Gehe dich im Laden ausrüsten, verlasse das Dorf oder bleibe noch etwas, <br>um zu Kräften zu kommen.</html>");
		
		break;
		case "laden":
			text.setText("Im Dorfladen gibt es Waffen zu kaufen. Sieh dich um.");
			firstButton.setText("Besseres Schwert kaufen");
			secondButton.setText("Besseren Zauberstab kaufen");
			thirdButton.setText("Gehe zurück ins Dorf");
			thirdButton.addActionListener(e -> setScene("dorf"));
			break;
		case "goblins":
			Enemy goblins = new Enemy("Goblins", 60, 3);
			text.setText("Die Goblins haben zusammen 80 Lebenspunkte.");
			firstButton.setText("Angriff!");
			secondButton.setText("Ausweichen");
			thirdButton.setText("Renn weg.");
			firstButton.addActionListener(e -> fight(goblins, spieler));
			thirdButton.addActionListener(e -> setScene("wald"));
		}
	}
	
	public void fight(Enemy monster, Charakter player) {
		if(monster.getHp() > 0 && player.getHp() > 0) {
			int zahlAttack = random.nextInt(5) + player.strength;
			monster.setHp(monster.getHp()-zahlAttack);
			int zahlMonster = random.nextInt(3) + monster.strength;
			player.setHp(player.getHp()-zahlMonster);
		
		
		if(monster.getHp() <= 0) {
			text.setText("Du hast den Gegner besiegt. Du erhälst Erfahrung.");
			spieler.setEp(player.getEp()+ 20*monster.getStrength());
			aktualisiereAnzeige();
			secondButton.setVisible(false);
			thirdButton.setVisible(false);
			firstButton.setText("Gehe zurück");
			firstButton.addActionListener(e -> SwingUtilities.invokeLater(() ->  setScene("wald")));
		}
		else if(player.getHp() <= 0){
			text.setText("Du bist tot. GAME OVER!");

		}else {
		
		spielerHp.setText("Lebenspunkte: " + player.getHp() + " | ");
		text.setText("Der Wolf hat noch " + monster.getHp() + " Lebenspunkte.");
	}
	}
	}
	public void aktualisiereAnzeige() {
		spielerHp.setText("Lebenspunkte: " + spieler.getHp() + " | ");
		spielerEp.setText("Erfahrung: " + spieler.getEp() + " | ");
	}
}

