package Game;
/* Programa que simula el famoso juego Galaga
 * @Author Carlos Fernandez Carchenilla y Daniel Cano Robledo
 * @since November 2018
 * @Version 1.26
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		//Creamos el nivel y lo ejecutamos
		Level level = new Level();
		level.runLevel();
	}
}
