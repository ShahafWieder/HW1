public class Action {
private  Board board;
private Tile tile;
private EnumDirection direction;
public Action(Tile t,Board b,EnumDirection ed){
   this.tile=t;
   this.board=b;
   this.direction=ed;
}
public String toString(){
    String action = "Move" + tile.getValue() + direction;
    return action;
    }
}
