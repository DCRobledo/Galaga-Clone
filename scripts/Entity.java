package Game;

public class Entity {
	// Indica la id de la entidad
	protected int id;
	// Indica su posicion en los ejes
	protected int x, y;
	// Indica la direccion hacia la que esta apuntando la entidad
	protected int direction;
	// Asocia un sprite a la entidad
	protected String sprite;
	// Variable para animar la explosion de muerte
	protected int counter_explosion = 0;

	// Constructor sin argumentos
	public Entity() {

	}

	// Constructor para gener una entidad en general
	public Entity(int id, int x, int y, int direction, String sprite) {
		setId(id);
		setX(x);
		setY(y);
		setDirection(direction);
		setSprite(sprite);
	}

	// Getters y Setters

	public int getId() {
		return id;
	}

	public int getCounter_explosion() {
		return counter_explosion;
	}

	public void setCounter_explosion(int counter_explosion) {
		this.counter_explosion = counter_explosion;
	}

	public void setId(int id) {
		if (id >= 0)
			this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		if (direction >= 0 && direction < 16) {
			this.direction = direction;
		}
	}

	public String getSprite() {
		return this.sprite;
	}

	public String getLiterallySprite() {
		return this.sprite;
	}

	public void setSprite(String sprite) {
		this.sprite = sprite;
	}

	// Este metodo sera caracteristico para cada tipo de entidad
	public void move() {
	}

}
