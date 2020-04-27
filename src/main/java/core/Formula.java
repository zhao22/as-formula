package core;

import operator.Operator;
import operator.impl.DivideOperator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 计算对象
 * @author: zhaoxin
 * @date: 20191204
 */
public class Formula {

    private ParseResults results;
    private int scale = 8;
    private RoundingMode mode = RoundingMode.HALF_UP;

    /**
     * 方法将会对传入公式进行解析，生成 {@link ParseResults}，之后可以根据这个模板重复运算
     * @param formula {@link String} 字符串公式 可以是直接运算的， 也可以是包含代数的
     */
    public Formula(String formula) {
        results = new FormulaParser().parse(formula);
    }

    public Formula(String formula, int scale, RoundingMode mode) {
        this(formula);
        this.scale = scale;
        this.mode = mode;
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
    private BigDecimal calculate(List<String> arguments, List<OperatorNode> nodes) {
        BigDecimal result = BigDecimal.ZERO;
        Set<Integer> calculatedIndexes = new HashSet<>(arguments.size() - 1);
        for (OperatorNode node : nodes) {
            BigDecimal arg1 = getAndPutArguments(node.getFirst(),calculatedIndexes, arguments, result);
            BigDecimal arg2 = getAndPutArguments(node.getFirst() + 1,calculatedIndexes, arguments, result);
            Operator operator = FormulaContext.getOperator(node.getSignal());
            if (operator instanceof DivideOperator) {
                result = ((DivideOperator)operator).execute(arg1, arg2, scale, mode);
            } else {
                result = operator.execute(arg1, arg2);
            }
        }
        return result;
    }

    /**
     * 如果对应位置已经计算过了，将之前的计算结果返回。
     * 如果对应位置没有计算过，将实际值返回，并将坐标添加过计算过节点中。
     * @param index  对应坐标
     * @param calculatedIndexes 已计算过坐标集合
     * @param arguments  实际值
     * @param result  之前的计算结果
     * @return  对应位置应该参与当前计算的值
     */
    private BigDecimal getAndPutArguments(int index, Set<Integer> calculatedIndexes, List<String> arguments, BigDecimal result) {
        if (calculatedIndexes.contains(index)) {
            return result;
        }
        calculatedIndexes.add(index);
        return new BigDecimal(arguments.get(index));
    }

}
