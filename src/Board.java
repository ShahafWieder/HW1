import java.util.Arrays;
public class Board {
    private  Tile [][] tiles;
    private  String board;
    public Board(String boardString) {
        this.board=boardString;
        String[] rows = board.split("\\|");
        tiles = new Tile[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            String[] columns = rows[i].trim().split(" ");
            tiles[i] = new Tile[columns.length];
            for (int j = 0; j< columns.length; j++){
                if (columns[j].equals("_")){
                    tiles[i][j] = new Tile(0);
                }
                else{
                    int number = Integer.parseInt(columns[j]);
                    tiles[i][j] = new Tile(number);
                }
            }
        }
    }
public int getRows() {
 return tiles.length;
}
    public int getColumns() {
        return tiles[0].length;
    }
    public int getTileValue(int i, int j) {
        return tiles[i][j].getValue();
    }


    //  public String[][] getTiles() {
      //  return this.tiles;
    //}
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }
}
