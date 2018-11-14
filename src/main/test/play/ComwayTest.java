package play;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class ComwayTest {

	@Test
	public void testLiveCelWithFewerThan2LivingNeighbours() {
		Cell cell = new Cell();
		assertEquals(false, cell.isAliveOnNextGen(1));
	}

	@Test
	public void testLiveCellWith2LivingNeighbours() {
		Cell cell = new Cell();
		cell.setCurrentState(CellState.ALIVE);
		assertEquals(true, cell.isAliveOnNextGen(2));
	}

	@Test
	public void testLiveCellWith3LivingNeighbours() {
		Cell cell = new Cell();
		cell.setCurrentState(CellState.ALIVE);
		assertEquals(true, cell.isAliveOnNextGen(3));
	}

	@Test
	public void testLiveCellWithMoreThan3LivingNeighbours() {
		Cell cell = new Cell();
		assertEquals(false, cell.isAliveOnNextGen(4));
	}

	@Test
	public void testDeadCellWith3AliveNeighbours() {
		Cell cell = new Cell();
		cell.setCurrentState(CellState.DEAD);
		assertEquals(true, cell.isAliveOnNextGen(3));
	}

	@Test
	public void testRandomNumber() {
		Random r = new Random();

		System.out.println(r.nextInt(50));
	}
}
