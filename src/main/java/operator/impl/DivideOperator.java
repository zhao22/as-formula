package operator.impl;

import operator.Operator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DivideOperator implements Operator {
    @Override
    public char getCode() {
        return '/';
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    @Deprecated
    public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
        return execute(arg1, arg2);
    }

    public BigDecimal execute(BigDecimal arg1, BigDecimal arg2, int scale, RoundingMode mode) {
        return arg1.divide(arg2, scale, mode);
    }
}
