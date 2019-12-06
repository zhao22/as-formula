package core;

/**
 * 操作符节点
 * @author zhaoxin
 * @date 2019-12-04
 */
class OperatorNode {

    /**
     * 操作符计算的第一个参数的下标。
     * 如 3 + 5 , 则记录 3 对应的下标。
     * 如果操作符需要两个参数，则第二个参数必须为 index + 1
     */
    private int index;

    /**
     * 对应操作符
     */
    private char signal;

    /**
     * 该操作符节点的优先级，为括号优先级 + 操作符优先级
     */
    private int priority;

    private OperatorNode() {

    }

    /**
     * 操作符节点
     * @param sizeOfArguments arguments的长度
     * @param operator 操作符
     * @param priority 括号优先级
     * @return {@link OperatorNode}
     */
    static OperatorNode create(int sizeOfArguments, char operator, int priority) {
        OperatorNode node = new OperatorNode();
        node.setFirst(sizeOfArguments - 1);
        node.setSignal(operator);
        node.setPriority(priority + FormulaContext.getPriority(operator));
        return node;
    }

    int getFirst() {
        return index;
    }

    private void setFirst(int first) {
        this.index = first;
    }

    char getSignal() {
        return signal;
    }

    private void setSignal(char signal) {
        this.signal = signal;
    }

    int getPriority() {
        return priority;
    }

    private void setPriority(int priority) {
        this.priority = priority;
    }
}
