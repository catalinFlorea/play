package play2;

public class Main {
	public void displayBoard(Board board) {
      Cell[][] grid = board.getGrid();
      
      String border = String.format("+%0" + 2*grid.length + "d+", 0).replace("0","-");
      
      System.out.println(border);
      
      for (Cell[] row : grid) {
          String r = "|";
          for (Cell c : row) {
              r += c.getState() ? "* " : "  ";
          }
          r += "|";
          System.out.println(r);
      }
      
      System.out.println(border);
  }
}
