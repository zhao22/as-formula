package core;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 计算对象
 * @author: zhaoxin
 * @date: 20191204
 */
public class Formula {

    private ParseResults results;

    private FormulaCalculator calculator = new FormulaCalculator();

    private List<String> arguments;

    /**
     * 方法将会对传入公式进行解析，生成 {@link ParseResults}，之后可以根据这个模板重复运算
     * @param formula {@link String} 字符串公式 可以是直接运算的， 也可以是包含代数的
     */
    public Formula(String formula) {
        results = new FormulaParser().parse(formula);
    }

    /**
     * 如果初始化时使用代数，则可以通过使用set对代数进行赋值
     * @param key 代数名称
     * @param value 代数对应值
     * @return {@link Formula} 可链式调用
     */
    public Formula set(String key, Object value) {
        if (key == null || "".equals(key)) {
            throw new FormulaException("unexpected key");
        }
        List<String> template = results.getArguments();
        if (arguments == null || arguments.size() == 0) {
            arguments = new ArrayList<>(template);
        }
        arguments.set(template.indexOf(key), String.valueOf(value));
        return this;
    }

    /**
     * 可以传入{@link Map} 作为映射对象，简化set操作
     * @param arguments {@link Map} 映射对象， key: 代数名称, value: 代数对应值
     * @return {@link Formula} 可链式调用
     */
    public Formula set(Map<String, Object> arguments) {
        Set<Map.Entry<String, Object>> entrySet = arguments.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            set(entry.getKey(), entry.getValue());
        }
        return this;
    }

    /**
     * 对之前的加载信息进行计算
     * @return {@link BigDecimal} 运算结果
     */
    public BigDecimal calc() {
        List<String> arguments = FormulaContext.isEmpty(this.arguments) ?
                new ArrayList<>(results.getArguments()) : new ArrayList<>(this.arguments);
        return calculator.calculate(arguments, results.getNodes());
    }

    /**
     * 清空之前加载的参数(仍会保留公式信息)
     * @return {@link Formula 链式调用}
     */
    public Formula clear() {
        if (this.arguments != null) {
            this.arguments = null;
        }
        return this;
    }
}
