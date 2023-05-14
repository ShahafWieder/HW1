public class Action {

private Tile tile;
private Direction direction;
public Action(Tile t,Direction ed){
   this.tile=t;
   this.direction=ed;
}
public Direction getDirection(){
    return this.direction;
}
public int tileValue(){
    return this.tile.getValue();
}
public String toString(){
    String action = "Move" + tile.getValue() + direction;
    return action;
    }
}
