package core;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhaoxin
 * @date 2019-12-04
 */
class FormulaCalculator {

    public BigDecimal calculate(List<String> arguments, List<FormulaNode> nodes) {
        BigDecimal result = BigDecimal.ZERO;
        for (FormulaNode node : nodes) {
            String arg1 = arguments.get(node.getFirst());
            String arg2 = arguments.get(node.getFirst() + 1);
            result = FormulaContext.getOperator(node.getSignal()).execute(new BigDecimal(arg1), new BigDecimal(arg2));
            arguments.set(node.getFirst(), result.toString());
            arguments.set(node.getFirst() + 1, result.toString());
        }
        return result;
    }
}
