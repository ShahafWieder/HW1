/**
 * Represents an action in a game, involving a tile and direction.
 */
public class Action {

private Tile tile;
private Direction direction;

    /**
     * Constructs an action with the specified tile and direction.
     *
     * @param t The tile associated with the action
     * @param ed The direction of the action
     */

    public Action(Tile t,Direction ed){
    this.tile=t;
    this.direction=ed;
    }

    /**
     * Returns the value of the tile associated with this action.
     *
     * @Return The value of the tile
     */
    public int tileValue(){
        return this.tile.getValue();
    }

    /**
     * Returns a string representation of the action.
     *
     * @Return A string representing the action
     */
    public String toString(){
        return "Move " + (tile.getValue() +" "+ direction).toLowerCase();
        }
    }
