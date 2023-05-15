public class Node {
    private State state;
    private Node parent;
    private Action action;
    public Node(State state, Node parent, Action action) {
        this.state = state;
        this.parent = parent;
        this.action = action;
    }

    public State getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public Action getAction() {
        return action;
    }

    public Node[] expand() {
        // Expand the current vertex by applying all possible actions
        Action[] possibleActions = this.state.actions();
        Node[] expandedNodes = new Node[possibleActions.length];

        for (int i = 0; i < possibleActions.length; i++) {
            Action currentAction = possibleActions[i];
            State nextState = state.result(currentAction);
            expandedNodes[i] = new Node(nextState, this, currentAction);
        }

        return expandedNodes;
    }
    public int heuristicValue() {
        // Calculate and return the heuristic value of the state
        return state.calculateHeuristicValue();
    }

}
