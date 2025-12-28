package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
	this.setLayout(new BorderLayout());

	
	JPanel container = new JPanel();
	
	container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
	this.add(container, BorderLayout.CENTER);

	container.setBackground(new Color(87, 152, 242));

	JLabel charakterFrage = new JLabel("Wähle einen Charakter");
	charakterFrage.setAlignmentX(Component.LEFT_ALIGNMENT);
	
	
	
	JPanel header = new JPanel();
	header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
	header.add(charakterFrage);
	container.add(header);
	
	header.setBackground(new Color(87, 152, 242));

	charakterFrage.setFont(new Font("Arial", Font.PLAIN, 28));
	JPanel bilder = new JPanel();
	container.add(bilder);
	bilder.setLayout(new BoxLayout(bilder, BoxLayout.X_AXIS));
	JPanel magier = new JPanel();
	bilder.add(magier);
	magier.setLayout(new BoxLayout(magier, BoxLayout.Y_AXIS));
	JPanel schwert = new JPanel();
	bilder.add(schwert);
	schwert.setLayout(new BoxLayout(schwert, BoxLayout.Y_AXIS));
	
	schwert.setBackground(new Color(87, 152, 242));
	magier.setBackground(new Color(87, 152, 242));
	
	
	Font text = new Font("Arial", Font.PLAIN, 22);
	mage = new JRadioButton("Magier");
	magier.add(mage);
	mage.setFont(text);
	

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
	sword.setFont(text);
	
	sword.setBackground(new Color(87, 152, 242));
	mage.setBackground(new Color(87, 152, 242));
	
	JPanel fooder = new JPanel();
	container.add(fooder);
	fooder.setBackground(new Color(87, 152, 242));
	
	JButton startButton = new JButton("Spiel starten!");
	fooder.add(startButton);
	startButton.setFont(text);
	startButton.setAlignmentX(Component.LEFT_ALIGNMENT);
	
	startButton.addActionListener(e -> {
		String choice = getCharakter();
		if(choice == "Magier") {
			spieler = new Charakter("Magier", "Zauberstab", 80, 0, 1, 0, false);
		}
		else if(choice == "Schwertkämpfer") {
			spieler = new Charakter("Schwertkämpfer", "Schwert", 100, 0, 1, 0, false);
		}
		new GameFrame(spieler);
		this.setVisible(false);
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
