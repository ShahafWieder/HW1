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
    public Action[] actions() {
        int [] defaultDirection = {0,0,0,0};
        int counter = 0;
        int [] zeroLocation = this.board.getTileLocation(0);
        if(zeroLocation[1] +1 <= 0){ // up
            defaultDirection[0] = 1;
            counter ++;
        }
        if(zeroLocation[1]-1 >= this.board.getColumns()){ // down
            defaultDirection[1] = 1;
            counter++;
        }
        if(zeroLocation[0]+1 <= this.board.getRows()){ // right
            defaultDirection[2] = 1;
            counter++;
        }
        if(zeroLocation[0]-1 >= 0) { // left
            defaultDirection[3] = 1;
            counter++;
        }
        Action [] actionOptions = new Action[counter];
        Direction [] temp = {Direction.UP, Direction.DOWN, Direction.RIGHT, Direction.LEFT};
        int[]dRow={1,-1,0,0};
        int[]dCol={0,0,-1,1};
        for(int i = 0; i < 4; i++){
            if(defaultDirection[i] == 0) continue;
            actionOptions[i] = new Action(this.board.getTile(zeroLocation[0]+dRow[i],zeroLocation[1]+dCol[i]), temp[i]);
        }
        return actionOptions;

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
