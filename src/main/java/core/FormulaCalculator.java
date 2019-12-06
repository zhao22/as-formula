package core;

import java.math.BigDecimal;
import java.util.List;

/**
 * 对公式解析结果进行计算
 * @author zhaoxin
 * @date 2019-12-04
 */
class FormulaCalculator {

    /**
     * 计算公式对应结果
     * @param arguments 参数列表，此时只能是数字
     * @param nodes 操作符节点列表
     * @return 公式计算结果
     */
    BigDecimal calculate(List<String> arguments, List<OperatorNode> nodes) {
        BigDecimal result = BigDecimal.ZERO;
        for (OperatorNode node : nodes) {
            String arg1 = arguments.get(node.getFirst());
            String arg2 = arguments.get(node.getFirst() + 1);
            result = FormulaContext.getOperator(node.getSignal()).execute(new BigDecimal(arg1), new BigDecimal(arg2));
            arguments.set(node.getFirst(), result.toString());
            arguments.set(node.getFirst() + 1, result.toString());
        }
        return result;
    }
}
