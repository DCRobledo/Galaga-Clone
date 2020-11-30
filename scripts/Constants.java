package Game;
/*
 * Universidad Carlos III de Madrid (UC3M)
 * Programacion 2016-2017
 */

/**
 * Parameters and magic numbers to be used throughout the code.
 * @author Planning and Learning Group (PLG)
 */
public class Constants {

    
    //Movement directions
    public static final int DIR_N = 0;
    public static final int DIR_NNE = 1;
    public static final int DIR_NE = 2;
    public static final int DIR_ENE = 3;
    public static final int DIR_E = 4;
    public static final int DIR_ESE = 5;
    public static final int DIR_SE = 6;
    public static final int DIR_SSE = 7;
    public static final int DIR_S = 8;
    public static final int DIR_SSW = 9;
    public static final int DIR_SW = 10;
    public static final int DIR_WSW = 11;
    public static final int DIR_W = 12;
    public static final int DIR_WNW = 13;
    public static final int DIR_NW = 14;
    public static final int DIR_NNW = 15;

	// The move deltas for the directions: {dX,dY}
	public static final int[][] MOVES = {
	    { 0, -4 },
	    { 1, -4 },
	    { 3, -3 },
	    { 4, -1 },
	    { 4, 0 },
	    { 4, 1 },
	    { 3, 3 },
	    { 1, 4 },
	    { 0, 4 },
	    { -1, 4 },
	    { -3, 3 },
	    { -4, 1 },
	    {  -4, 0 },
	    { -4, -1 },
	    { -3, -3 },
	    { -1, -4 }
	 };
	//Posiciones iniciales en el eje X del escuadron para cada nivel
	public static int [][][] enemies_positions_X = new int [][][] {
		//Nivel 1
		{
			//Commanders
			{53, 65},
			//Goeis
			{26, 46, 56, 86, 96, 116},
			//Zakos
			{40, 50, 60, 70, 80, 90, 100, 110, 50, 60, 70, 80, 90, 100}
		},
		//Nivel 2
		{
			//Commanders
			{35, 49, 63},
			//Goeis
			{43, 53, 63, 73, 83, 33, 43, 53, 63, 73, 83, 93},
			//Zakos
			{23, 33, 43, 53, 63, 73, 83, 93, 103, 23, 33, 43, 53, 63, 73, 83, 93, 103}
		},
		//Nivel 3
		{
			//Commanders
			{41, 53, 65, 77, 89, 101},
			//Goeis
			{35, 45, 55, 65, 75, 85, 95, 105, 115, 125, 35, 45, 55, 65, 75, 85, 95, 105, 115, 125},
			//Zakos
			{35, 45, 55, 65, 75, 85, 95, 105, 115, 125, 135, 145, 45, 55, 65, 75, 85, 95, 105, 115, 125, 135, 55, 65, 75, 85, 95, 105, 115, 125}
		}
	};
	//Posiciones iniciales en el eje Y del escuadron para cada nivel
		public static int [][][] enemies_positions_Y = new int [][][] {
			//Nivel 1
			{
				//Commanders
				{20, 20},
				//Goeis
				{30, 30, 30, 30, 30, 30},
				//Zakos
				{40, 40, 40, 40, 40, 40, 40, 40, 50, 50, 50, 50, 50, 50}
			},
			//Nivel 2
			{
				//Commanders
				{20, 20, 20},
				//Goeis
				{30, 30, 30, 30, 30, 40, 40, 40, 40, 40, 40, 40},
				//Zakos
				{50, 50, 50, 50, 50, 50, 50, 50, 50, 60, 60, 60, 60, 60, 60, 60, 60, 60}
			},
			//Nivel 3
			{
				//Commanders
				{20, 20, 20, 20, 20, 20},
				//Goeis
				{30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40},
				//Zakos
				{50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 70, 70, 70, 70, 70, 70, 70, 70}
			}
		};
	//Movimiento kamikaze de los zakos
	public static int[][][] zakos_kamikaze= new int[][][] {
		{
			{Constants.DIR_SE, 8},
			{Constants.DIR_S, 2},
			{Constants.DIR_SW, 10},
			{Constants.DIR_WSW, 1},
			{Constants.DIR_W, 1},
			{Constants.DIR_WNW, 1},
			{Constants.DIR_NW, 1},
			{Constants.DIR_NNW, 1},
			{Constants.DIR_N, 1},
			{Constants.DIR_NNE, 1},
			{Constants.DIR_NE, 1},
			{Constants.DIR_ENE, 1},
			{Constants.DIR_E, 1},
			{Constants.DIR_ESE, 1},
			{Constants.DIR_SE, 1},
			{Constants.DIR_S, 2},
			{Constants.DIR_SW, 4},
			{Constants.DIR_SE, 6}
		},
		{
			{Constants.DIR_S, 4},
			{Constants.DIR_SE, 10},
			{Constants.DIR_SW, 4},
			{Constants.DIR_WSW, 1},
			{Constants.DIR_W, 1},
			{Constants.DIR_WNW, 1},
			{Constants.DIR_NW, 1},
			{Constants.DIR_NNW, 1},
			{Constants.DIR_N, 1},
			{Constants.DIR_NNE, 1},
			{Constants.DIR_NE, 1},
			{Constants.DIR_ENE, 1},
			{Constants.DIR_E, 1},
			{Constants.DIR_ESE, 1},
			{Constants.DIR_SE, 1},
			{Constants.DIR_SE, 7},
			{Constants.DIR_S, 4},
			{Constants.DIR_SW, 6}
		},
		{
			{Constants.DIR_SE, 8},
			{Constants.DIR_S, 2},
			{Constants.DIR_SW, 8},
			{Constants.DIR_S, 2},
			{Constants.DIR_SE, 8},
			{Constants.DIR_ESE, 1},
			{Constants.DIR_E, 1},
			{Constants.DIR_ENE, 1},
			{Constants.DIR_NE, 1},
			{Constants.DIR_NNE, 1},
			{Constants.DIR_N, 1},
			{Constants.DIR_NNW, 1},
			{Constants.DIR_NW, 1},
			{Constants.DIR_WNW, 1},
			{Constants.DIR_W, 1},
			{Constants.DIR_WSW, 1},
			{Constants.DIR_SW, 1},
			{Constants.DIR_SSW, 1},
			{Constants.DIR_S, 1},
			{Constants.DIR_SSE, 1},
			{Constants.DIR_SE, 4},
			{Constants.DIR_SW, 14},
			{Constants.DIR_S, 6},
			{Constants.DIR_SE, 10},
		}
		
	};
	//Movimiento kamikaze de los zakos
	public static int[][][] goeis_kamikaze= new int[][][] {
				{
					{Constants.DIR_W, 1},
					{Constants.DIR_WNW, 1},
					{Constants.DIR_NW, 1},
					{Constants.DIR_NNW, 1},
					{Constants.DIR_N, 1},
					{Constants.DIR_NNE, 1},
					{Constants.DIR_NE, 1},
					{Constants.DIR_ENE, 1},
					{Constants.DIR_E, 1},
					{Constants.DIR_ESE, 1},
					{Constants.DIR_SE, 1},
					{Constants.DIR_S, 2},
					{Constants.DIR_SW, 4},
					{Constants.DIR_S, 6},
					{Constants.DIR_SW, 8},
					{Constants.DIR_SE, 10},
					{Constants.DIR_S, 6},
					{Constants.DIR_ESE, 6}	
				},
				{
					{Constants.DIR_S, 4},
					{Constants.DIR_SE, 2},
					{Constants.DIR_ESE, 1},
					{Constants.DIR_E, 1},
					{Constants.DIR_ENE, 1},
					{Constants.DIR_NE, 1},
					{Constants.DIR_NNE, 1},
					{Constants.DIR_N, 1},
					{Constants.DIR_NNW, 1},
					{Constants.DIR_NW, 1},
					{Constants.DIR_WNW, 1},
					{Constants.DIR_W, 1},
					{Constants.DIR_WSW, 1},
					{Constants.DIR_SW, 1},
					{Constants.DIR_SSW, 1},
					{Constants.DIR_S, 1},
					{Constants.DIR_SSE, 1},
					{Constants.DIR_SW, 4},
					{Constants.DIR_SE, 7},
					{Constants.DIR_S, 4},
					{Constants.DIR_SW, 6},
					{Constants.DIR_SW, 8},
					{Constants.DIR_SE, 10},
					{Constants.DIR_S, 6},
					{Constants.DIR_ESE, 6}
				}
		};
	//Movimiento kamikaze de los commanders
	public static int[][] commanders_kamikaze= new int[][] {
		{Constants.DIR_W, 1},
		{Constants.DIR_WNW, 1},
		{Constants.DIR_NW, 1},
		{Constants.DIR_NNW, 1},
		{Constants.DIR_N, 1},
		{Constants.DIR_NNE, 1},
		{Constants.DIR_NE, 1},
		{Constants.DIR_ENE, 1},
		{Constants.DIR_E, 1},
		{Constants.DIR_ESE, 1},
		{Constants.DIR_S, 6},
		{Constants.DIR_SW, 8},
		{Constants.DIR_SE, 10},
		{Constants.DIR_S, 6},
		{Constants.DIR_ESE, 6},
		{Constants.DIR_SE, 6},
		{Constants.DIR_S, 4},
		{Constants.DIR_SW, 4},
		{Constants.DIR_E, 1},
		{Constants.DIR_ENE, 1},
		{Constants.DIR_NE, 1},
		{Constants.DIR_NNE, 1},
		{Constants.DIR_N, 1},
		{Constants.DIR_NNW, 1},
		{Constants.DIR_NW, 1},
		{Constants.DIR_WNW, 1},
		{Constants.DIR_W, 1},
		{Constants.DIR_WSW, 1},
		{Constants.DIR_SW, 1},
		{Constants.DIR_SSW, 1},
		{Constants.DIR_S, 6},
		{Constants.DIR_SW, 8},
		{Constants.DIR_SE, 10},
		{Constants.DIR_S, 6},
		{Constants.DIR_ESE, 6}	
	};
	//ZigZag que siguen los enemigos tras acabar el ataque
	public static int[][] zigzag_Reset = new int [][] {
		{Constants.DIR_SE, 12},
		{Constants.DIR_SW, 12},
		{Constants.DIR_S, 8},
		{Constants.DIR_SE, 12},
		{Constants.DIR_SW, 12},
		{Constants.DIR_SE, 12},
		{Constants.DIR_SW, 12},
		{Constants.DIR_S, 8},
		{Constants.DIR_SE, 12},
		{Constants.DIR_SW, 12},
	};
}


		

