import java.lang.module.ModuleFinder;

public class State {
    private Board board;
    //private EnumDirection Direction;
    public State(Board b) {
        this.board = b;
       // this.Direction = ed;
    }

    public boolean isGoal() {
        int counter = 1;
        int rows = board.getRows();
        int columns = board.getColumns();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board.getTileValue(i, j) != counter) {
                    if (i == rows - 1 && j == columns - 1 && board.getTileValue(i, j) == 0) {
                        // We're at the last tile, and it's empty, so it's still a valid goal state
                        return true;
                    } else {
                        // There's a tile out of place, so it's not a goal state
                        return false;
                    }
                }
                counter++;
            }
        }
        return true;
    }
    public Action[] actions() {
        int size = 0;
        int [] defaultDirection = {0,0,0,0};
        int counter = 0;
        int [] zeroLocation = this.board.getTileLocation(0);
        if(zeroLocation[1]-1 >= this.board.getColumns()){ // UP
            defaultDirection[0] = 1;
            counter++;
        }
        if(zeroLocation[1] +1 <= 0){ // DOWN
            defaultDirection[1] = 1;
            counter ++;
        }
        if(zeroLocation[0]-1 >= 0) { // RIGHT
            defaultDirection[2] = 1;
            counter++;
        }
        if(zeroLocation[0]+1 <= this.board.getRows()){ // LEFT
            defaultDirection[3] = 1;
            counter++;
        }
        Action [] actionOptions = new Action[counter];
        Direction [] temp = {Direction.UP, Direction.DOWN, Direction.RIGHT, Direction.LEFT};
        int[]dRow={1,-1,0,0};
        int[]dCol={0,0,-1,1};
        for(int i = 0; i < 4; i++){
            if(defaultDirection[i] == 0) continue;
            actionOptions[size] = new Action(this.board.getTile(zeroLocation[0]+dRow[i],zeroLocation[1]+dCol[i]), temp[i]);
            size ++;
        }
        return actionOptions;

    }
    //Board newBoard = new Board(this.board.getstring());
    public State result(Action a){
        Board newBoard = new Board(this.board.getstring());
        newBoard = moveTile(this.board,a.tileValue());
        State newState = new State(newBoard);
        return newState;
    }

    public Board moveTile(Board currentboard,int value){
        int [] targetTilelocation = currentboard.getTileLocation(value);
        int [] emptyTilelocation = currentboard.getTileLocation(0);

        if (board.isValidTile(targetTilelocation[0], targetTilelocation[1]) && board.isValidTile(emptyTilelocation[0], emptyTilelocation[1])){
            currentboard.switchTiles(targetTilelocation[0], targetTilelocation[1], emptyTilelocation[0], emptyTilelocation[1]); // switch tile locations
            return  currentboard;
        }
        else {
            return currentboard;
        }
    }


    public int calculateHeuristicValue() {
        int heuristicValue = 0;
        int size = board.getRows() * board.getColumns();

        // Iterate through each tile on the board
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int tile = board.getTileValue(row, col);

                if (tile != 0) { // Skip the empty tile (assuming it's represented by 0)
                    // Calculate the expected row and column for the current tile value
                    int expectedRow = (tile - 1) / size;
                    int expectedCol = (tile - 1) % size;

                    // Add the Manhattan distance between the current position and the expected position
                    heuristicValue += Math.abs(row - expectedRow) + Math.abs(col - expectedCol);
                }
            }
        }

        return heuristicValue;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof State)) {
            return false;
        }
        State otherState = (State) other;
        return board.equals(otherState.board);
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }
}
