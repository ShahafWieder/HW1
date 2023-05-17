import java.util.Arrays;

/**
 * Board class, Represents a 2D array of tiles.
 */
public class Board {
    private  Tile [][] tiles;

    /**
     * Constructs a Board object based on the provided boardString.
     *
     * @param boardString a string representation of the board, where each tile value is separated by a space
     *                    and each row is separated by a "|" character. Use "_" to represent the empty tile.
     *                    Example: "1 2 3|4 5 6|7 8 _"
     */
    public Board(String boardString) {
        String[] rows = boardString.split("\\|");
        int numRows = rows.length;
        if (numRows > 0) {
            String[] columns = rows[0].trim().split(" ");
            int numCols = columns.length;
            tiles = new Tile[numRows][numCols];

            for (int i = 0; i < numRows; i++) {
                columns = rows[i].trim().split(" ");
                for (int j = 0; j < numCols; j++) {
                    if (columns[j].equals("_")) {
                        tiles[i][j] = new Tile(0);
                    } else {
                        int number = Integer.parseInt(columns[j]);
                        tiles[i][j] = new Tile(number);
                    }
                }
            }
        }
    }


    /**
     * Constructs a Board object based on the provided 2D array of tiles.
     *
     * @param tiles a 2D array representing the tiles of the board. Each element of the array
     *              represents a tile on the board.
     *              Example: tiles[0][0] represents the top-left tile.
     *                       tiles[2][1] represents the tile in the third row and second column.
     */
    public  Board(Tile[][]tiles){//second constructor
        this.tiles=tiles;
    }

    /**
     * Creates and returns a copy of the current board.
     *
     * @Return a new 2D array representing a copy of the board, with the same tile values as the original board.
     */
    public  Tile[][] copyBoard(){
        int rows=this.getRows(),columns=this.getColumns();
        Tile[][] temp=new Tile[rows][columns];
        for (int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                temp[i][j]=new Tile(this.getTileValue(i,j));
            }
        }
        return temp;
    }

    /**
     * Returns the number of rows in the board.
     *
     * @Return the number of rows in the board.
     */
    public int getRows() {
     return tiles.length;
    }

    /**
     * Returns the number of columns in the board.
     *
     * @Return the number of columns in the board.
     */
    public int getColumns() {
        return tiles[0].length;
    }

    /**
     * Returns the value of the tile at the specified row and column.
     *
     * @param i the row index of the tile.
     * @param j the column index of the tile.
     * @Return the value of the tile at the specified position.
     */
    public int getTileValue(int i, int j) {
        return tiles[i][j].getValue();
    }

    /**
     * Returns the location of the tile with the specified value.
     *
     * @param value the value of the tile to search for.
     * @Return an array of two integers representing the row and column indices of the tile,
     *         or null if the tile is not found on the board.
     */
    public int[] getTileLocation(int value) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j].getValue() == value) {
                    return new int[] {i, j};
                }
            }
        }
        // tile not found
        return null;
    }

    /**
     * Returns the tile at the specified row and column.
     *
     * @param i the row index of the tile.
     * @param j the column index of the tile.
     * @Return the tile at the specified position if the indices are valid, otherwise returns the tile at (0, 0).
     */
    public Tile getTile(int i, int j) {
        if (isValidTile(i, j)) {
            return tiles[i][j];
        } else {
            // Handle the case when the indices are out of bounds
            return tiles[0][0];
        }
    }

    /**
     * Switches the positions of two tiles on the board.
     *
     * @param row1 the row index of the first tile.
     * @param col1 the column index of the first tile.
     * @param row2 the row index of the second tile.
     * @param col2 the column index of the second tile.
     */
    public void switchTiles(int row1, int col1, int row2, int col2) {
        Tile temp = this.tiles[row1][col1];
        this.tiles[row1][col1] = this.tiles[row2][col2];
        this.tiles[row2][col2] = temp;
    }

    /**
     * Checks if the specified row and column indices are valid tile positions on the board.
     *
     * @param row the row index to check.
     * @param col the column index to check.
     * @Return true if the indices are within the valid range, false otherwise.
     */
    public boolean isValidTile(int row, int col) {
        return row >= 0 && row < tiles.length && col >= 0 && col < tiles[row].length;
    }

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
