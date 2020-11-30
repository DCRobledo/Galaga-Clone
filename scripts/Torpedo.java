package Game;

public class Torpedo extends Entity {
	// Indica si el torpedo ha sido disparado
	private boolean shot;
	// Indica si el torpedo ha impactado
	private boolean hit;

	// Constructor del torpedo
	public Torpedo(int id, int x, int y, int direction, String sprite, boolean shot, boolean hit) {
		super(id, x, y, direction, sprite);
		setShot(shot);
		setHit(hit);
	}

	// Getters y Setters tipicos
	public boolean isShot() {
		return shot;
	}

	public void setShot(boolean shot) {
		this.shot = shot;
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	// Metodo move caracteristico de los torpedos, solo permite el movimiento en el
	// eje Y
	public void move(int steps) {
		setY(getY() + steps);
	}

	// Metodo que determina si se repone el torpedo o no
	public void loadTorpedo() {
		if (isHit() == true || (getY() < -10)) {
			// Reponemos el torpedo
			setShot(false);
			// Restablecemos el valor de hit
			setHit(false);
			// Lo llevamos a la posicion origen de los torpedos
			setX(200);
			setY(200);
		}
	}
	
	// Metodo que determina si se repone el torpedo o no
	public void loadEnemyTorpedo() {
		if (isHit() == true || (getY() > 230)) {
			// Reponemos el torpedo
			setShot(false);
			// Restablecemos el valor de hit
			setHit(false);
			// Lo llevamos a la posicion origen de los torpedos
			setX(-50);
			setY(-50);
		}
	}

}
