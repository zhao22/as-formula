package core;

import operator.Operator;
import operator.impl.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 公式计算上下文信息
 * @author zhaoxin
 * @date 2019-12-04
 */
class FormulaContext {

    static final char SPACE = ' ';

    static final String EMPTY = "";

    private static final char LEFT_BRACKET = '(';
    /**
     * 左括号的优先级
     */
    private static final int PRIORITY_OF_LEFT_BRACKET = 10;
    private static final char RIGHT_BRACKET = ')';
    /**
     * 右括号的优先级
     */
    private static final int PRIORITY_OF_RIGHT_BRACKET = -10;

    /**
     * 操作符列表
     */
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

    /**
     * 符号是否为操作符
     * @param character 元素
     * @return {@link Boolean} true: 元素是操作符 false: 元素是其他信息
     */
    static boolean isOperator(char character) {
        return OPERATOR_MAP.containsKey(character);
    }

    static int getPriority(char operator) {
        return OPERATOR_MAP.get(operator).getPriority();
    }

    static Operator getOperator(char operator) {
        return OPERATOR_MAP.get(operator);
    }

    /**
     * 元素是否为括号
     * @param character {@link Character} 元素
     * @return {@link Boolean} true: 元素是括号， false: 元素不是括号
     */
    static boolean isBrackets(char character) {
        return isLeftBracket(character) || character == RIGHT_BRACKET;
    }

    private static boolean isLeftBracket(char character) {
        return character == LEFT_BRACKET;
    }

    static int getPriorityOfBracket(char bracket) {
        return isLeftBracket(bracket) ? PRIORITY_OF_LEFT_BRACKET : PRIORITY_OF_RIGHT_BRACKET;
    }

    static boolean isEmpty(String string) {
        return string == null || "".equals(string);
    }

    static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }
}
