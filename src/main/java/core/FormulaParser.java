package core;

import static core.FormulaContext.isBrackets;

/**
 * 公式解析
 * @author zhaoxin
 * @date 2019-12-04
 */
class FormulaParser {

    /**
     * 解析公式
     * @param formula
     * @return
     */
    ParseResults parse(String formula) {
        if (FormulaContext.isEmpty(formula)) {
            throw new FormulaException("unexpected formula expression");
        }
        formula = removeSpace(formula);
        ParseResults results = new ParseResults(formula.length());
        char[] chars = formula.toCharArray();
        int bracketsPriority = 0;

        for (int i = 0; i < chars.length; i++) {
            if (isBrackets(chars[i])) {
                // 如果是左括号，则提高优先级；右括号则降低优先级。后续的操作符的优先级将加入括号优先级
                bracketsPriority += FormulaContext.getPriorityOfBracket(chars[i]);
                if (bracketsPriority < 0) {
                    throw new FormulaException("unexpected right bracket on position:" + i);
                }
            } else if (FormulaContext.isOperator(chars[i])) {
                // 如果是操作符，将创建node节点
                OperatorNode node = OperatorNode.create(results.sizeOfArgument(), chars[i], bracketsPriority);
                // 将node 节点插入results中
                results.insert(node);
            } else {
                // 不是括号和操作符，统一视作代数。
                StringBuilder builder = new StringBuilder().append(chars[i]);
                while (i + 1 < chars.length && !FormulaContext.isOperator(chars[i + 1]) && !isBrackets(chars[i + 1])) {
                    builder.append(chars[i + 1]);
                    i++;
                }
                results.append(builder.toString());
            }
        }
        if (bracketsPriority != 0) {
            throw new FormulaException("number of left brackets doesn't match number of right brackets");
        }
        return results;
    }

    /**
     * 去除掉公式中所有的空格
     * @param formula 公式
     * @return {@link String} 去除掉空格的公式
     */
    private static String removeSpace(String formula) {
        return formula.replaceAll(FormulaContext.SPACE + "", "");
    }

}
