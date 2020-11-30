package Game;

public class Enemy extends Entity {
	// Indica la vida del enemigo
	private int health;
	private int indexPath = 0;
	private int counterPath = 0;
	// Torpedo del enemigo
	private Torpedo torpedo;
	// Atributo que indica si esta realizando un movimiento
	private boolean moving = false;
	// Variables que determinan el reseteo de un enemigo tras un ataque
	private boolean reach_limit = false;
	private boolean reach_squad = false;
	// Variable para decidir la ruta de ataque aleatoriamente
	private int random_route = 0;
	// Variable que determina si los enemigos estan vivos
	private boolean alive = true;

	// Constructor sin argumentos
	public Enemy() {
	}

	// Constructor de los enemigos
	public Enemy(int id, int x, int y) {
		setId(id);
		setX(x);
		setY(y);
	}

	// Getters y Setters
	public int getHealth() {
		return health;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getRandom_route() {
		return random_route;
	}

	public void setRandom_route(int random_route) {
		this.random_route = random_route;
	}

	public boolean isReach_limit() {
		return reach_limit;
	}

	public void setReach_limit(boolean reach_limit) {
		this.reach_limit = reach_limit;
	}

	public boolean isReach_squad() {
		return reach_squad;
	}

	public void setReach_squad(boolean reach_squad) {
		this.reach_squad = reach_squad;
	}

	public int getIndexPath() {
		return indexPath;
	}

	public void setIndexPath(int indexPath) {
		this.indexPath = indexPath;
	}

	public int getCounterPath() {
		return counterPath;
	}

	public void setCounterPath(int counterPath) {
		this.counterPath = counterPath;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public Torpedo getTorpedo() {
		return torpedo;
	}

	public void setTorpedo(Torpedo torpedo) {
		this.torpedo = torpedo;

	}

	public void setHealth(int health) {
		if (health >= 0)
			this.health = health;
	}

	// Los enemigos cuentan con un metodo getSprite especial
	public String getSprite() {
		String directionCode;

		if (direction <= 9) {
			directionCode = "0" + direction;
		} else {
			directionCode = "" + direction;
		}
		String[] fileName = sprite.split("\\.");
		return fileName[0] + directionCode + "." + fileName[1];
	}

	public String getLiterallySprite() {
		return this.sprite;
	}

	// Metodo que permite a los enemigos, a diferencia de otras entidades, moverse
	// en todas las direcciones
	public void move(int direction, int steps) {
		// Nos movemos
		setDirection(direction);
		// Calculamos la X e Y a partir de los deltas
		setX(this.getX() + Constants.MOVES[this.getDirection()][0] * steps);
		setY(this.getY() + Constants.MOVES[this.getDirection()][1] * steps);
	}

	// Metodo para cambiar el sprite del enemigo en cada paso
	public void changeSprite() {
	}
	// Metodo que permite al enemigo recorrer un camino preestablecido
	public boolean followPath(int[][] path) {
		if (indexPath >= path.length)
			indexPath = 0;
		if (counterPath == path[indexPath][1]) {
			indexPath++;
			counterPath = 0;
		}
		move(path[indexPath][0], 1);
		counterPath++;
		if (indexPath == path.length - 1) {
			counterPath = 0;
			indexPath = 0;
		}
		return true;
	}
	// Metodo que determina cuando ataca un enemigo
	public boolean doIAttack() {
		int rnd = (int) (Math.random() * 800);
		if (rnd == 13)
			return true;
		else
			return false;
	}

	// Metodo que determina cuando dispara un enemigo
	public boolean doIShot() {
		int rnd = (int) (Math.random() * 600);
		if (rnd == 56)
			return true;
		else
			return false;
	}
}
