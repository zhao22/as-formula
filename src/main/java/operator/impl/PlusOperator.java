package operator.impl;

import operator.Operator;

import java.math.BigDecimal;

public class PlusOperator implements Operator {
    @Override
    public char getCode() {
        return '+';
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
        return arg1.add(arg2);
    }
}
