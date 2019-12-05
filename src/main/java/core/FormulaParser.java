package core;

import static core.FormulaContext.isBrackets;

/**
 * @author zhaoxin
 * @date 2019-12-04
 */
class FormulaParser {

    private ParseResults parseResults;

    void parse(String formula) {
        formula = removeSpace(formula);
        ParseResults results = new ParseResults(formula.length());
        char[] chars = formula.toCharArray();
        int basePriority = 0;

        for (int i = 0; i < chars.length; i++) {
            if (isBrackets(chars[i])) {
                basePriority += FormulaContext.getPriorityOfBracket(chars[i]);
            } else if (FormulaContext.isOperator(chars[i])) {
                FormulaNode node = FormulaNode.create(results.sizeOfArgument(), chars[i], basePriority);
                results.insert(node);
            } else {
                StringBuilder builder = new StringBuilder().append(chars[i]);
                while (i + 1 < chars.length && !FormulaContext.isOperator(chars[i + 1]) && !isBrackets(chars[i + 1])) {
                    builder.append(chars[i + 1]);
                    i++;
                }
                results.append(builder.toString());
            }
        }
        this.parseResults = results;
    }

    private static String removeSpace(String formula) {
        return formula.replaceAll(FormulaContext.SPACE + "", "");
    }

    ParseResults getResults() {
        return this.parseResults;
    }
}
