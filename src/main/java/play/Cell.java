package play;

public class Cell {
	private CellState currentState;

	public boolean isAliveOnNextGen(int nrOfLivingNeighbours) {
		if (currentState == CellState.ALIVE && nrOfLivingNeighbours < 2 || nrOfLivingNeighbours > 3) {
			return false;
		}

		if (currentState == CellState.ALIVE && (nrOfLivingNeighbours == 2 || nrOfLivingNeighbours == 3)) {
			return true;
		}

		if (currentState == CellState.DEAD && nrOfLivingNeighbours == 3) {
			return true;
		}

		return false;
	}

	public void setCurrentState(CellState currentState) {
		this.currentState = currentState;
	}

	public CellState getCurrentState(CellState alive) {
		return currentState;
	}
}
