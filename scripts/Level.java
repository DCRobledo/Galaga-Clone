package Game;

import edu.uc3m.game.GameBoardGUI;

//Clase que organiza la informacion que contiene un nivel
public class Level {
	// Atributo que determina el tablero
	private GameBoardGUI board;
	// Jugador del nivel
	private Player player;
	// Enemigos tipo comandante
	private Commander[] commanders;
	// Enemigos tipo goei
	private Goei[] goeis;
	// Enemigos tipo zako
	private Zako[] zakos;
	// Atributo que indica el nivel
	private int level;
	// Contadores para las entradas de los enemigos
	private int counter_commanders = 0;
	private int counter_goeis = 0;
	private int counter_zakos = 0;
	// Variables auxiliares utilizadas en el movimiento de los enemigos
	private int steps = 0;
	private int sense = 0;
	private int time = 0;
	// Variable para el cambio de sprites de los enemigos
	private int counter = 0;
	// Variables auxiliares para los torpedos del jugador
	private int shoots = 0;
	private int hits = 0;
	// Variable que determina si un comandate a abandonado la formacion
	private boolean commander_out = false;
	// Variable que determina si un comandante tiene guardia o no
	private boolean guard_found = false;
	// Variables que controlan el flujo
	private boolean keepRunning = true;
	// Variable que controla el modo pausa
	private boolean pause = false;

	// Constructor del nivel
	public Level() {
		setLevel(1);
		setBoard();
		setPlayer();
		setCommanders();
		setGoeis();
		setZakos();
	}

	// Getters y Setters
	public GameBoardGUI getBoard() {
		return this.board;
	}

	public boolean isKeepRunning() {
		return keepRunning;

	}

	public void setKeepRunningLevel(boolean keepRunning) {
		this.keepRunning = keepRunning;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	// El tablero se crea con unas medidas preestablecidas y se pinta de color negro
	public void setBoard() {
		final int boardWidth = 17;
		final int boardHeight = 22;
		GameBoardGUI board = new GameBoardGUI(boardWidth, boardHeight);
		board.setVisible(true); // Hacemos el tablero visible
		for (int ii = 0; ii < boardWidth; ii++) { // FILAS
			for (int jj = 0; jj < boardHeight; jj++) { // COLUMNAS
				board.gb_setGridColor(0, 0, 0);
				board.gb_setSquareColor(ii, jj, 0, 0, 0); // Pinta la casilla de color negro
			}
		}
		this.board = board;
	}

	public Player getPlayer() {
		return this.player;
	}

	// Creamos el jugador y sus torpedos
	public void setPlayer() {
		Player player = new Player(0, 80, 200, Constants.DIR_N, "player.png", 0, 3, 3, 3, 0);
		for (int i = 0; i < player.getTorpedos().length; i++) {
			// Creamos los torpedos y los situamos primeramente en una parte del tablero no
			// visible
			player.getTorpedos()[i] = new Torpedo(i + 1000, 200, 200, Constants.DIR_N, "torpedo100.png", false, false);
			getBoard().gb_addSprite(player.getTorpedos()[i].getId(), player.getTorpedos()[i].getLiterallySprite(),
					true);
			getBoard().gb_setSpriteVisible(player.getTorpedos()[i].getId(), true);
			getBoard().gb_moveSpriteCoord(player.getTorpedos()[i].getId(), player.getTorpedos()[i].getX(),
					player.getTorpedos()[i].getY());
		}
		this.player = player;
	}

	public Commander[] getCommanders() {
		return commanders;
	}

	// Los enemigos se crean usando la parte de constants que determina sus
	// posiciones en los ejes X e Y
	// Cada vez que pasamos de nivel se crean 3 nuevos arrays de enemigos en funcion
	// de lo establecido en constants
	public void setCommanders() {
		Commander[] commanders = new Commander[Constants.enemies_positions_X[level - 1][0].length];
		for (int i = 0; i < commanders.length; i++) {
			commanders[i] = new Commander(i + 1, Constants.enemies_positions_X[level - 1][0][i],
					Constants.enemies_positions_Y[level - 1][0][i]);
			// Creamos los torpedos y los situamos fuera del tablero
			Torpedo torpedo = new Torpedo(i + 200, -50, -50, Constants.DIR_N, "torpedo200.png", false, false);
			commanders[i].setTorpedo(torpedo);
			board.gb_addSprite(commanders[i].getTorpedo().getId(), commanders[i].getTorpedo().getLiterallySprite(),
					true);
			board.gb_setSpriteVisible(commanders[i].getTorpedo().getId(), true);
			board.gb_moveSpriteCoord(commanders[i].getTorpedo().getId(), commanders[i].getTorpedo().getX(),
					commanders[i].getTorpedo().getY());
		}
		this.commanders = commanders;
	}

	public Goei[] getGoeis() {
		return goeis;
	}

	public void setGoeis() {
		Goei[] goeis = new Goei[Constants.enemies_positions_X[level - 1][1].length];
		for (int i = 0; i < goeis.length; i++) {
			goeis[i] = new Goei(i + commanders.length + 1, Constants.enemies_positions_X[level - 1][1][i],
					Constants.enemies_positions_Y[level - 1][1][i]);
			Torpedo torpedo = new Torpedo(i + 300, -50, -50, Constants.DIR_N, "torpedo200.png", false, false);
			goeis[i].setTorpedo(torpedo);
			board.gb_addSprite(goeis[i].getTorpedo().getId(), goeis[i].getTorpedo().getLiterallySprite(), true);
			board.gb_setSpriteVisible(goeis[i].getTorpedo().getId(), true);
			board.gb_moveSpriteCoord(goeis[i].getTorpedo().getId(), goeis[i].getTorpedo().getX(),
					goeis[i].getTorpedo().getY());
		}
		this.goeis = goeis;
	}

	public Zako[] getZakos() {
		return zakos;
	}

	public void setZakos() {
		Zako[] zakos = new Zako[Constants.enemies_positions_X[level - 1][2].length];
		for (int i = 0; i < zakos.length; i++) {
			zakos[i] = new Zako(i + commanders.length + goeis.length + 1,
					Constants.enemies_positions_X[level - 1][2][i], Constants.enemies_positions_Y[level - 1][2][i]);
			Torpedo torpedo = new Torpedo(i + 400, -50, -50, Constants.DIR_N, "torpedo200.png", false, false);
			zakos[i].setTorpedo(torpedo);
			board.gb_addSprite(zakos[i].getTorpedo().getId(), zakos[i].getTorpedo().getLiterallySprite(), true);
			board.gb_setSpriteVisible(zakos[i].getTorpedo().getId(), true);
			board.gb_moveSpriteCoord(zakos[i].getTorpedo().getId(), zakos[i].getTorpedo().getX(),
					zakos[i].getTorpedo().getY());
		}
		this.zakos = zakos;
	}

	// Metodo que ejecuta el nivel, establece stats, enemigos, jugador, bucle de
	// partida, etc.
	public void runLevel() throws InterruptedException {
		showInstructions();
		do {
			// Establecemos los datos que se mostraran en la interfaz
			setStats();
			// Añadimos los sprites
			addEntities();
			// Posicionamos a los enemigos
			setEnemiesPositions();
			// Bucle que controla cuando acaba el nivel actual
			do {
				// Entrada de los enemigos
				enemiesEntrances();
				// Actualizamos las stas que se muestran en pantalla
				updateStats();
				// Movimiento de los enemigos
				moveEnemies();
				// Ataque de los enemigos
				enemiesAttack();
				enemiesHit();
				// Acciones del jugador
				playerAction();
				// Torpedos del jugador
				playerTorpedos();
				// Muertes de los enemigos
				enemiesDeaths();
				// Muerte del jugador
				playerDeath();
				// Pausa
				while (pause == true) {
					playerAction();
				}
				// Control de flujo
				keepRunning = keepRunning();
				Thread.sleep(30L);
			} while (keepRunning == true);
			// Si se acaba el nivel reseteamos y avanzamos al siguiente si este existe
			if (keepRunning == false) {
				if (level == 3 && player.getHealth() > 0) {
					board.gb_println("¡NIVEL 3 COMPLETADO!");
					board.gb_showMessageDialog("ENHORABUENA. ¡Has completado el juego!");
					setLevel(4);
				} else {
					if (player.getHealth() > 0)
						resetLevel();
					// Control que determina si el nivel ha terminado porque hemos muerto o porque
					// lo hemos completado
					if (player.getHealth() <= 0) {
						board.gb_showMessageDialog("Has muerto. Partida finalizada");
						setLevel(4);
					}
				}
			}
		} while (level < 4);
	}

	// Metodo que muestra la informacion basica
	public void showInstructions() {
		board.gb_println(
				"CONTROLES: \n" + "Derecha: Flecha derecha \n" + "Izquierda: Flecha derecha \n" + "Disparar: Espacio \n"
						+ "Pausa: Tabulador \n" + "Siguiente nivel: Flecha arriba \n" + "Modo invencible: god \n\n"
						+ "ENEMIGOS: \n" + "Comandante: 500 pnts \n" + "Goei: 250 pnts \n" + "Zako: 100 pnts \n");
	}

	// Metodo que resetea los valores del nivel
	public void resetLevel() {
		if (level < 3) {
			board.gb_println("¡NIVEL " + level + " COMPLETADO!");
			setLevel(getLevel() + 1);
			setCommanders();
			setGoeis();
			setZakos();
			keepRunning = true;
			counter_commanders = 0;
			counter_goeis = 0;
			counter_zakos = 0;
			steps = 0;
			sense = 0;
			time = 0;
			counter = 0;
			shoots = 0;
			hits = 0;
			player.setHealth(3);
			updateStats();
		}
	}

	// Metodo que determina cuando pasar de nivel
	public boolean keepRunning() {
		if (player.getCounter_explosion() == 40
				|| player.getHits() >= commanders.length * 2 + goeis.length + zakos.length)
			return false;
		return true;
	}

	// Metodo que determina las stas de la interfaz
	public void setStats() {
		board.gb_setTextPlayerName("Galaga");
		board.gb_setTextAbility1("Disparos:");
		board.gb_setTextAbility2("Aciertos:");
		board.gb_setTextLevel("Nivel " + level);
		board.gb_setValueLevel(level);
		board.gb_setTextPointsUp("Record: ");
		board.gb_setTextPointsDown("Puntuacion: ");
		board.gb_setPortraitPlayer("galagaLogo.jpg");
		board.gb_addSprite(90, "player.png", true);
		board.gb_moveSpriteCoord(90, 8, 212);
		board.gb_setSpriteVisible(90, true);
		board.gb_addSprite(91, "player.png", true);
		board.gb_moveSpriteCoord(91, 20, 212);
		board.gb_setSpriteVisible(91, true);
	}

	// Metodo que añade, mueve y hace visibles los sprites de enemigos
	public void addEnemiesSprites(Enemy[] enemies) {
		for (int i = 0; i < enemies.length; i++) {
			addSprite(enemies[i]);
		}
	}

	// Metodo que añade, mueve y hace visible un unico sprite
	public void addSprite(Entity entity) {
		board.gb_addSprite(entity.getId(), entity.getSprite(), true);
		board.gb_moveSpriteCoord(entity.getId(), entity.getX(), entity.getY());
		board.gb_setSpriteVisible(entity.getId(), true);
	}

	// Metodo que actualiza las stats
	public void updateStats() {
		board.gb_setValueHealthMax(player.getMax_health());
		board.gb_setValueHealthCurrent(player.getHealth());
		board.gb_setValueAbility1(player.getShoots());
		board.gb_setValueAbility2(player.getHits());
		board.gb_setValuePointsDown(player.getScore());
		board.gb_setValuePointsUp(player.getScore());
		if (player.getHealth() <= 2) {
			board.gb_setSpriteVisible(91, false);
		}
		if (player.getHealth() <= 1) {
			board.gb_setSpriteVisible(90, false);
		}
		if (player.getHealth() == 0 || player.getHits() == (commanders.length * 2 + goeis.length + zakos.length))
			setKeepRunningLevel(false);
	}

	// Metodo que mueve a los enemigos hacia abajo
	public void moveEnemiesDown() {
		// Comprobamos que la entrada de este tipo de enemigo ha terminado
		if (counter_commanders > 77 + commanders.length) {
			for (int i = 0; i < commanders.length; i++) {
				// Movemos a los enemigos que esten vivos
				if (commanders[i].isMoving() == false && commanders[i].isAlive() == true) {
					commanders[i].move(Constants.DIR_S, 1);
					board.gb_moveSpriteCoord(commanders[i].getId(), commanders[i].getX(), commanders[i].getY());
				}
			}
		}
		if (counter_goeis > 99 + goeis.length) {
			for (int i = 0; i < this.getGoeis().length; i++) {
				if (goeis[i].isMoving() == false && goeis[i].isAlive() == true) {
					goeis[i].move(Constants.DIR_S, 1);
					board.gb_moveSpriteCoord(goeis[i].getId(), goeis[i].getX(), goeis[i].getY());
				}
			}
		}
		if (counter_zakos > 102 + zakos.length) {
			for (int i = 0; i < this.getZakos().length; i++) {
				if (zakos[i].isMoving() == false && zakos[i].isAlive() == true) {
					zakos[i].move(Constants.DIR_S, 1);
					board.gb_moveSpriteCoord(zakos[i].getId(), zakos[i].getX(), zakos[i].getY());
				}
			}
		}
	}

	// Metodo del movimiento sincronizado del escuadron
	public void moveEnemies() {
		// Delay que hace a los enemigos mas lentos que al resto de entidades
		if (time % 500 == 0) {
			switch (sense) {
			// Sentido ESTE
			case 0:
				// Decidimos cuando hay que moverse una posicion hacia abajo y cambiar el
				// sentido
				if (steps <= 7) {
					moveEnemiesDown();
					break;
				}
				// Movimiento del enjambre hacia la derecha
				// Condicional que determina cuando a terminado la entrada de cada tipo de
				// enemigo
				if (counter_commanders > 77 + commanders.length) {
					for (int i = 0; i < commanders.length; i++) {
						// Movemos a los enemigos que esten vivos y en la formacion
						if (commanders[i].isMoving() == false && commanders[i].isAlive() == true) {
							commanders[i].move(Constants.DIR_E, 1);
							commanders[i].changeSprite(counter);
							board.gb_setSpriteImage(commanders[i].getId(), commanders[i].getLiterallySprite());
							board.gb_moveSpriteCoord(commanders[i].getId(), commanders[i].getX(), commanders[i].getY());
						}
					}
				}
				if (counter_goeis > 99 + goeis.length) {
					for (int i = 0; i < goeis.length; i++) {
						if (goeis[i].isMoving() == false && goeis[i].isAlive() == true) {
							goeis[i].move(Constants.DIR_E, 1);
							goeis[i].changeSprite(counter);
							board.gb_setSpriteImage(goeis[i].getId(), goeis[i].getLiterallySprite());
							board.gb_moveSpriteCoord(goeis[i].getId(), goeis[i].getX(), goeis[i].getY());
						}
					}
				}
				if (counter_zakos > 102 + zakos.length) {
					for (int i = 0; i < zakos.length; i++) {
						if (zakos[i].isMoving() == false && zakos[i].isAlive() == true) {
							zakos[i].move(Constants.DIR_E, 1);
							zakos[i].changeSprite(counter);
							board.gb_setSpriteImage(zakos[i].getId(), zakos[i].getLiterallySprite());
							board.gb_moveSpriteCoord(zakos[i].getId(), zakos[i].getX(), zakos[i].getY());
						}
					}
				}
				counter++;
				break;
			// Sentido OESTE
			case 1:
				if (steps >= 18) {
					moveEnemiesDown();
					break;
				}
				// Movimiento del enjambre hacia la izquierda
				if (counter_commanders > 77 + commanders.length) {
					for (int i = 0; i < commanders.length; i++) {
						if (commanders[i].isMoving() == false && commanders[i].isAlive() == true) {
							commanders[i].move(Constants.DIR_W, 1);
							commanders[i].changeSprite(counter);
							board.gb_setSpriteImage(commanders[i].getId(), commanders[i].getLiterallySprite());
							board.gb_moveSpriteCoord(commanders[i].getId(), commanders[i].getX(), commanders[i].getY());
						}
					}
				}
				if (counter_goeis > 99 + goeis.length) {
					for (int i = 0; i < goeis.length; i++) {
						if (goeis[i].isMoving() == false && goeis[i].isAlive() == true) {
							goeis[i].move(Constants.DIR_W, 1);
							goeis[i].changeSprite(counter);
							board.gb_setSpriteImage(goeis[i].getId(), goeis[i].getLiterallySprite());
							board.gb_moveSpriteCoord(goeis[i].getId(), goeis[i].getX(), goeis[i].getY());
						}
					}
				}
				if (counter_zakos > 102 + zakos.length) {

					for (int i = 0; i < zakos.length; i++) {
						if (zakos[i].isMoving() == false && zakos[i].isAlive() == true) {
							zakos[i].move(Constants.DIR_W, 1);
							zakos[i].changeSprite(counter);
							board.gb_setSpriteImage(zakos[i].getId(), zakos[i].getLiterallySprite());
							board.gb_moveSpriteCoord(zakos[i].getId(), zakos[i].getX(), zakos[i].getY());
						}
					}
				}
				counter++;
				break;
			}
			// Control del sentido del movimiento
			switch (sense) {
			case 0:
				steps++;
				break;
			case 1:
				steps--;
				break;
			}
			switch (steps) {
			case 7:
				sense = 0;
				break;
			case 18:
				sense = 1;
				break;
			}
		}
		time += 50;

	}

	// Metodo que determina las acciones del jugador
	public void playerAction() {
		// Captura en lastAction la última tecla pulsada y trim() elimina los espacios
		String lastAction = getBoard().gb_getLastAction().trim();
		// Se mueve solo si se pulsa alguna tecla o se mantiene en movimiento
		if (lastAction.length() > 0 && player.getHealth() > 0) {

			// Cada uno de los casos corresponderá con las teclas/controles
			switch (lastAction) {
			case "right":
				if (pause == false) {
					if (player.getX() < 161) {
						player.move(2);
						board.gb_moveSpriteCoord(player.getId(), player.getX(), player.getY());
						break;
					} else {
						board.gb_moveSpriteCoord(player.getId(), player.getX(), player.getY());
						break;
					}
				}
			case "left":
				if (pause == false) {

					if (player.getX() > 10) {
						player.move(-2);
						board.gb_moveSpriteCoord(player.getId(), player.getX(), player.getY());
						break;
					} else {
						board.gb_moveSpriteCoord(player.getId(), player.getX(), player.getY());
						break;
					}
				}
				// Disparos de los torpedos
			case "space":
				if (pause == false) {
					// Variable que controla cuando se ha encontrado un torpedo que no haya sido
					// disparado
					boolean found = false;
					// Buscamos un torpedo que no haya sido disparado
					for (int i = 0; i < player.getTorpedos().length && found == false; i++) {
						if (player.getTorpedos()[i].isShot() == false) {
							// Lo disparamos
							player.getTorpedos()[i].setShot(true);
							// Lo movemos a la posicion de la punta de la nave
							player.getTorpedos()[i].setX(player.getX());
							player.getTorpedos()[i].setY(player.getY() - 1);
							board.gb_moveSpriteCoord(player.getTorpedos()[i].getId(), player.getTorpedos()[i].getX(),
									player.getTorpedos()[i].getY());
							// Variables que determina cuando ha sido disparado un nuevo torpedo
							shoots++;
							player.setShoots(shoots);
							board.gb_setValueAbility1(getPlayer().getShoots());
							// Cortamos la busqueda
							found = true;
						}
					}
					break;
				}
			case "up":
				if (pause == false) {
					player.setHits(commanders.length * 2 + goeis.length + zakos.length);
				}
				break;

			case "tab":
				pause = (pause == true) ? false : true;
				if (pause == true) {
					board.gb_println("Pausa ON");
				} else
					board.gb_println("Pausa OFF");
				break;
			case "command god":
				if (player.isGodMode() == false) {
					player.setGodMode(true);
					board.gb_println("Modo invencible ON");
				} else {
					player.setGodMode(false);
					board.gb_println("Modo invencible OFF");
				}
				break;
			}
		}
	}

	// Metodo que determina cuando ha impactado un torpedo
	public boolean hit(Entity torpedo, Entity entity) {

		// Este array indica las posibles posiciones de impacto
		int mistakeX = 6;
		int mistakeY = 5;

		// Comprobamos si se ha acertado en alguna de las posiciones posibles
		if ((torpedo.getX() > (entity.getX() - mistakeX)) && (torpedo.getX() <= (entity.getX() + mistakeX))) {
			if ((torpedo.getY() > (entity.getY() - mistakeY)) && (torpedo.getY() <= (entity.getY() + mistakeY)))
				return true;
			else
				return false;
		}
		return false;
	}

	// Metodo correspondiente a los torpedos
	public void playerTorpedos() {
		// Comprobamos que torpedos han sido disparados y, por lo tanto, deben moverse
		for (int i = 0; i < player.getTorpedos().length; i++) {
			// Condicional que indica si este torpedo ha sido disparado
			if (player.getTorpedos()[i].isShot() == true) {
				// Movemos el torpedo en el eje Y
				player.getTorpedos()[i].move(-5);
				board.gb_moveSpriteCoord(player.getTorpedos()[i].getId(), player.getTorpedos()[i].getX(),
						player.getTorpedos()[i].getY());
				// Comprobamos si ha impactado en algun enemigo
				for (int j = 0; j < commanders.length; j++) {
					if ((hit(player.getTorpedos()[i], commanders[j]) == true)) {

						// Consecuencias de acierto, cambiamos el atributo hit del torpedo
						player.getTorpedos()[i].setHit(true);
						// Reducion de la vida del enemigo en caso de acierto
						commanders[j].setHealth(commanders[j].getHealth() - 1);
						if (commanders[j].getHealth() == 0) {
							commanders[j].setAlive(false);
							board.gb_println("Comandante eliminado! 500 puntos!");
							player.setScore(player.getScore() + 500);
						}
						hits++;
						player.setHits(hits);
					}
				}
				for (int j = 0; j < goeis.length; j++) {
					if ((hit(player.getTorpedos()[i], goeis[j]) == true)) {
						player.getTorpedos()[i].setHit(true);
						goeis[j].setAlive(false);
						goeis[j].setHealth(goeis[j].getHealth() - 1);
						board.gb_println("Goei eliminado! 250 puntos!");
						player.setScore(player.getScore() + 250);
						hits++;
						player.setHits(hits);
					}
				}
				for (int j = 0; j < zakos.length; j++) {
					if ((hit(getPlayer().getTorpedos()[i], zakos[j]) == true) && zakos[j].isAlive() == true) {
						player.getTorpedos()[i].setHit(true);
						zakos[j].setAlive(false);
						zakos[j].setHealth(zakos[j].getHealth() - 1);
						board.gb_println("Zako eliminado! 100 puntos!");
						player.setScore(player.getScore() + 100);
						hits++;
						player.setHits(hits);
					}
				}
			}
			// Reponemos los torpedos que hagan falta
			player.getTorpedos()[i].loadTorpedo();
			board.gb_moveSpriteCoord(player.getTorpedos()[i].getId(), player.getTorpedos()[i].getX(),
					player.getTorpedos()[i].getY());
		}
	}

	// Metodo para los torpedos enemigos
	public void enemyTorpedos(Enemy[] enemies) {
		// Buscamos los enemigos que puedan disparar
		for (int i = 0; i < enemies.length; i++) {
			if (enemies[i].getTorpedo().isShot() == false && enemies[i].doIShot() == true
					&& enemies[i].getHealth() > 0) {
				// Disparamos
				enemies[i].getTorpedo().setShot(true);
				// Colocamos el torpedo en la punta inferior del enemigo
				enemies[i].getTorpedo().setX(enemies[i].getX());
				enemies[i].getTorpedo().setY(enemies[i].getY() + 1);
				board.gb_moveSpriteCoord(enemies[i].getTorpedo().getId(), enemies[i].getTorpedo().getX(),
						enemies[i].getTorpedo().getY());
			}
			// Condicional que indica si este torpedo ha sido disparado
			if (enemies[i].getTorpedo().isShot() == true) {
				// Movemos el torpedo en el eje Y
				enemies[i].getTorpedo().move(2);
				board.gb_moveSpriteCoord(enemies[i].getTorpedo().getId(), enemies[i].getTorpedo().getX(),
						enemies[0].getTorpedo().getY());
				// Comprobamos si ha impactado en el jugador
				if (hit(enemies[i].getTorpedo(), player) == true && player.isGodMode() == false) {
					// Consecuencias de acierto, cambiamos el atributo hit del torpedo
					enemies[i].getTorpedo().setHit(true);
					// Reducion de la vida del jugador en caso de acierto
					player.setHealth(player.getHealth() - 1);
				}
				// Reponemos los torpedos que hagan falta
				enemies[i].getTorpedo().loadEnemyTorpedo();
				board.gb_moveSpriteCoord(enemies[i].getTorpedo().getId(), enemies[i].getTorpedo().getX(),
						enemies[i].getTorpedo().getY());
			}
		}
	}

	// Metodo que unifica el disparo de los torpedos enemigos
	public void enemiesTorpedos() {
		if (counter_commanders > 78)
			this.enemyTorpedos(commanders);
		if (counter_goeis > 104)
			this.enemyTorpedos(goeis);
		if (counter_zakos > 117)
			this.enemyTorpedos(zakos);
	}

	// Metodo para posicionar al inicio a los commanders
	public void setCommandersPosition() {
		for (int i = 0; i < commanders.length; i++) {
			commanders[i].setX(Constants.enemies_positions_X[level - 1][0][i] + 15);
			commanders[i].setY(-5);
			board.gb_setSpriteImage(commanders[i].getId(), commanders[i].getSprite());
			board.gb_moveSpriteCoord(commanders[i].getId(), commanders[i].getX(), commanders[i].getY());
		}
	}

	// Metodo para las entradas de los commanders
	public void commanderEntrance() {
		// Hacemos una ruta definida por condicionales, estos vienen marcados por los
		// pasos
		for (int i = 0; i < commanders.length; i++) {
			if (commanders[i].isAlive() == true) {
				if (i < commanders.length / 2) {
					if (counter_commanders <= 32) {
						commanders[i].move(Constants.DIR_S, 1);

					} else if (counter_commanders > 32 && counter_commanders <= 38) {
						commanders[i].move(Constants.DIR_WSW, 1);

					} else if (counter_commanders > 38 && counter_commanders <= 46) {
						commanders[i].move(Constants.DIR_WNW, 1);

					} else if (counter_commanders > 46 && counter_commanders <= 65) {
						commanders[i].move(Constants.DIR_NE, 1);
					} else if (counter_commanders > 65 && counter_commanders <= 77) {
						commanders[i].move(Constants.DIR_N, 1);
					} else if (commanders[i].getHealth() > 0) {
						commanders[i].setX(Constants.enemies_positions_X[level - 1][0][i]);
						commanders[i].setY(Constants.enemies_positions_Y[level - 1][0][i]);
						commanders[i].setSprite("enemy1.png");
						board.gb_setSpriteImage(commanders[i].getId(), commanders[i].getSprite());
						board.gb_moveSpriteCoord(commanders[i].getId(), commanders[i].getX(), commanders[i].getY());
					}
					if (counter_commanders <= 77) {
						board.gb_setSpriteImage(commanders[i].getId(), commanders[i].getSprite());
						board.gb_moveSpriteCoord(commanders[i].getId(), commanders[i].getX(), commanders[i].getY());
					}
				} else {
					if (counter_commanders <= 32) {
						commanders[i].move(Constants.DIR_S, 1);

					} else if (counter_commanders > 32 && counter_commanders <= 38) {
						commanders[i].move(Constants.DIR_ESE, 1);

					} else if (counter_commanders > 38 && counter_commanders <= 46) {
						commanders[i].move(Constants.DIR_ENE, 1);

					} else if (counter_commanders > 46 && counter_commanders <= 65) {
						commanders[i].move(Constants.DIR_NW, 1);
					} else if (counter_commanders > 65 && counter_commanders <= 77) {
						commanders[i].move(Constants.DIR_N, 1);
					} else if (commanders[i].getHealth() > 0) {
						commanders[i].setX(Constants.enemies_positions_X[level - 1][0][i]);
						commanders[i].setY(Constants.enemies_positions_Y[level - 1][0][i]);
						commanders[i].setSprite("enemy1.png");
						board.gb_setSpriteImage(commanders[i].getId(), commanders[i].getSprite());
						board.gb_moveSpriteCoord(commanders[i].getId(), commanders[i].getX(), commanders[i].getY());
					}
					if (counter_commanders <= 77) {
						board.gb_setSpriteImage(commanders[i].getId(), commanders[i].getSprite());
						board.gb_moveSpriteCoord(commanders[i].getId(), commanders[i].getX(), commanders[i].getY());
					}
				}
			}
		}
	}

	// Metodo para posicionar al inicio a los goei
	public void setGoeisPosition() {
		for (int i = 0; i < goeis.length; i++) {
			if (i < goeis.length / 2) {
				goeis[i].setX(10);
				goeis[i].setY(230 + i * 10);
			} else {
				goeis[i].setX(160);
				goeis[i].setY(230 + (i - goeis.length / 2) * 10);
			}
			board.gb_setSpriteImage(goeis[i].getId(), goeis[i].getSprite());
			board.gb_moveSpriteCoord(goeis[i].getId(), goeis[i].getX(), goeis[i].getY());
		}
	}

	// Metodo para las entradas de los goeis
	public void goeisEntrance() {
		for (int i = 0; i < goeis.length; i++) {
			if (goeis[i].isAlive() == true) {
				if (i < goeis.length / 2) {
					if (counter_goeis <= 25 + 2 * i) {
						goeis[i].move(Constants.DIR_N, 1);
					} else if (counter_goeis > 25 + 2 * i && counter_goeis <= 55 + 2 * i) {
						goeis[i].move(Constants.DIR_ENE, 1);
					} else if (counter_goeis > 55 + 2 * i && counter_goeis <= 65 + 2 * i) {
						int nextDir = goeis[i].getDirection() + 1;
						if (nextDir == 15) {
							nextDir = 0;
						}
						goeis[i].move(nextDir, 1);
					} else if (counter_goeis > 65 + 2 * i && counter_goeis <= 85 + 2 * i) {
						goeis[i].move(Constants.DIR_WNW, 1);
					} else if (counter_goeis > 85 + 2 * i && counter_goeis <= 89 + 2 * i) {
						int nextDir = goeis[i].getDirection() + 1;
						if (nextDir == 15) {
							nextDir = 0;
						}
						goeis[i].move(nextDir, 1);
					} else if (counter_goeis > 89 + 2 * i && counter_goeis < 99 + 2 * i) {
						goeis[i].move(Constants.DIR_NNE, 1);
					} else if (goeis[i].getHealth() > 0) {
						goeis[i].setDirection(Constants.DIR_N);
						goeis[i].setX(Constants.enemies_positions_X[level - 1][1][i]);
						goeis[i].setY(Constants.enemies_positions_Y[level - 1][1][i]);
						goeis[i].setSprite("enemy2.png");
						board.gb_setSpriteImage(goeis[i].getId(), goeis[i].getSprite());
						board.gb_moveSpriteCoord(goeis[i].getId(), goeis[i].getX(), goeis[i].getY());
					}
					if (counter_goeis < 99 + 2 * i) {
						board.gb_setSpriteImage(goeis[i].getId(), goeis[i].getSprite());
						board.gb_moveSpriteCoord(goeis[i].getId(), goeis[i].getX(), goeis[i].getY());
					}
				} else {
					if (counter_goeis <= 25 + 2 * (i - goeis.length / 2)) {
						goeis[i].move(Constants.DIR_N, 1);
					} else if (counter_goeis > 25 + 2 * (i - goeis.length / 2)
							&& counter_goeis <= 55 + 2 * (i - goeis.length / 2)) {
						goeis[i].move(Constants.DIR_WNW, 1);
					} else if (counter_goeis > 55 + 2 * (i - goeis.length / 2)
							&& counter_goeis <= 65 + 2 * (i - goeis.length / 2)) {
						int nextDir = goeis[i].getDirection() - 1;
						if (nextDir == 0) {
							nextDir = 15;
						}
						goeis[i].move(nextDir, 1);
					} else if (counter_goeis > 65 + 2 * (i - goeis.length / 2)
							&& counter_goeis <= 85 + 2 * (i - goeis.length / 2)) {
						goeis[i].move(Constants.DIR_ENE, 1);
					} else if (counter_goeis > 85 + 2 * (i - goeis.length / 2)
							&& counter_goeis <= 89 + 2 * (i - goeis.length / 2)) {
						int nextDir = goeis[i].getDirection() - 1;
						if (nextDir == 0) {
							nextDir = 15;
						}
						goeis[i].move(nextDir, 1);
					} else if (counter_goeis > 89 + 2 * (i - goeis.length / 2)
							&& counter_goeis < 99 + 2 * (i - goeis.length / 2)) {
						goeis[i].move(Constants.DIR_NNW, 1);
					} else if (goeis[i].getHealth() > 0) {
						goeis[i].setX(Constants.enemies_positions_X[level - 1][1][i]);
						goeis[i].setY(Constants.enemies_positions_Y[level - 1][1][i]);
						goeis[i].setDirection(Constants.DIR_N);
						goeis[i].setSprite("enemy2.png");
						board.gb_setSpriteImage(goeis[i].getId(), goeis[i].getSprite());
						board.gb_moveSpriteCoord(goeis[i].getId(), goeis[i].getX(), goeis[i].getY());
					}
					if (counter_goeis < 99 + 2 * (i - goeis.length / 2)) {
						board.gb_setSpriteImage(goeis[i].getId(), goeis[i].getSprite());
						board.gb_moveSpriteCoord(goeis[i].getId(), goeis[i].getX(), goeis[i].getY());
					}
				}

			}
		}
	}

	// Metodo para posicionar al inicio a los zakos
	public void setZakosPosition() {
		for (int i = 0; i < zakos.length; i++) {
			if (i < zakos.length / 2) {
				zakos[i].setX(-5 - i * 10);
			} else {
				zakos[i].setX(200 + (i - zakos.length / 2) * 10);

			}
			zakos[i].setY(140);

			board.gb_setSpriteImage(zakos[i].getId(), zakos[i].getSprite());
			board.gb_moveSpriteCoord(zakos[i].getId(), zakos[i].getX(), zakos[i].getY());
		}
	}

	// Metodo para las entradas de los zakos
	public void zakosEntrance() {
		for (int i = 0; i < getZakos().length; i++) {
			if (zakos[i].isAlive() == true) {
				if (i < zakos.length / 2) {
					if (counter_zakos <= 35 + 2 * i) {
						zakos[i].move(Constants.DIR_E, 1);
					} else if ((counter_zakos > 35 + 2 * i && counter_zakos <= 75 + 2 * i)) {
						int nextDir = zakos[i].getDirection() + 1;
						if (nextDir == 15) {
							nextDir = 0;
						}
						zakos[i].move(nextDir, 1);
					} else if (counter_zakos > 75 + 2 * i && counter_zakos <= 85 + 2 * i) {
						zakos[i].move(Constants.DIR_NW, 1);
					} else if (counter_zakos > 85 + 2 * i && counter_zakos <= 90 + 2 * i) {
						zakos[i].move(Constants.DIR_NNE, 1);
					} else if (counter_zakos > 90 + 2 * i && counter_zakos < 102 + 2 * i) {
						zakos[i].move(Constants.DIR_NNW, 1);
					} else if (counter_zakos == 102 + 2 * i) {
						zakos[i].setDirection(Constants.DIR_N);
					} else if (zakos[i].getHealth() > 0) {
						zakos[i].setSprite("enemy3.png");
						zakos[i].setX(Constants.enemies_positions_X[level - 1][2][i]);
						zakos[i].setY(Constants.enemies_positions_Y[level - 1][2][i]);
						board.gb_setSpriteImage(zakos[i].getId(), zakos[i].getSprite());
						board.gb_moveSpriteCoord(zakos[i].getId(), zakos[i].getX(), zakos[i].getY());
					}
					if (counter_zakos <= 102 + 2 * i) {
						board.gb_setSpriteImage(zakos[i].getId(), zakos[i].getSprite());
						board.gb_moveSpriteCoord(zakos[i].getId(), zakos[i].getX(), zakos[i].getY());
					}
				} else {
					if (counter_zakos <= 35 + 2 * (i - zakos.length / 2)) {
						zakos[i].move(Constants.DIR_W, 1);
					} else if (counter_zakos > 35 + 2 * (i - zakos.length / 2)
							&& counter_zakos <= 75 + 2 * (i - zakos.length / 2)) {
						int nextDir = zakos[i].getDirection() - 1;
						if (nextDir == 0) {
							nextDir = 15;
						}
						zakos[i].move(nextDir, 1);
					} else if (counter_zakos > 75 + 2 * (i - zakos.length / 2)
							&& counter_zakos <= 85 + 2 * (i - zakos.length / 2)) {
						zakos[i].move(Constants.DIR_NE, 1);
					} else if (counter_zakos > 85 + 2 * (i - zakos.length / 2)
							&& counter_zakos <= 90 + 2 * (i - zakos.length / 2)) {
						zakos[i].move(Constants.DIR_NNW, 1);
					} else if (counter_zakos > 90 + 2 * (i - zakos.length / 2)
							&& counter_zakos < 102 + 2 * (i - zakos.length / 2)) {
						zakos[i].move(Constants.DIR_NNE, 1);
					} else if (counter_zakos == 102 + 2 * (i - zakos.length / 2)) {
						zakos[i].setDirection(Constants.DIR_N);
					} else if (zakos[i].getHealth() > 0) {
						zakos[i].setSprite("enemy3.png");
						zakos[i].setX(Constants.enemies_positions_X[level - 1][2][i]);
						zakos[i].setY(Constants.enemies_positions_Y[level - 1][2][i]);
						board.gb_setSpriteImage(zakos[i].getId(), zakos[i].getSprite());
						board.gb_moveSpriteCoord(zakos[i].getId(), zakos[i].getX(), zakos[i].getY());
					}
					if (counter_zakos < 102 + 2 * (i - zakos.length / 2)) {
						board.gb_setSpriteImage(zakos[i].getId(), zakos[i].getSprite());
						board.gb_moveSpriteCoord(zakos[i].getId(), zakos[i].getX(), zakos[i].getY());
					}
				}
			}
		}
	}

	// Metodo que unifica las entradas de todos los enemigos
	public void enemiesEntrances() {
		if (counter_commanders <= 77 + commanders.length) {
			commanderEntrance();
			counter_commanders++;
		}
		if (counter_goeis <= 99 + goeis.length) {
			goeisEntrance();
			counter_goeis++;
		}
		if (counter_zakos <= 102 + zakos.length) {
			zakosEntrance();
			counter_zakos++;
		}
	}

	// Metodo para añadir todas las entidades del nivel simultaneamente
	public void addEntities() {
		this.addEnemiesSprites(commanders);
		this.addEnemiesSprites(goeis);
		this.addEnemiesSprites(zakos);
		this.addSprite(player);
	}

	// Metodo para colocar en sus posiciones iniciales a todos los enemigos
	public void setEnemiesPositions() {
		setCommandersPosition();
		setGoeisPosition();
		setZakosPosition();
	}

	// Metodo para el movimiento kamikaze de los zakos
	public void zakosAttack() {
		for (int i = 0; i < zakos.length; i++) {
			// Buscamos un zako que este vivo de la formacion y cuyo ataque sea true
			if (zakos[i].doIAttack() == true && zakos[i].isMoving() == false && zakos[i].isAlive() == true) {
				// Lo ponemos en movimiento y le buscamos una ruta
				zakos[i].setMoving(true);
				zakos[i].setRandom_route((int) ((Math.random() * 3)));
			}
			if (zakos[i].isMoving() == true) {
				// Lo movemos siempre y cuando no haya salido de la pantalla
				if (zakos[i].isReach_limit() == false) {
					zakos[i].followPath(Constants.zakos_kamikaze[zakos[i].getRandom_route()]);
					zakos[i].setSprite("enemy3.png");
					board.gb_setSpriteImage(zakos[i].getId(), zakos[i].getSprite());
					board.gb_moveSpriteCoord(zakos[i].getId(), zakos[i].getX(), zakos[i].getY());
					// Si sale de la pantalla le reposicionamos en el escuadron
				} else if (zakos[i].isReach_limit() == true) {
					findSquad(Constants.zigzag_Reset, zakos[i], zakos, "zako");
					zakos[i].setSprite("enemy3.png");
					board.gb_setSpriteImage(zakos[i].getId(), zakos[i].getSprite());
					board.gb_moveSpriteCoord(zakos[i].getId(), zakos[i].getX(), zakos[i].getY());
					// Cuando se recoloque en el escuadron reiniciamos sus valores a los inciales
					if (zakos[i].isReach_squad() == true) {
						zakos[i].setReach_limit(false);
						zakos[i].setReach_squad(false);
						zakos[i].setMoving(false);
					}

				}
			}
			// Comprobamos si ha salido de la pantalla
			if (zakos[i].getY() > 230) {
				zakos[i].setReach_limit(true);
				zakos[i].setY(-5);
			}
		}
	}

	// Metodo para el movimiento kamikaze de los goeis
	public void goeisAttack() {
		for (int i = 0; i < goeis.length; i++) {
			if (goeis[i].doIAttack() == true && goeis[i].isMoving() == false && goeis[i].isAlive() == true
					&& goeis[i].isRequested() == false) {
				goeis[i].setMoving(true);
				goeis[i].setRandom_route((int) ((Math.random() * 2)));
			}
			if (goeis[i].isMoving() == true && goeis[i].isRequested() == false) {
				if (goeis[i].isReach_limit() == false) {
					goeis[i].followPath(Constants.goeis_kamikaze[goeis[i].getRandom_route()]);
					goeis[i].setSprite("enemy2.png");
					board.gb_setSpriteImage(goeis[i].getId(), goeis[i].getSprite());
					board.gb_moveSpriteCoord(goeis[i].getId(), goeis[i].getX(), goeis[i].getY());
				} else if (goeis[i].isReach_limit() == true) {
					findSquad(Constants.zigzag_Reset, goeis[i], goeis, "goei");
					goeis[i].setSprite("enemy2.png");
					board.gb_setSpriteImage(goeis[i].getId(), goeis[i].getSprite());
					board.gb_moveSpriteCoord(goeis[i].getId(), goeis[i].getX(), goeis[i].getY());
					if (goeis[i].isReach_squad() == true) {
						goeis[i].setReach_limit(false);
						goeis[i].setReach_squad(false);
						goeis[i].setMoving(false);
					}

				}
			}
			if (goeis[i].getY() > 230 && goeis[i].isRequested() == false) {
				goeis[i].setReach_limit(true);
				goeis[i].setY(-5);
			}
		}
	}

	// Metodo para el movimiento kamikaze de los commanders
	public void commandersAttack() {
		// Comprobamos si ya hay otro comandante fuera del escuadron, si ya lo hay no
		// dejamos que este salga tambien
		for (int i = 0; i < commanders.length; i++) {
			if (commanders[i].isMoving() == true || commanders[i].isAlive() == false)
				commander_out = true;
		}
		for (int i = 0; i < commanders.length; i++) {
			if (commanders[i].doIAttack() == true && commanders[i].isMoving() == false && commander_out == false
					&& commanders[i].getHealth() > 0) {
				// Llamamos a un goei para que escolte al comandante
				claimGuard();
				commander_out = true;
				commanders[i].setMoving(true);
			}
			if (commanders[i].isMoving() == true) {
				if (commanders[i].isReach_limit() == false) {
					commanders[i].followPath(Constants.commanders_kamikaze);
					if (commanders[i].getHealth() == 2) {
						commanders[i].setSprite("enemy1.png");
					}
					if (commanders[i].getHealth() == 1) {
						commanders[i].setSprite("enemy9.png");
					}
					board.gb_setSpriteImage(commanders[i].getId(), commanders[i].getSprite());
					board.gb_moveSpriteCoord(commanders[i].getId(), commanders[i].getX(), commanders[i].getY());
				} else if (commanders[i].isReach_limit() == true) {
					this.findSquad(Constants.zigzag_Reset, commanders[i], commanders, "commander");
					commander_out = false;
					if (commanders[i].getHealth() == 2) {
						commanders[i].setSprite("enemy1.png");
					}
					if (commanders[i].getHealth() == 1) {
						commanders[i].setSprite("enemy9.png");
					}
					board.gb_setSpriteImage(commanders[i].getId(), commanders[i].getSprite());
					board.gb_moveSpriteCoord(commanders[i].getId(), commanders[i].getX(), commanders[i].getY());
					if (commanders[i].isReach_squad() == true) {
						commanders[i].setReach_limit(false);
						commanders[i].setReach_squad(false);
						commanders[i].setMoving(false);
					}

				}
			}
			if (commanders[i].getY() > 230) {
				commanders[i].setReach_limit(true);
				commanders[i].setY(-5);
			}
		}
	}

	// Metodo que asocia un guardian al commandante cuando este inicia su ataque
	public void claimGuard() {
		for (int i = 0; i < goeis.length; i++) {
			if (goeis[goeis.length / 2].isMoving() == false && guard_found == false) {
				guard_found = true;
				goeis[goeis.length / 2].setRequested(true);
			} else if (goeis[(goeis.length / 2) - 1].isMoving() == false && guard_found == false) {
				guard_found = true;
				goeis[(goeis.length / 2) - 1].setRequested(true);
			}
		}
	}

	// Metodo para el movimiento de escolta que realizan los goeis cuando los
	// commanders atacan
	public void guardAttack() {
		for (int i = 0; i < goeis.length; i++) {
			if (goeis[i].isRequested() == true) {
				goeis[i].setMoving(true);
				if (goeis[i].isMoving() == true) {
					if (goeis[i].isReach_limit() == false) {
						goeis[i].followPath(Constants.commanders_kamikaze);
						goeis[i].setSprite("enemy2.png");
						board.gb_setSpriteImage(goeis[i].getId(), goeis[i].getSprite());
						board.gb_moveSpriteCoord(goeis[i].getId(), goeis[i].getX(), goeis[i].getY());
					} else if (goeis[i].isReach_limit() == true) {
						this.findSquad(Constants.zigzag_Reset, goeis[i], goeis, "goei");
						goeis[i].setSprite("enemy2.png");
						board.gb_setSpriteImage(goeis[i].getId(), goeis[i].getSprite());
						board.gb_moveSpriteCoord(goeis[i].getId(), goeis[i].getX(), goeis[i].getY());
						if (goeis[i].isReach_squad() == true) {
							goeis[i].setReach_limit(false);
							goeis[i].setReach_squad(false);
							goeis[i].setMoving(false);
							goeis[i].setRequested(false);
							guard_found = false;
						}

					}
				}
				if (goeis[i].getY() > 230) {
					goeis[i].setReach_limit(true);
					goeis[i].setY(-5);
				}
			}
		}
	}

	// Metodo que busca el enemigo mas proximo del escuadron
	public void findPartner(Enemy enemy, Enemy[] partners, String type) {
		// Switch que controla que enemigo buscar
		switch (type) {
		case "zako":
			for (int i = 0; i < partners.length && (enemy.isMoving() == true); i++) {
				// Dependiendo del sentido tenemos que buscar un compañero que tenga un hueco a
				// su izquierda o a su derecha
				if (sense == 0) {
					// Si estamos en los limites forzamos la busqueda de un hueco al lado contrario
					// del sentido para no romper la formacion
					if (i==0) {
						if ((partners[i].isMoving() == false && partners[i].getHealth() != 0)
								&& (partners[i + 1].isMoving() == true || partners[i + 1].getHealth() == 0)) {
							enemy.setX(partners[i].getX() + 10);
							enemy.setY(partners[i].getY());
							enemy.setDirection(Constants.DIR_N);
							enemy.setMoving(false);
						} else
							continue;

					} else {
						if ((partners[i].isMoving() == false && partners[i].getHealth() != 0)
								&& (partners[i - 1].isMoving() == true || partners[i - 1].getHealth() == 0)) {
							// Posicionamos al enemigo al lado de su compañero, en el hueco encontrado
							enemy.setX(partners[i].getX() - 10);
							enemy.setY(partners[i].getY());
							enemy.setDirection(Constants.DIR_N);
							enemy.setMoving(false);
						} else
							continue;
					}

				}
				if (sense == 1) {
					if (i == partners.length-1) {
						if (partners[i].isMoving() == false
								&& (partners[i - 1].isMoving() == true || partners[i - 1].getHealth() == 0)) {
							enemy.setX(partners[i].getX() - 10);
							enemy.setY(partners[i].getY());
							enemy.setDirection(Constants.DIR_N);
							enemy.setMoving(false);
						} else
							continue;

					} else {
						if (partners[i].isMoving() == false
								&& (partners[i + 1].isMoving() == true || partners[i + 1].getHealth() == 0)) {
							enemy.setX(partners[i].getX() + 10);
							enemy.setY(partners[i].getY());
							enemy.setDirection(Constants.DIR_N);
							enemy.setMoving(false);
						} else
							continue;

					}

				}
			}
			break;
		case "goei":
			for (int i = 0; i < partners.length && (enemy.isMoving() == true); i++) {
				if (sense == 0) {
					if (partners[i].isMoving() == false && partners[i].getHealth() != 0) {
						if (i==0) {
							if ((partners[i + 1].isMoving() == true || partners[i + 1].getHealth() == 0)) {
								enemy.setX(partners[i].getX() + 20);
								enemy.setY(partners[i].getY());
								enemy.setDirection(Constants.DIR_N);
								enemy.setMoving(false);
							} else
								continue;

						} else {
							if ((partners[i].isMoving() == false && partners[i].getHealth() != 0)
									&& (partners[i - 1].isMoving() == true || partners[i - 1].getHealth() == 0)) {
								switch (i % 2) {
								case 0:
									enemy.setX(partners[i].getX() - 10);
									break;
								default:
									enemy.setX(partners[i].getX() - 20);
									break;
								}
								enemy.setY(partners[i].getY());
								enemy.setDirection(Constants.DIR_N);
								enemy.setMoving(false);
							} else
								continue;
						}
					}
				}
				if (sense == 1) {
					if (partners[i].isMoving() == false && partners[i].getHealth() != 0) {
						if (i == partners.length-1) {
							if ((partners[i - 1].isMoving() == true || partners[i - 1].getHealth() == 0)) {
								enemy.setX(partners[i].getX() - 20);
								enemy.setY(partners[i].getY());
								enemy.setDirection(Constants.DIR_N);
								enemy.setMoving(false);
							} else
								continue;

						} else {
							if (partners[i].isMoving() == false
									&& (partners[i + 1].isMoving() == true || partners[i + 1].getHealth() == 0)) {
								switch (i % 2) {
								case 0:
									enemy.setX(partners[i].getX() + 20);
									break;
								default:
									enemy.setX(partners[i].getX() + 10);
									break;
								}
								enemy.setY(partners[i].getY());
								enemy.setDirection(Constants.DIR_N);
								enemy.setMoving(false);
							} else
								continue;

						}

					}
				}
			}
			break;
		case "commander":
			for (int i = 0; i < partners.length && (enemy.isMoving() == true); i++) {
				if (sense == 0) {
					if (i==0) {
						if ((partners[i].isMoving() == false && partners[i].getHealth() != 0)
								&& (partners[i + 1].isMoving() == true || partners[i + 1].getHealth() == 0)) {
							enemy.setX(partners[i].getX() + 10);
							enemy.setY(partners[i].getY());
							enemy.setDirection(Constants.DIR_N);
							enemy.setMoving(false);
						} else
							continue;

					} else {
						if ((partners[i].isMoving() == false && partners[i].getHealth() != 0)
								&& (partners[i - 1].isMoving() == true || partners[i - 1].getHealth() == 0)) {
							enemy.setX(partners[i].getX() - 10);
							enemy.setY(partners[i].getY());
							enemy.setDirection(Constants.DIR_N);
							enemy.setMoving(false);
						} else
							continue;
					}

				}
				if (sense == 1) {
					if (i == partners.length-1) {
						if (partners[i].isMoving() == false
								&& (partners[i - 1].isMoving() == true || partners[i - 1].getHealth() == 0)) {
							enemy.setX(partners[i].getX() - 10);
							enemy.setY(partners[i].getY());
							enemy.setDirection(Constants.DIR_N);
							enemy.setMoving(false);
						} else
							continue;

					} else {
						if (partners[i].isMoving() == false
								&& (partners[i + 1].isMoving() == true || partners[i + 1].getHealth() == 0)) {
							enemy.setX(partners[i].getX() + 10);
							enemy.setY(partners[i].getY());
							enemy.setDirection(Constants.DIR_N);
							enemy.setMoving(false);
						} else
							continue;

					}

				}
			}
			break;
		}

	}

	// Metodo que devuelve la posicion del escuadron en el eje Y
	public void findSquad(int[][] path, Enemy enemy, Enemy[] partners, String type) {
		// Condicinal que comprueba que el enemigo necestia recolocarse
		if (enemy.isReach_squad() == false) {
			// Le hacemos recorrer un camino preestablecido
			if (enemy.getIndexPath() >= path.length)
				enemy.setIndexPath(0);
			if (enemy.getCounterPath() == path[enemy.getIndexPath()][1]) {
				enemy.setIndexPath(enemy.getIndexPath() + 1);
				enemy.setCounterPath(0);
			}
			if (enemy.getIndexPath() < path.length) {
				enemy.move(path[enemy.getIndexPath()][0], 1);
			}
			// Comprobamos si el enemigo ha alcanzado la altura del enjambre
			for (int i = 0; i < partners.length; i++) {
				if (partners[i].isMoving() == false
						&& (((enemy.getY() <= partners[i].getY() + 5)) && (enemy.getY() >= partners[i].getY() - 5))) {
					// Lo recolocamos
					findPartner(enemy, partners, type);
					enemy.setReach_squad(true);
				}
			}
			enemy.setCounterPath(enemy.getCounterPath() + 1);
			// Cuando conseguimos recolocar al enemigo reseteamos sus valores de path
			if (enemy.getIndexPath() == path.length - 1 || enemy.isReach_squad() == true) {
				enemy.setCounterPath(0);
				enemy.setIndexPath(0);
			}
		}
	}

	// Metodo que unifica el ataque de todos los enemigos
	public void enemiesAttack() {
		if (counter_zakos > 102 + zakos.length) {
			zakosAttack();
		}
		if (counter_goeis > 99 + goeis.length) {
			goeisAttack();
			commandersAttack();
			guardAttack();
		}
		if (time % 10 == 0) {
			enemiesTorpedos();
		}
	}

	// Metodo que comprueba si algun enemigo ha impactado en el jugador
	public void enemiesHit() {
		for (int i = 0; i < commanders.length; i++) {
			if (hit(commanders[i], player) == true && player.isGodMode() == false)
				player.setHealth(0);
		}
		for (int i = 0; i < goeis.length; i++) {
			if (hit(goeis[i], player) == true && player.isGodMode() == false)
				player.setHealth(0);
		}
		for (int i = 0; i < zakos.length; i++) {
			if (hit(zakos[i], player) == true && player.isGodMode() == false)
				player.setHealth(0);
		}
	}

	// Metodo para animar la explosion de los enemigos cuando estos mueren
	public void enemiesDeath(Enemy[] enemies) {
		for (int i = 0; i < enemies.length; i++) {
			// Aumentamos el contador del enemigo
			if (enemies[i].isAlive() == false) {
				if (enemies[i].getCounter_explosion() <= 4) {
					enemies[i].setCounter_explosion(enemies[i].getCounter_explosion() + 1);
				}
				// Dependiendo de cuando valga el contador de explosion mostramos un sprite u
				// otro
				switch (enemies[i].getCounter_explosion()) {
				case 0:
					board.gb_setSpriteImage(enemies[i].getId(), "explosion20.png");
					break;
				case 1:
					board.gb_setSpriteImage(enemies[i].getId(), "explosion21.png");
					break;
				case 2:
					board.gb_setSpriteImage(enemies[i].getId(), "explosion22.png");
					break;
				case 3:
					board.gb_setSpriteImage(enemies[i].getId(), "explosion23.png");
					break;
				case 4:
					board.gb_setSpriteImage(enemies[i].getId(), "explosion24.png");
					break;
				case 5:
					enemies[i].move(Constants.DIR_N, 100);
					board.gb_moveSpriteCoord(enemies[i].getId(), enemies[i].getX(), enemies[i].getY());
					break;
				}
			}
		}
	}

	// Metodo para animar la explosion del jugador cuando este muere
	public void playerDeath() {
		if (player.getHealth() == 0) {
			if (player.getCounter_explosion() <= 40) {
				player.setCounter_explosion(player.getCounter_explosion() + 1);
			}
			if (player.getCounter_explosion() == 0) {
				board.gb_setSpriteImage(player.getId(), "explosion11.png");
			}
			if (player.getCounter_explosion() == 10) {
				board.gb_setSpriteImage(player.getId(), "explosion12.png");
			}
			if (player.getCounter_explosion() == 20) {
				board.gb_setSpriteImage(player.getId(), "explosion13.png");
			}
			if (player.getCounter_explosion() == 30) {
				board.gb_setSpriteImage(player.getId(), "explosion14.png");
			}
			if (player.getCounter_explosion() == 35) {
				board.gb_setSpriteVisible(player.getId(), false);
			}
		}
	}

	// Metodo que unifica las explosiones de los enemigos
	public void enemiesDeaths() {
		enemiesDeath(commanders);
		enemiesDeath(goeis);
		enemiesDeath(zakos);
	}
}
