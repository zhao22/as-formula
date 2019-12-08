# as-formula
为Java精确计算提供更好的公式展示

## 直接计算

as-formula 可以对数学表达式直接进行精确计算，内部使用BigDecimal实现。

如下算式，直接使用 0.1 + 0.2 会得到一个不准确的数值。

```
System.out.println(0.1 +0.2); // 0.30000000000000004
Formula f = new Formula("0.1 + 0.2");
System.out.println(f.calc().doubleValue()); // 0.3
```

通过使用Formula().calc()方法，会返回一个BigDecimal对象，  
通过调用doubleValue()方法即可生成准确的double数值。  

Formula对象会缓存上次的解析结果，多次调用可以提高计算效率。
```
Formula f = new Formula("0.1 + 0.2");
System.out.println(f.calc().doubleValue()); // 0.3
System.out.println(f.calc().doubleValue()); // 0.3
```

## 代数运算

也可以输入代数，在执行时通过传入实际参数进行计算

```
Formula at = new Formula("(1 + i) ^ t");
Map<String, Object> parameters = new HashMap<String,Object>();
parameters.put("i", 0.0485);
parameters.put("t", 360);
System.out.println(at.calc(parameters)); // 1.2671912750046956
```

## 推荐设置为公用静态变量

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