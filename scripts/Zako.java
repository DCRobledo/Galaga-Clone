package Game;

public class Zako extends Enemy {
	// Enemigo tipo Zako
	public Zako(int id, int x, int y) {
		super(id, x, y);
		setHealth(1);
		setSprite("enemy3.png");
	}
	// Metodo que cambia el sprite del Zako en funcion de su posicion
	public void changeSprite(int counter) {
		if (counter % 2 == 0)
			setSprite("enemy3G1.png");
		else
			setSprite("enemy3G0.png");
	}
}
