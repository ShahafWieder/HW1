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
        // Board target=new Board()
        int counter = 1;
        int rows= board.getRows();
        int columns=board.getColumns();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(i!=rows & j!=columns) {//כשלא באחרון
                    if (board.getTileValue(i, j) == counter) {
                        counter++;
                    }
                    else{return false;}
                }
                if(board.getTileValue(rows,columns)==0){return  true;}
                else {
                    return false;
                }
            }
        }
        return true;
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
