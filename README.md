# as-formula
为Java精确计算提供更好的公式展示

## 直接计算

as-formula 可以对数学表达式直接进行精确计算，内部使用BigDecimal实现。

如下算式，直接使用 0.1 + 0.2 会得到一个不准确的数值。

```
System.out.println(0.1 +0.2); // 0.30000000000000004
System.out.println(new Formula("0.1 + 0.2").calc().doubleValue()); // 0.3
```

通过使用Formula().calc()方法，会返回一个BigDecimal对象，  
通过调用doubleValue()方法即可生成准确的double数值。  

### 目前支持的运算

as-formula 目前支持 加法(+)、减法(-)、乘法(*)、除法(/)、次方(^)。

```
Formula formula = new Formula("1 + 2");
System.out.println(formula.calc()); // 3
formula = new Formula("1 - 2");
System.out.println(formula.calc()); // -1
formula = new Formula("1 * 2");
System.out.println(formula.calc()); // 2
formula = new Formula("1 / 2");
System.out.println(formula.calc()); // 0.5
formula = new Formula("1 ^ 2");
System.out.println(formula.calc()); // 1
```

注: 

1. 次方运算不支持指数为分数(即不支持开方运算)。

2. 除法运算默认四舍五入保留8位，可以通过调用 Formula(formula, scale, roundingMode) 方法进行设置。


## 代数运算

也可以输入代数，在执行时通过传入实际参数进行计算

```
Formula at = new Formula("(1 + i) ^ t");
Map<String, Object> parameters = new HashMap<String,Object>();
parameters.put("i", 0.0485);
parameters.put("t", 360);
System.out.println(at.calc(parameters)); // 1.2671912750046956
```

### 推荐设置为公用静态变量

Formula 在加载后是线程安全的(详见 FormulaTest 的 ThreadTest方法)，可以将常用的公式作为公用变量初始化在公用区域，
Formula 的构造方法执行后将会存储公式的解析结果，提高调用的效率。

```
static Formula formula = new Formula("(1 + i) ^ t");

public void calc1() {
   formula.calc(...);
}

public void calc2() {
  formula.calc(...);
}
```