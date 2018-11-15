package play3;

import java.util.ArrayList;
import java.util.Random;

/*
consider the following diagram
grid[i - 1][j - 1] . grid[i - 1][j] .  grid[i - 1][j + 1]
grid[i][j - 1]     .       x        .  grid[i][j + 1]
grid[i + 1][j - 1] . grid[i + 1][j] .  grid[i + 1][j + 1]
where x is the current cell in the matrix
here's a beautiful glider
String[][] grid = {
  {".", ".", ".", ".", ".", "."},
  {".", ".", "@", ".", ".", "."},
  {".", ".", ".", "@", ".", "."},
  {".", "@", "@", "@", ".", "."},
  {".", ".", ".", ".", ".", "."},
  {".", ".", ".", ".", ".", "."},
  {".", ".", ".", ".", ".", "."},
  {".", ".", ".", ".", ".", "."},
  {".", ".", ".", ".", ".", "."},
};
*/

public class GOL {
	private static final String LIFE = "@";
	private static final String DEATH = ".";

	public static void main(String... args) {

		String[][] grid = generateGrid(20, 20);

		int gen = 0;

		while (true) {
			clearScreen();
			printGrid(grid);
			System.out.println("Gen " + (gen++) + "");

			String[][] nextGenerationGrid = copyGrid(grid);

			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					// System.out.println(grid[i][j] + " " + i + ":" + j);

					int livingNeighbors = getLivingNeighborsOfCell(grid, i, j);

					if (grid[i][j].equals(DEATH)) {
						if (livingNeighbors == 3) {
							nextGenerationGrid[i][j] = LIFE;
						}
					} else {
						if (livingNeighbors < 2 || livingNeighbors > 3) {
							nextGenerationGrid[i][j] = DEATH;
						}
					}
				}
			}

			// copy grid with new generations to the one
			// previously created
			grid = copyGrid(nextGenerationGrid);

			// wait 200 milliseconds before repeating the loop
			try {
				Thread.sleep(200);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}

	private static int getLivingNeighborsOfCell(String[][] grid, int i, int j) {
		int livingNeighbors = 0;
		ArrayList<String> neighbors = new ArrayList<String>();

		// find the neighbor cells to the current one.
		// the grid closes in itself due to the modulus operator
		neighbors.add(grid[Math.floorMod(i - 1, grid.length)][Math.floorMod(j - 1, grid[i].length)]);
		neighbors.add(grid[Math.floorMod(i - 1, grid.length)][j]);
		neighbors.add(grid[Math.floorMod(i - 1, grid.length)][Math.floorMod(j + 1, grid[i].length)]);
		neighbors.add(grid[i][Math.floorMod(j - 1, grid[i].length)]);
		neighbors.add(grid[i][Math.floorMod(j + 1, grid[i].length)]);
		neighbors.add(grid[Math.floorMod(i + 1, grid.length)][Math.floorMod(j - 1, grid[i].length)]);
		neighbors.add(grid[Math.floorMod(i + 1, grid.length)][j]);
		neighbors.add(grid[Math.floorMod(i + 1, grid.length)][Math.floorMod(j + 1, grid[i].length)]);

		for (String neighbor : neighbors) {
			if (neighbor.equals(LIFE))
				livingNeighbors += 1;
		}
		return livingNeighbors;
	}

	private static String[][] copyGrid(String[][] grid) {
		String[][] tempGrid = new String[grid.length][grid[0].length];

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				tempGrid[i][j] = grid[i][j];
			}
		}

		return tempGrid;
	}

	public static String[][] generateGrid(int width, int height) {
		String[][] randomGrid = new String[height][width];
		Random rand = new Random();

		for (int i = 0; i < randomGrid.length; i++) {
			for (int j = 0; j < randomGrid[i].length; j++) {
				Boolean god = rand.nextBoolean();

				if (god)
					randomGrid[i][j] = LIFE;
				else
					randomGrid[i][j] = DEATH;
			}
		}

		return randomGrid;
	}

	private static void clearScreen() {
		for (int i = 0; i < 100; i++)
			System.out.println();
	}

	public static void printGrid(String[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(" " + matrix[i][j] + " ");
			}

			System.out.println();
		}
	}
}
