public class Action {
private  Board board;
private Tile tile;
private Direction direction;
public Action(Tile t,Direction ed){
   this.tile=t;
   this.direction=ed;
}
public String toString(){
    String action = "Move" + tile.getValue() + direction;
    return action;
    }
}
