public class State {
    private Board board;
    private Action action;
    private EnumDirection enumDirection;

    public State(Board b, Action a, EnumDirection ed) {
        this.board = b;
        this.action = a;
        this.enumDirection = ed;

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
                    } else {ז
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
