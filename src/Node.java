/**
 * Represents a node in a search algorithm, containing a state, parent node, and action.
 */
public class Node {
    private State state;
    private Node parent;
    private Action action;


    /**
     * Constructs a new Node object with the given state, parent node, and action.
     *
     * @param state the state associated with the node.
     * @param parent the parent node of the current node.
     * @param action the action that was taken to reach this node from the parent node.
     */
    public Node(State state, Node parent, Action action) {
        this.state = state;
        this.parent = parent;
        this.action = action;
    }

    /**
     * Returns the state associated with this node.
     *
     * @Return the state object.
     */
    public State getState() {
        return state;
    }

    /**
     * Returns the parent node of this node.
     *
     * @Return the parent node.
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Returns the action associated with this node.
     *
     * @Return the action object.
     */
    public Action getAction() {
        return action;
    }

    /**
     * Expands the current node by generating all possible child nodes.
     *
     * @Return an array of expanded nodes.
     */
    public Node[] expand() {
        // Expand the current vertex by applying all possible actions
        Action[] possibleActions = this.state.actions();
        Node[] expandedNodes = new Node[possibleActions.length];

        for (int i = 0; i < possibleActions.length; i++) {
            Action currentAction = possibleActions[i];
            State nextState = this.state.result(currentAction);
            expandedNodes[i] = new Node(nextState, this, currentAction);
        }
        return expandedNodes;
    }

    /**
     * Returns the heuristic value associated with this node.
     *
     * @Return the heuristic value.
     */
    public int heuristicValue() {
        return state.calculateHeuristicValue();
    }

}
