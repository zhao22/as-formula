package core;

/**
 * 公式异常问题修复
 * @author zhaoxin
 * @date 2019-12-06
 */
class FormulaException extends RuntimeException{

    FormulaException(){}

    FormulaException(String errorMessage) {
        super(errorMessage);
    }
}
