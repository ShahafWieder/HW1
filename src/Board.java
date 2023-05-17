import java.util.Arrays;
public class Board {
    private  Tile [][] tiles;
    private  String board;
    private int rows;
    private int columns;
    public Board(String boardString) {
        this.board = boardString;
        String[] rows = board.split("\\|");
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



    public  Board(Tile[][]tiles){//second constructor
        this.tiles=tiles;
        this.rows=tiles.length;
        this.columns=tiles[0].length;
    }
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
    public int getRows() {
     return tiles.length;//maybe add -1
    }
    public int getColumns() {
        return tiles[0].length; //maybe add -1
    }
    public int getTileValue(int i, int j) {
        return tiles[i][j].getValue();
    }
    public Tile getTile(int i, int j) {
        if (isValidTile(i, j)) {
            return tiles[i][j];
        } else {
            // Handle the case when the indices are out of bounds
            return tiles[0][0];
        }
    }
    public String getstring(){
        return this.board;
    }

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
    public void switchTiles(int row1, int col1, int row2, int col2) {
        Tile temp = this.tiles[row1][col1];
        this.tiles[row1][col1] = this.tiles[row2][col2];
        this.tiles[row2][col2] = temp;
    }

    public boolean isValidTile(int row, int col) {
        return row >= 0 && row < tiles.length && col >= 0 && col < tiles[row].length; //maybe add -1 when .length
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
