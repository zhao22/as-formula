package operator;

import java.math.BigDecimal;

/**
 * 操作符
 * @author zhaoxin
 * @date 2019-12-04
 */
public interface Operator {

    /**
     * @return {@link Character} 操作符的符号
     */
    char getCode();

    /**
     * @return {@link Integer} 操作符的优先级
     */
    int getPriority();

    /**
     * 操作符的执行逻辑
     * @param arg1 参数1
     * @param arg2 参数2
     * @return {@link BigDecimal}操作符的执行结果
     */
    BigDecimal execute(BigDecimal arg1, BigDecimal arg2);
}
