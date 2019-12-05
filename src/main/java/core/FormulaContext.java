package core;

import operator.Operator;
import operator.impl.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhaoxin
 * @date 2019-12-04
 */
class FormulaContext {

    public static final char SPACE = ' ';

    private static final char LEFT_BRACKET = '(';
    private static final int PRIORITY_OF_LEFT_BRACKET = 10;
    private static final char RIGHT_BRACKET = ')';
    private static final int PRIORITY_OF_RIGHT_BRACKET = -10;

    private static final Map<Character, Operator> OPERATOR_MAP = new ConcurrentHashMap<>(32);

    static {
        initOperatorMap();
    }

    /**
     * 加载操作映射
     * key: 操作符， value: Operator
     * TODO: 后期添加为自动扫描，增加扩展
     */
    private static void initOperatorMap() {
        Operator operator = new PlusOperator();
        OPERATOR_MAP.put(operator.getCode(), operator);
        operator = new SubtractOperator();
        OPERATOR_MAP.put(operator.getCode(), operator);
        operator = new MultiplyOperator();
        OPERATOR_MAP.put(operator.getCode(), operator);
        operator = new DivideOperator();
        OPERATOR_MAP.put(operator.getCode(), operator);
        operator = new PowOperator();
        OPERATOR_MAP.put(operator.getCode(), operator);
    }

    public static boolean isOperator(char signal) {
        return OPERATOR_MAP.containsKey(signal);
    }

    public static int getPriority(char operator) {
        return OPERATOR_MAP.get(operator).getPriority();
    }

    public static Operator getOperator(char operator) {
        return OPERATOR_MAP.get(operator);
    }

    public static boolean isBrackets(char character) {
        return isLeftBracket(character) || character == RIGHT_BRACKET;
    }

    private static boolean isLeftBracket(char character) {
        return character == LEFT_BRACKET;
    }

    public static int getPriorityOfBracket(char bracket) {
        return isLeftBracket(bracket) ? PRIORITY_OF_LEFT_BRACKET : PRIORITY_OF_RIGHT_BRACKET;
    }
}
