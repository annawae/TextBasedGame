package frames;

public class Charakter {
	String name; 
	String weapon;
	int hp;
	int ep;
	int strength;
	int gold;
	boolean shield;
	
	public Charakter(String name, String weapon, int hp, int ep, int strength, int gold, boolean shield) {
		this.name = name;
		this.ep = ep;
		this.weapon = weapon;
		this.hp = hp;
		this.strength = strength;
		this.gold = gold;
		this.shield = shield;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setWeapon(String newWeapon) {
		this.weapon = newWeapon;
	}
	public String getWeapon() {
		return weapon;
	}
	
	public void setHp(int newHp) {
		this.hp = newHp;
	}
	public int getHp() {
		return hp;
	}
	public void setEp(int newEp) {
		this.ep = newEp;
	}
	public int getEp() {
		return ep;
	}
	
	public void setStrength(int newStrength) {
		this.strength = newStrength;
	}
	public int getStrength() {
		return strength;
	}
	
	public void setGold(int newGold) {
		this.gold = newGold;
	}
	public int getGold() {
		return gold;
	}
	public void setShield(boolean newShield) {
		this.shield = newShield;
	}
	public boolean getShield() {
		return shield;
	}
}
