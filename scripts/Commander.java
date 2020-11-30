package Game;

public class Commander extends Enemy {
	// Enemigo tipo Commander
	public Commander(int id, int x, int y) {
		super(id, x, y);
		setHealth(2);
		setSprite("enemy1.png");
	}
	// Metodo que cambia el sprite del Commander en funcion de su posicion y su vida
	public void changeSprite(int counter) {
		if (this.getHealth() == 2) {
			if (counter % 2 == 0)
				setSprite("enemy1G0.png");
			else
				setSprite("enemy1G1.png");
		}
		if (this.getHealth() == 1) {
			if (counter % 2 == 0)
				setSprite("enemy9G0.png");
			else
				setSprite("enemy9G1.png");
		}
	}
}
