package operator;

import java.math.BigDecimal;

public interface Operator {

    char getCode();

    int getPriority();

    BigDecimal execute(BigDecimal arg1, BigDecimal arg2);
}
