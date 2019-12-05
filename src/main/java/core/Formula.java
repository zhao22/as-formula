package core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Formula {

    private FormulaParser parser = new FormulaParser();

    private FormulaCalculator calculator = new FormulaCalculator();

    private List<String> arguments;

    public Formula(String formula) {
        parser.parse(formula);
    }

    public Formula set(String key, Object value) {
        if (key == null || "".equals(key)) {
            return this;
        }
        List<String> template = parser.getResults().getArguments();
        if (arguments == null || arguments.size() == 0) {
            arguments = new ArrayList<>(template);
        }
        arguments.set(template.indexOf(key), String.valueOf(value));
        return this;
    }

    public Formula set(Map<String, Object> arguments) {
        Set<Map.Entry<String, Object>> entrySet = arguments.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            set(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public BigDecimal calc() {
        boolean isEmpty = this.arguments == null || this.arguments.size() == 0;
        List<String> arguments = isEmpty ? new ArrayList<>(parser.getResults().getArguments()) : new ArrayList<>(this.arguments);
        BigDecimal results = calculator.calculate(arguments, parser.getResults().getNodes());

        return results;
    }

    public Formula clear() {
        if (this.arguments != null) {
            this.arguments = null;
        }
        return this;
    }
}
