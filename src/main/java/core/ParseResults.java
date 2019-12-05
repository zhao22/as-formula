package core;

import java.util.ArrayList;
import java.util.List;

public class ParseResults {

    private List<String> arguments;

    private List<FormulaNode> nodes;

    public ParseResults() {
        arguments = new ArrayList<>();
        nodes = new ArrayList<>();
    }

    public ParseResults(int length) {
        arguments = new ArrayList<>(length);
        nodes = new ArrayList<>(length / 2);
    }

    public void insert(FormulaNode node) {
        int j = nodes.size();
        if (nodes.size() == 0) {
            nodes.add(node);
            return;
        }
        for (; ; j--) {
            if (j == 0 || nodes.get(j - 1).getPriority() >= node.getPriority()) {
                nodes.add(j, node);
                break;
            }
        }
    }

    public void append(String argument) {
        arguments.add(argument);
    }

    public int sizeOfArgument() {
        return arguments.size();
    }

    public List<FormulaNode> getNodes() {
        return nodes;
    }

    public List<String> getArguments() {
        return new ArrayList<>(arguments);
    }
}
