package core;

/**
 * @author zhaoxin
 * @date 2019-12-04
 */
class FormulaNode {


    private int first;

    private char signal;

    private int priority;


    public static FormulaNode create(int sizeOfArguments, char operator, int priority) {
        FormulaNode node = new FormulaNode();
        node.setFirst(sizeOfArguments - 1);
        node.setSignal(operator);
        node.setPriority(priority + FormulaContext.getPriority(operator));
        return node;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public char getSignal() {
        return signal;
    }

    public void setSignal(char signal) {
        this.signal = signal;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
