package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

public class GameFrame extends JFrame {
	private Charakter spieler;
	JButton firstButton, secondButton, thirdButton;
	JLabel text;
	Random random = new Random();
	JLabel spielerHp;
	JLabel spielerEp;
	JLabel spielerWeapon;
	JLabel spielerGold;
	JLabel spielerStaerke;
	
	int dracheBesiegt = 0;
	int bier = 0;
	Enemy harlunke = new Enemy("Harlunke", 100, 6);
	Enemy drache = new Enemy("Drache", 250, 15);
	
	JLabel picture = new JLabel();
	ImageIcon dorf = new ImageIcon(getClass().getResource("/grafiken/dorfplatz.png"));
	ImageIcon vorDemDorf = new ImageIcon(getClass().getResource("/grafiken/vorDemDorf.png"));
	ImageIcon wald = new ImageIcon(getClass().getResource("/grafiken/wald.png"));
	ImageIcon goblinsBild = new ImageIcon(getClass().getResource("/grafiken/goblins.png"));
	ImageIcon wolfBild = new ImageIcon(getClass().getResource("/grafiken/wolf.png"));
	ImageIcon wolfBildBW= new ImageIcon(getClass().getResource("/grafiken/wolf_bw.png"));
	ImageIcon goblinsBW= new ImageIcon(getClass().getResource("/grafiken/goblins_bw.png"));
	ImageIcon gameOver = new ImageIcon(getClass().getResource("/grafiken/gameOver.jpg"));
	ImageIcon flusstal = new ImageIcon(getClass().getResource("/grafiken/flusstal.png"));
	ImageIcon stadtMarkt = new ImageIcon(getClass().getResource("/grafiken/stadtMarkt.png"));
	ImageIcon weggabelung = new ImageIcon(getClass().getResource("/grafiken/weggabelung.png"));
	ImageIcon harlunkeBild = new ImageIcon(getClass().getResource("/grafiken/harlunke.png"));
	ImageIcon drachennest = new ImageIcon(getClass().getResource("/grafiken/drachennest.png"));
	//Stadt buttons fehlen
	
	public GameFrame(Charakter spieler) {
		this.spieler = spieler;
		this.setSize(1200,650);
		this.setLayout(new BorderLayout(20,20));

		//Hintergrundfarbe für alle Elemente erstellen:
		Color hintergrundfarbe = new Color(87, 152, 242);
		this.getContentPane().setBackground(hintergrundfarbe);
		
		
		JPanel spielerAnzeige = new JPanel();
		this.add(spielerAnzeige,BorderLayout.NORTH);
		spielerAnzeige.setLayout(new BoxLayout(spielerAnzeige, BoxLayout.X_AXIS));
		spielerAnzeige.setBackground(hintergrundfarbe);
		TitledBorder rahmen = BorderFactory.createTitledBorder("Spieler Werte");
		spielerAnzeige.setBorder(rahmen);
		rahmen.setTitleFont(new Font("HP Simplified", Font.BOLD, 22));
		
		//Umrandung mit Abstand für Spieler Werte:
		Border umrandung = BorderFactory.createMatteBorder(2,2,2,0 ,Color.BLACK); 
		Border abstand = BorderFactory.createEmptyBorder(4, 3, 4, 3);
		
		JLabel spielerCharakter = new JLabel(spieler.getName() );
		spielerAnzeige.add(spielerCharakter);
		spielerCharakter.setFont(new Font("Gill Sans", Font.BOLD, 26));
		spielerCharakter.setBorder(abstand);
		spielerHp = new JLabel("Lebenspunkte: " + spieler.getHp() );
		spielerHp.setBorder(BorderFactory.createCompoundBorder(umrandung, abstand));
		
		spielerAnzeige.add(spielerHp);
		spielerHp.setFont(new Font("HP Simplified", Font.PLAIN, 26));
		spielerEp = new JLabel("Erfahrung: " + spieler.getEp()  );
		spielerEp.setBorder(BorderFactory.createCompoundBorder(umrandung, abstand));
		
		spielerAnzeige.add(spielerEp);
		spielerEp.setFont(new Font("HP Simplified", Font.PLAIN, 26));
		spielerWeapon= new JLabel("Waffe: " + spieler.getWeapon() );
		spielerAnzeige.add(spielerWeapon);
		spielerWeapon.setFont(new Font("HP Simplified", Font.PLAIN, 26));
		
		spielerWeapon.setBorder(BorderFactory.createCompoundBorder(umrandung, abstand));
		spielerGold = new JLabel("Gold:" + spieler.getGold());
		spielerGold.setFont(new Font("HP Simplified", Font.PLAIN, 26));

		spielerStaerke = new JLabel("Stärke: " + spieler.getStrength());
		spielerStaerke.setFont(new Font("HP Simplified", Font.PLAIN, 26));
		spielerStaerke.setBorder(BorderFactory.createCompoundBorder(umrandung, abstand));
		spielerAnzeige.add(spielerStaerke);
		spielerAnzeige.add(spielerGold);
		//Extra Umrandung für Gold damit am Ende ein Strich vorhanden ist
		spielerGold.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK), abstand ));
		
		
		
		JPanel contentContainer = new JPanel();
		this.add(contentContainer, BorderLayout.CENTER);
		contentContainer.setBackground(hintergrundfarbe);
		
		JPanel textContainer = new JPanel();
		contentContainer.add(textContainer, BorderLayout.NORTH);
		text = new JLabel("<html>Du befindest dich auf dem Dorfplatz. <br>Entscheide, was du als nächstes tun möchtest. <br>Gehe dich im Laden ausrüsten, verlasse das Dorf oder bleibe noch etwas, <br>um zu Kräften zu kommen.</html>");
		
		textContainer.setBackground(hintergrundfarbe);
		textContainer.add(text);
		text.setFont(new Font("HP Simplified", Font.PLAIN, 26));
		
		
	    //Bild einfügen
		
		JPanel bild = new JPanel();
		contentContainer.add(bild, BorderLayout.CENTER);
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setBackground(hintergrundfarbe);
		bild.setBackground(hintergrundfarbe);
		bild.add(picture);
		picture.setIcon(dorf);
		picture.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		
		Font buttonSchrift = new Font("Gill Sans", Font.BOLD, 22);
		
		JPanel buttons = new JPanel();
		this.add(buttons, BorderLayout.SOUTH);
		firstButton = new JButton("Verlasse das Dorf");
		buttons.add(firstButton);
		secondButton = new JButton("Betritt den Dorfladen");
		buttons.add(secondButton);
		thirdButton = new JButton("Ruhe dich aus.");
		buttons.add(thirdButton);
		
		buttons.setBackground(hintergrundfarbe);
		
		firstButton.setFont(buttonSchrift);
		secondButton.setFont(buttonSchrift);
		thirdButton.setFont(buttonSchrift);
		
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
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	//löscht die alten ActionListener:
	private void setScene(String ort) {
        for (JButton b : new JButton[]{firstButton, secondButton, thirdButton}) {
            for (java.awt.event.ActionListener al : b.getActionListeners()) {
                b.removeActionListener(al);
            }
        }
        //Hier kommen alle Orte:
		switch(ort) {
		case "draußen":
			text.setText("Wohin möchtest du gehen?");
			picture.setIcon(vorDemDorf);
			firstButton.setText("Den Wald betreten");
			secondButton.setText("Auf dem Weg bleiben");
			thirdButton.setText("Gehe zurück ins Dorf");
			firstButton.addActionListener(e -> setScene("wald"));
			secondButton.addActionListener(e -> setScene("wegFolgen"));
			thirdButton.addActionListener(e -> setScene("dorf"));
			aktualisiereAnzeige();
			break;
		case "wald":
			secondButton.setVisible(true);
			thirdButton.setVisible(true);
			text.setText("Triff eine Wahl");
			picture.setIcon(wald);
			firstButton.setText("Wolf bekämpfen");
			secondButton.setText("Goblins bekämpfen");
			thirdButton.setText("Gehe zurück Richtung Dorf");
			firstButton.addActionListener(e -> setScene("wolf"));
			thirdButton.addActionListener(e -> setScene("dorf"));
			secondButton.addActionListener(e -> setScene("goblins"));
			aktualisiereAnzeige();
			break;
			
			//weg muss nach gewonnenem kampf weitergehen
		case "wegFolgen":
			picture.setIcon(harlunkeBild);
			text.setText("Du begegnest einem Harlunken");
			firstButton.setText("Harlunken angreifen");
			secondButton.setText("Dem Harlunken Wegzoll zahlen (100 Gold)");
			thirdButton.setText("Zurück gehen");
			firstButton.addActionListener(e -> {fight(harlunke, spieler); 
													if(harlunke.getHp()<=0) {
													setScene("kreuzung");}});
												
			secondButton.addActionListener(e -> goldGeben(spieler));
			thirdButton.addActionListener(e -> setScene("draußen"));
			aktualisiereAnzeige();
			break;
			
		case "wolf":
			Enemy wolf = new Enemy("Wolf", 30, 1);
			text.setText("Der Wolf hat 30 Lebenspunkte.");
			picture.setIcon(wolfBild);
			firstButton.setText("Angriff!");
			secondButton.setText("Ausweichen");
			thirdButton.setText("Renn weg.");
			firstButton.addActionListener(e -> fight(wolf, spieler));
			thirdButton.addActionListener(e -> setScene("wald"));
			secondButton.addActionListener(e -> ausweichen(wolf, spieler));
			aktualisiereAnzeige();
		break;

		case "dorf":
			firstButton.setText("Verlasse das Dorf");
			secondButton.setText("Gehe in den Dorfladen");
			thirdButton.setText("Ruh dich aus.");
			picture.setIcon(dorf);
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
			firstButton.setText("Bessere Waffe kaufen");
			secondButton.setText("Schild kaufen");
			thirdButton.setText("Gehe zurück ins Dorf");
			thirdButton.addActionListener(e -> setScene("dorf"));
			firstButton.addActionListener(e -> buyWeapon(spieler));
			secondButton.addActionListener(e -> buyShield(spieler));
			aktualisiereAnzeige();
			break;
		case "goblins":
			Enemy goblins = new Enemy("Goblins", 60, 3);
			text.setText("<html>Die Goblins haben zusammen 80 Lebenspunkte.<br></html>");
			picture.setIcon(goblinsBild);
			firstButton.setText("Angriff!");
			secondButton.setText("Ausweichen");
			thirdButton.setText("Renn weg.");
			firstButton.addActionListener(e -> fight(goblins, spieler));
			thirdButton.addActionListener(e -> setScene("wald"));
			secondButton.addActionListener(e -> ausweichen(goblins, spieler));
			aktualisiereAnzeige();
			break;
		case "kreuzung":
			text.setText("<html>Du erreichst eine Weggabelung.<br> Links geht es zum Drachenberg hinauf. <br>Rechts in ein Flusstal. </html>");
			secondButton.setVisible(true);
			thirdButton.setVisible(true);
			firstButton.setText("Nach Links gehen");
			secondButton.setText("Nach Rechts gehen");
			thirdButton.setText("Zum Dorf zurückkehren");
			thirdButton.addActionListener(e -> setScene("draußen"));
			firstButton.addActionListener(e -> setScene("drachenberg"));
			secondButton.addActionListener(e -> setScene("flusstal"));
			aktualisiereAnzeige();
			picture.setIcon(weggabelung);
			break;
			
		case "flusstal":
			text.setText("<html>Im Flusstal triffst du auf einen Händler.<br> Er erzählt dir, dass der Weg zu einem großen Dorf führt. </html>");
			picture.setIcon(flusstal);
			firstButton.setText("Weitergehen");
			secondButton.setText("Handeln");
			thirdButton.setText("zurück gehen");
			thirdButton.addActionListener(e -> setScene("kreuzung"));
			firstButton.addActionListener(e -> setScene("stadt"));
			secondButton.addActionListener(e -> handeln(spieler));
			aktualisiereAnzeige();
			break;
		case "drachenberg":
			picture.setIcon(drachennest);
			text.setText("<html>Als du den Berggipfel erreichst, entdeckst du ein Drachennest mit 4 Eiern darin. <br>"
					+ "willst du riskieren eines zu nehmen, oder dich lieber vorsichtig zurückziehen?</html>");
			firstButton.setText("Ein Ei nehmen");
			secondButton.setText("Verstecken und Beobachten");
			thirdButton.setText("zurückziehen");
			
			thirdButton.addActionListener(e-> setScene("kreuzung"));
			firstButton.addActionListener(e -> setScene("dragon"));
			secondButton.addActionListener(e -> setScene("versteck"));
			
			aktualisiereAnzeige();
			break;
		case "dragon":

			text.setText("Als du dich dem Nest näherst, kommt plötzlich der Drache angeflogen und fixiert dich!");
			
			firstButton.setText("Angriff!");
			secondButton.setText("Ausweichen");
			thirdButton.setText("Renn weg.");
			firstButton.addActionListener(e -> {fight(drache, spieler);
											if(drache.hp <= 0) {
												dracheBesiegt = 1;
											}
											else {
												dracheBesiegt = 0;
											}
			});
			
			thirdButton.addActionListener(e -> setScene("kreuzung"));
			secondButton.addActionListener(e -> ausweichen(drache, spieler));
			aktualisiereAnzeige();
			break;
		case "versteck":
			text.setText("Du lauerst in deinem Versteck, als auf einmal der Drache zurückkehrt.");
			firstButton.setText("Angreifen");
			secondButton.setText("Ausharren, bis der Drache weg ist.");
			thirdButton.setText("Vorsichtig zurückziehen");
			firstButton.addActionListener(e -> setScene("dragon"));
			secondButton.addActionListener(e -> setScene("drachenberg"));
			thirdButton.addActionListener(e -> {setScene("dragon");
												text.setText("Du versuchst leise davon zu schleichen. Doch der Drache bemerkt dich sofort.");
												});
			
			break;
		case "stadt": 
			if(drache.hp > 0) {
			text.setText("<html>Du durchquerst die großen Stadttore und befindest dich direkt auf dem Markplatz <br> "
					+ "Doch dieser wirkt wie ausgestorben. <br> Nur ein einzelner Wachmann erzählt dir von den gefährlichen Drachenangriffen. <br> "
					+ "Deshalb traut sich niemand mehr auf die Straße.</html>");
					firstButton.setText("Taverne aufsuchen");
					secondButton.setText("Kaserne betreten");
					thirdButton.setText("Stadt verlassen");
					thirdButton.setVisible(true);
					firstButton.addActionListener(e -> setScene("taverne"));
					secondButton.addActionListener(e -> setScene("kaserne"));
					thirdButton.addActionListener(e -> setScene("flusstal"));
					picture.setIcon(stadtMarkt);
			}
			else{
				text.setText("<html>Du durchquerst die großen Stadttore und befindest dich direkt auf dem Markplatz <br>"
						+ "Viele Menschen sind auf den Straßen und jubeln dir zu. <br>"
						+ "Du bist nun der gefeierte Drachentöter!</html>");
				firstButton.setText("An Dorffest teilnehmen.");
				secondButton.setText("Haus kaufen");
				thirdButton.setText("Stadt verlassen");
				thirdButton.setVisible(true);
				firstButton.addActionListener(e -> setScene("dorffest"));
				secondButton.addActionListener(e -> hausKaufen(spieler));
				thirdButton.addActionListener(e -> setScene("flusstal"));
			}
			break;
		case "taverne":
				text.setText("<html>Hier herrscht ausgelassene Stimmung. <br>Trinke ein paar Bierchen oder nimm dir ein Zimmer um dich zu erholen.</html>");
				firstButton.setText("Trinke ein Bier (5 Gold)");
				secondButton.setText("Nimm dir ein Zimmer (25 Gold)");
				thirdButton.setText("Verlasse die Taverne");
				firstButton.addActionListener(e -> bierTrinken(spieler));
				secondButton.addActionListener(e -> ausschlafen(spieler));
				thirdButton.addActionListener(e -> setScene("stadt"));
			break;
		case "kaserne":
			text.setText("<html>Du betrittst die Kaserne.<br>Hier kannst du deine Kampffähikeiten trainieren.<br>"
					+ "du musst nur genug Erfahrung mitbringen.</html>");
			firstButton.setText("Kampf trainieren (30 Erfahrung)");
			secondButton.setText("zurück");
			thirdButton.setVisible(false);
			firstButton.addActionListener(e -> trainiereKampf(spieler));
			secondButton.addActionListener(e -> setScene("stadt"));
			break;
		case "winner":
			text.setText("<html>Herzlichen Glückwunsch!<br>Du hast das Spiel gewonnen!</html>");
			text.setFont(new Font("Castellar Standard", Font.PLAIN, 24));
		}
	}
	
	public void fight(Enemy monster, Charakter player) {
		if(monster.getHp() > 0 && player.getHp() > 0) {
			int zahlAttack = random.nextInt(5) + player.strength;
			monster.setHp(monster.getHp()-zahlAttack);
			int zahlMonster = random.nextInt(3) + monster.strength;
			player.setHp(player.getHp()-zahlMonster);
		
		
		if(monster.getHp() <= 0) {
			text.setText("Du hast den Gegner besiegt. Du erhälst Erfahrung und Gold.");
			spieler.setEp(player.getEp()+ 20*monster.getStrength());
			spieler.setGold(spieler.getGold()+(10*monster.getStrength()));
			aktualisiereAnzeige();
			secondButton.setVisible(false);
			thirdButton.setVisible(false);
			firstButton.setText("Gehe zurück");
			firstButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
			    if (monster.getName().equals("Goblins") || monster.getName().equals("Wolf")) {
			        setScene("wald");
			    } else {
			        setScene("kreuzung");
			    }
			}));
			monsterDead(monster.name);

		}
		else if(player.getHp() <= 0){
			text.setText("Du bist tot. GAME OVER!");
			gameOver();
		}else {
		
		spielerHp.setText("Lebenspunkte: " + player.getHp() + " | ");
		text.setText(monster.getName() + " hat noch " + monster.getHp() + " Lebenspunkte.");
	}
	}
		aktualisiereAnzeige();
	}
	public void aktualisiereAnzeige() {
		spielerHp.setText("Lebenspunkte: " + spieler.getHp());
		spielerEp.setText("Erfahrung: " + spieler.getEp());
		spielerWeapon.setText("Waffe: " + spieler.getWeapon());
		spielerGold.setText("Gold: " + spieler.getGold());
		spielerStaerke.setText("Stärke: " + spieler.getStrength());

		
		if(spieler.getHp() <= 10) {
			spielerHp.setForeground(Color.RED);
		}
			else {
				spielerHp.setForeground(Color.BLACK);
			}
		if(spieler.getWeapon() == "Breitschwert") {
			spielerWeapon.setForeground(Color.YELLOW);
		}
		else if(spieler.getWeapon() == "Feuerschwert") {
			spielerWeapon.setForeground(Color.ORANGE);
		}
		else {
			spielerWeapon.setForeground(Color.BLACK);
		}
	}
	
	public void ausweichen(Enemy monster, Charakter player) {
		if(monster.getHp() >0 && player.getHp() >0) {
			player.setHp(player.getHp()-monster.getStrength());
		}
		aktualisiereAnzeige();
	}
	public void buyWeapon(Charakter player) {
		if(player.getWeapon() == "Schwert" && player.getGold()>=50) {
			player.setWeapon("Breitschwert");
			player.setStrength(player.getStrength()+2);
			player.setGold(player.getGold()-50);
		}
		else if(player.getWeapon() == "Breitschwert" && player.getGold()>=100) {
			player.setWeapon("Feuerschwert");
			player.setStrength(player.getStrength()+5);
			player.setGold(player.getGold()-100);
		}
		else if(player.getGold()<50) {
			text.setText("Du hast nicht genug Gold");
		}
		else if(player.getWeapon() == "Feuerschwert") {
			text.setText("Du hast bereits die beste Waffe.");
		}
		aktualisiereAnzeige();
	}
	
	public void handeln(Charakter player) {
		text.setText("Der Händler hat magische Amulette im Angebot.");
		for (JButton b : new JButton[]{firstButton, secondButton, thirdButton}) {
            for (java.awt.event.ActionListener al : b.getActionListeners()) {
                b.removeActionListener(al);
            }}
		firstButton.setText("Magieramulett kaufen (70 Gold)");
		secondButton.setText("Kriegeramulett kaufen (70 Gold)");
		thirdButton.setText("Handel beenden");
		thirdButton.addActionListener(e -> setScene("flusstal"));
		firstButton.addActionListener(e -> magieAmulettKaufen(player));
		secondButton.addActionListener(e -> kriegerAmulettKaufen(player));
		aktualisiereAnzeige();
	}
	

	public void magieAmulettKaufen(Charakter player) {
		
		if(player.getName().equals("Magier") && player.getAmulett() == false && player.getGold() >= 70) {
			player.setGold(player.getGold()-70);
			player.setStrength(player.getStrength() + 5);
			player.setAmulett(true);
		}
		else if(player.getGold() < 70) {
			text.setText("Du hast nicht genug Gold.");
		}
		else {
			text.setText("<html>Du kannst dieses Amulett nicht kaufen. <br>Entweder besitzt du es bereits oder es passt nicht zu deiner Spielerklasse.</html>");
		}
	}
	
	public void kriegerAmulettKaufen(Charakter player) {
		
		if(player.getName().equals("Schwertkämpfer") && player.getAmulett() == false && player.getGold() >= 70) {
			player.setGold(player.getGold() - 70);
			player.setStrength(player.getStrength() + 5);
			player.setAmulett(true);
		}
		else if(player.getGold() < 70) {
			text.setText("Du hast nicht genug Gold.");
		}
		else {
			text.setText("<html>Du kannst dieses Amulett nicht kaufen. <br>Entweder besitzt du es bereits oder es passt nicht zu deiner Spielerklasse.</html>");
		}
		aktualisiereAnzeige();
	}
	
	
	public void buyShield(Charakter player) {
		if(player.getShield() == false && player.getGold()>=30) {
			player.setShield(true);
			player.setGold(player.getGold()-30);
		}
		else if(player.getShield() == true) {
			text.setText("Du hast bereits einen Schild");
		}
		else if(player.getGold()<30) {
			text.setText("Du hast nicht genug Gold.");
		}
		aktualisiereAnzeige();
	}
	public void gameOver() {
		secondButton.setVisible(false);
		thirdButton.setVisible(false);
		picture.setIcon(gameOver);
		firstButton.setText("Neues Spiel starten.");
		firstButton.addActionListener(e -> {
			new StartFrame();
			this.setVisible(false);
		});
	}
	
	public void monsterDead(String name) {
		switch(name) {
		case "Wolf":
			picture.setIcon(wolfBildBW);
			picture.revalidate();
			picture.repaint();
		break;
		case "Goblins":
			picture.setIcon(goblinsBW);
		break;
	}
	}
	public void goldGeben(Charakter player) {
		
		if(player.getGold() < 100) {
			text.setText("Du hast nicht genug Gold");
		}
		else {
		player.setGold(player.getGold()-100);
		// setScene hier noch
		setScene("kreuzung");
		}
	}
	public void hausKaufen(Charakter player) {
		for (JButton b : new JButton[]{firstButton, secondButton, thirdButton}) {
            for (java.awt.event.ActionListener al : b.getActionListeners()) {
                b.removeActionListener(al);
            }}
		text.setText("<html>Da du der Drachentöter bist, darfst du dir ein Haus kaufen. <br>Es kostet 1000 Gold."
				+ "<br>Mit Kauf des Hauses hast das Ziel des Spiels erreicht und gewonnen. </html>");
		firstButton.setText("Haus kaufen (1000 Gold)");
		secondButton.setText("Zurück");
		thirdButton.setVisible(false);
		firstButton.addActionListener(e -> {if(player.getGold()>=1000) {
												player.setGold(player.getGold()-1000);
												setScene("winner");
												}
		});
		secondButton.addActionListener(e -> setScene("stadt"));
		aktualisiereAnzeige();
	}
	
	public void bierTrinken(Charakter player) {
		
		if(player.getBier() < 5) {
			player.setGold(player.getGold()-5);
			player.setBier(player.getBier()+1);
			text.setText("Das Bier schmeckt köstlich.");
		}
		else if(player.getBier() < 8) {
			player.setGold(player.getGold()-5);
			player.setBier(player.getBier()+1);
			text.setText("Es schmeckt zu gut. Langsam wirst du benebelt.");
			player.setHp(player.getHp()-5);
		}
		else{
			text.setText("Das war zu viel. Dir wird schwarz vor Augen.");
			player.setHp(5);
			player.setBier(0);
			setScene("dorf");
		}
	
		aktualisiereAnzeige();
	}
	
	public void ausschlafen(Charakter player) {
		player.setGold(player.getGold()-25);
		player.setHp(100);
		aktualisiereAnzeige();
	}
	
	public void trainiereKampf(Charakter player) {
		if(player.getEp() > 30) {
			player.setStrength(player.getStrength()+5);
			player.setEp(player.getEp()-30);
		}
		else {
			text.setText("Du hast nicht genug Erfahrung");
		}
		aktualisiereAnzeige();
	}
}

