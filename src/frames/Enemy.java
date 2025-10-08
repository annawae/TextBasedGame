package frames;

public class Enemy {
	String name;
	int hp;
	int strength;
	
	public Enemy(String name, int hp, int strength) {
		this.name = name;
		this.hp = hp;
		this.strength = strength;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setHp(int newHp) {
		this.hp = newHp;
	}
	public int getHp() {
		return hp;
	}
	
	public void setSrength(int newStrength) {
		this.strength = newStrength;
	}
	public int getStrength() {
		return strength;
	}
	
}
