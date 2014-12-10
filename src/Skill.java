import java.util.HashMap;


public class Skill {
	private String name;
	private String type;
	private float mpCost;
	private float damage;
	private int range;

	public Skill(String name, String type, float mpCost, float damage, int range, boolean isDefensible) {
		setName(name);
		setType(type);
		setDefensible(isDefensible);
		setDamage(damage);
		setMpCost(mpCost);
		setRange(range);
		
		if(type.equals("Offensive")) {
			setOffensive(true);
		}else if(type.equals("Support")) {
			setSupport(true);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public float getDamage() {
		return damage;
	}
	
	public void setDamage(float damage) {
		this.damage = damage;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getMpCost() {
		return mpCost;
	}

	public void setMpCost(float mpCost) {
		this.mpCost = mpCost;
	}
	
	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	private boolean defensible;
	public boolean isDefensible() {
		return defensible;
	}

	public void setDefensible(boolean defensible) {
		this.defensible = defensible;
	}

	private boolean isOffensive;
	private boolean isSupport;
	
	public boolean isOffensive() {
		return isOffensive;
	}

	public void setOffensive(boolean isOffensive) {
		this.isOffensive = isOffensive;
	}

	public boolean isSupport() {
		return isSupport;
	}

	public void setSupport(boolean isSupport) {
		this.isSupport = isSupport;
	}
}
