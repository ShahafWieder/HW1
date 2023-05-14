public class State {
    private Board board;
    private Action action;
    private EnumDirection enumDirection;

    public State(Board b, Action a, EnumDirection ed) {
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
                        // We're at the last tile and it's empty, so it's still a valid goal state
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
    public EnumDirection.Direction[] actions(){
        int i_empty = -1;
        int j_empty = -1;

        for(int i=0;i<board.getRows();i++){
            for (int j=0;j<board.getColumns();j++){
                if (board.getTileValue(i,j)==0){
                    i_empty = i;
                    j_empty = j;
                }
            }
        }

        EnumDirection.Direction[] actions = { EnumDirection.Direction.UP, EnumDirection.Direction.DOWN, EnumDirection.Direction.RIGHT, EnumDirection.Direction.LEFT };
        if (j_empty < 0) {
            actions[3] = null;
        }
        if (j_empty > board.getColumns()-1) {
            actions[2] = null;
        }
        if (i_empty < 0) {
            actions[0] = null;
        }
        if(i_empty > board.getRows()-1) {
            actions[1] = null;
        }
        return actions;
    }
    Board newBoard = new Board(this.board.getstring());
    public State result(Action a){
       // Board newBoard = new Board(this.board.getstring());
        newBoard = moveTile(this.board,a.tileValue());
        State newState = new State(newBoard);
        return newState;
    }

    public Board moveTile(Board currentboard,int value){
        int [] emptyTilelocation= new int [2]; // location coordinates for empty tile
        int [] targetTilelocation = new int [2]; // location coordinate target tile
        for (int i=0;i<currentboard.getRows();i++){
            for (int j=0;j<currentboard.getColumns();i++){
                if(currentboard.getTileValue(i,j)==value) {
                    targetTilelocation[0] = i;
                    targetTilelocation[1] = j; //saving the coordinates of the target tile
                }
                if(currentboard.getTileValue(i,j)==0){
                    emptyTilelocation [0] = i;
                    emptyTilelocation [1] = j;//saving the coordinates of the missing tile
                }
            }
        }
        currentboard.switchTiles(targetTilelocation[0], targetTilelocation[1], targetTilelocation[0], targetTilelocation[1]); // switch tile locations
        return  currentboard;
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
