package frames;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		
		
		
		this.setVisible(true);
	}
	
	private void setScene(String ort) {
        for (var b : new JButton[]{firstButton, secondButton, thirdButton}) {
            for (var al : b.getActionListeners()) {
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
		case "wald":
			text.setText("Triff eine Wahl");
			firstButton.setText("Wolf bekämpfen");
			secondButton.setText("Goblins bekämpfen");
			thirdButton.setText("Gehe zurück Richtung Dorf");
			firstButton.addActionListener(e -> setScene("wolf"));
		case "wolf":
			Enemy wolf = new Enemy("Wolf", 30, 1);
			text.setText("Der Wolf hat 30 Lebenspunkte.");
			firstButton.setText("Angriff!");
			secondButton.setText("Ausweichen");
			thirdButton.setText("Renn weg.");
			firstButton.addActionListener(e -> fight(wolf, spieler));
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
			spieler.setEp(player.getEp()+20);
		}
		else if(player.getHp() <= 0){
			text.setText("Du bist tot. GAME OVER!");

		}else {
		
		spielerHp.setText("Lebenspunkte: " + player.getHp() + " | ");
		text.setText("Der Wolf hat noch " + monster.getHp() + " Lebenspunkte.");
	}
	}
	}
}

