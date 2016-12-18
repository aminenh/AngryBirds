package angrybird.model;

public class Pig{
	
	private double x,y;
	
		public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getCasier() {
		return casier;
	}

	public void setCasier(String casier) {
		this.casier = casier;
	}

	private String casier;
	
	public Pig(){
		this.x=Math.random() * 500 + 200;
		this.y=480;
	}
}
