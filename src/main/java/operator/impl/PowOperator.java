package operator.impl;

import operator.Operator;

import java.math.BigDecimal;

public class PowOperator implements Operator {
    @Override
    public char getCode() {
        return '^';
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
        return arg1.pow(arg2.intValue());
    }
}
