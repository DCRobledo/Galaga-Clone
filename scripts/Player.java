package Game;

public class Player extends Entity {
	// Marca la puntuacion del jugador
	private int score;
	// Indica la vida maxima y la vida actual
	private int max_health;
	private int health;
	// Numero de disparos realizados
	private int shoots;
	// Numero de aciertos realizados
	private int hits;
	// Torpedos que posee el jugador
	private Torpedo[] torpedos = new Torpedo[10];
	// Atributo que controla el modo invencible
	private boolean godMode = false;

	// Constructor del jugador
	public Player(int id, int x, int y, int direction, String sprite, int score, int max_health, int health, int shoots,
			int hits) {
		super(id, x, y, Constants.DIR_N, sprite);
		setScore(score);
		setMax_health(max_health);
		setHealth(health);
		setShoots(shoots);
		setHits(hits);
	}

	// Getters y Setters
	public int getScore() {
		return score;
	}

	public boolean isGodMode() {
		return godMode;
	}

	public void setGodMode(boolean godMode) {
		this.godMode = godMode;
	}

	public Torpedo[] getTorpedos() {
		return torpedos;
	}

	public void setTorpedos(Torpedo[] torpedos) {
		this.torpedos = torpedos;
	}

	public void setScore(int score) {
		if (score >= 0)
			this.score = score;
	}

	public int getMax_health() {
		return max_health;
	}

	public void setMax_health(int max_health) {
		if (max_health > 0)
			this.max_health = max_health;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		if (health >= 0 && health <= getMax_health())
			this.health = health;
	}

	public int getShoots() {
		return shoots;
	}

	public void setShoots(int shoots) {
		if (shoots >= 0)
			this.shoots = shoots;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		// Este condicional esta comentado para poder pasar entre niveles con la flecha
		// hacia arriba
		// if (hits >= 0 && hits <= getShoots())
		this.hits = hits;
	}

	// Metodo move caracteristico del jugador, unicamente se produce movimiento en
	// el eje X
	public void move(int steps) {
		setX(getX() + steps);
	}
}
