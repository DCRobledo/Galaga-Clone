package Game;

public class Goei extends Enemy {
	// Atributo que indica si este goei ha sido llamado por un commander
	private boolean requested = false;
	// Enemigo tipo Goei
	public Goei(int id, int x, int y) {
		super(id, x, y);
		setHealth(1);
		setSprite("enemy2.png");
	}
	// Get y Set de claimed
	public boolean isRequested() {
		return requested;
	}
	public void setRequested(boolean requested) {
		this.requested = requested;
	}
	// Metodo que cambia el sprite del Goei en funcion de su posicion
	public void changeSprite(int counter) {
		if (counter % 2 == 0)
			setSprite("enemy2G1.png");
		else
			setSprite("enemy2G0.png");
	}

}
