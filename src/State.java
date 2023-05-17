public class State {
    private Board board;
    public State(Board b) {
        this.board = b;
    }

    /**
     * Checks if the current state is a goal state.
     *
     * @Return `true` if the current state is a goal state, `false` otherwise
     */
    public boolean isGoal() {
        int counter = 1;
        int rows = this.board.getRows();
        int columns = this.board.getColumns();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (this.board.getTileValue(i, j) != counter) {
                    // We're at the last tile, and it's empty, so it's still a valid goal state
                    // There's a tile out of place, so it's not a goal state
                    return i == rows - 1 && j == columns - 1 && board.getTileValue(i, j) == 0;
                }
                counter++;
            }
        }
        return true;
    }

    /**
     * Generates an array of available actions in the current state.
     *
     * @Return An array of available actions
     */
    public Action[] actions() {
        int size = 0;
        int [] defaultDirection = {0,0,0,0};
        int counter = 0;
        int [] zeroLocation = this.board.getTileLocation(0);

        if(zeroLocation[0]+1 < this.board.getRows()){ // UP
            defaultDirection[0] = 1;
            counter++;
        }
        if(zeroLocation[0] -1 >= 0){ // DOWN
            defaultDirection[1] = 1;
            counter ++;
        }
        if(zeroLocation[1]-1 >= 0) { // RIGHT
            defaultDirection[2] = 1;
            counter++;
        }
        if(zeroLocation[1]+1 < this.board.getColumns()){ // LEFT
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

    /**
     * Generates the next state by applying the specified action.
     *
     * @param a The action to apply
     * @Return The resulting state after applying the action
     */
    public State result(Action a){
        Board newBoard = new Board(this.board.copyBoard());
        newBoard = moveTile(newBoard,a.tileValue());
        return new State(newBoard);
    }

    /**
     * Moves the specified tile to the empty tile's location on the board.
     *
     * @param currentboard The current board state
     * @param value        The value of the tile to be moved
     * @Return The updated board state after moving the tile
     */
    public Board moveTile(Board currentboard,int value){
        int [] targetTilelocation = currentboard.getTileLocation(value);
        int [] emptyTilelocation = currentboard.getTileLocation(0);
        currentboard.switchTiles(targetTilelocation[0], targetTilelocation[1], emptyTilelocation[0], emptyTilelocation[1]);
        return  currentboard;
    }

    /**
     * Calculates the absolute difference between two integers.
     *
     * @param a The first integer
     * @param b The second integer
     * @Return The absolute difference between the two integers
     */
    public int calculateAbsoluteDifference(int a, int b) {
        int diff = a - b;
        return diff >= 0 ? diff : -diff;
    }


    /**
     * Calculates the heuristic value for the current state.
     * The heuristic value represents the estimated cost from the current state to the goal state.
     * It is based on the sum of the Manhattan distances between each tile's current position and its expected position in the goal state.
     *
     * @Return The heuristic value for the current state
     */
    public int calculateHeuristicValue() {
        int heuristicValue = 0;

        // Iterate through each tile on the board
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                int tile = this.board.getTileValue(row, col);

                if (tile != 0) { // Skip the empty tile (assuming it's represented by 0)
                    // Calculate the expected row and column for the current tile value
                    int expectedRow = (tile - 1) / this.board.getColumns();
                    int expectedCol = (tile - 1) % this.board.getColumns();

                    // Add the absolute difference between the current position and the expected position
                    heuristicValue += calculateAbsoluteDifference(row, expectedRow)
                            + calculateAbsoluteDifference(col, expectedCol);
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
