package core;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析结果
 * @author zhaoxin
 * @date 2019-12-04
 */
class ParseResults {

    /**
     * 按顺序排列的运算参数。
     * 可以是直接运算的数字，也可以是代数
     */
    private List<String> arguments;
    /**
     * 操作节点，优先级越高的节点越靠前，如果相同优先级的节点，先添加的在较前面。
     */
    private List<OperatorNode> nodes;

    ParseResults() {
        arguments = new ArrayList<>();
        nodes = new ArrayList<>();
    }

    /**
     * 根据长度初始化元素和节点列表。
     * @param length 公式长度
     */
     ParseResults(int length) {
        arguments = new ArrayList<>(length);
        nodes = new ArrayList<>(length / 2);
    }

    /**
     * 采用插入排序的方式，将优先级较高的节点插入在前面
     * @param node
     */
    void insert(OperatorNode node) {
        int j = nodes.size();
        if (nodes.size() == 0) {
            nodes.add(node);
            return;
        }
        // 从尾节点向前遍历
        for (; ; j--) {
            // 如果遍历到第一个节点或遇到不小于当前节点优先级的节点，则将节点添加到对应位置
            if (j == 0 || nodes.get(j - 1).getPriority() >= node.getPriority()) {
                nodes.add(j, node);
                break;
            }
        }
    }

    void append(String argument) {
        arguments.add(argument);
    }

    int sizeOfArgument() {
        return arguments.size();
    }

    List<OperatorNode> getNodes() {
        return nodes;
    }

    List<String> getArguments() {
        return new ArrayList<>(arguments);
    }
}
