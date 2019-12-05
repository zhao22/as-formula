package expr;

import core.Formula;
import org.junit.Test;

public class FormulaTest {

    @Test
    public void test() {
        Formula formula = new Formula("3 + 2 - 5 * 2");
        System.out.println(formula.calc());
        formula = new Formula("(3 + 2 - 5) * 2");
        System.out.println(formula.calc());
        Formula f = new Formula("a ^ 2 - b ^ 2").set("a", 5);
        System.out.println(f.set("b", 4).calc());
        System.out.println(f.set("b", 3).calc());
    }
}
