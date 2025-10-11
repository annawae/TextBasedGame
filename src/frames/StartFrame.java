package frames;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class StartFrame extends JFrame {
	
	private JRadioButton mage, sword;
	Charakter spieler;
	
	public StartFrame() {
	this.setSize(550, 420);
	
	JPanel container = new JPanel();
	this.add(container);
	container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
	
	
	JLabel charakterFrage = new JLabel("Wähle einen Charakter");
	container.add(charakterFrage);
	charakterFrage.setFont(new Font("Arial", Font.PLAIN, 24));
	JPanel bilder = new JPanel();
	container.add(bilder);
	bilder.setLayout(new BoxLayout(bilder, BoxLayout.X_AXIS));
	JPanel magier = new JPanel();
	bilder.add(magier);
	magier.setLayout(new BoxLayout(magier, BoxLayout.Y_AXIS));
	JPanel schwert = new JPanel();
	bilder.add(schwert);
	schwert.setLayout(new BoxLayout(schwert, BoxLayout.Y_AXIS));
	
	mage = new JRadioButton("Magier");
	magier.add(mage);

	JLabel magePicture = new JLabel();
	magier.add(magePicture);
	ImageIcon magePic = new ImageIcon(getClass().getResource("/grafiken/magier.png"));
	magePicture.setIcon(magePic);
	sword = new JRadioButton("Schwertkämpfer");
	schwert.add(sword);
	ImageIcon swordPic = new ImageIcon(getClass().getResource("/grafiken/schwertKaempfer.png"));
	JLabel swordPicture = new JLabel();
	swordPicture.setIcon(swordPic);
	schwert.add(swordPicture);
	
	JButton startButton = new JButton("Spiel starten!");
	container.add(startButton);
	
	startButton.addActionListener(e -> {
		String choice = getCharakter();
		if(choice == "Magier") {
			spieler = new Charakter("Magier", "Zauberstab", 80, 0, 1, 0, false);
		}
		else if(choice == "Schwertkämpfer") {
			spieler = new Charakter("Schwertkämpfer", "Schwert", 100, 0, 1, 0, false);
		}
		new GameFrame(spieler);
	});
	
	
	
	
	//nötig damit nur ein Button wählbar ist:
	ButtonGroup charGroup = new ButtonGroup();
	charGroup.add(sword);
	charGroup.add(mage);
	
	
	
	this.setVisible(true);
	}
	
	private String getCharakter() {
		if(mage.isSelected()) return "Magier";
		if(sword.isSelected()) return "Schwertkämpfer";
		return null;
	}
}
