package core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 计算对象
 * @author: zhaoxin
 * @date: 20191204
 */
public class Formula {

    private ParseResults results;

    /**
     * 方法将会对传入公式进行解析，生成 {@link ParseResults}，之后可以根据这个模板重复运算
     * @param formula {@link String} 字符串公式 可以是直接运算的， 也可以是包含代数的
     */
    public Formula(String formula) {
        results = new FormulaParser().parse(formula);
    }

    /**
     * 对之前的加载信息进行计算
     * @return {@link BigDecimal} 运算结果
     */
    public BigDecimal calc() {
        return calculate(results.getArguments(), results.getNodes());
    }

    /**
     *
     */
    public BigDecimal calc(Map<String, Object> parameters) {
        List<String> arguments = new ArrayList<>(results.getArguments());
        for (int i = 0; i < arguments.size(); i++) {
            Object val = parameters.get(arguments.get(i));
            if (val != null) {
                arguments.set(i, String.valueOf(val));
            }
        }
        return calculate(arguments, results.getNodes());
    }

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
