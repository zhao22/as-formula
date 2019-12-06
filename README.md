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

Formula对象会缓存上次的计算结果，多次调用可以提高计算效率。
```
Formula f = new Formula("0.1 + 0.2");
System.out.println(f.calc().doubleValue()); // 0.3
System.out.println(f.calc().doubleValue()); // 0.3
```

## 代数运算

也可以输入代数，在执行时通过传入实际参数进行计算

```
Formula at = new Formula("(1 + i) ^ t");
System.out.println(at.set(i, 0.0485).set(t, 360).calc()); // 1.2671912750046956
```

在第一次计算结束之后，可以只替换其中的部分参数进行计算

```
Formula at = new Formula("(1 + i) ^ t");
System.out.println(at.set(i, 0.0485).set(t, 360).calc()); // 1.2671912750046956
System.out.println(at.set("t", 3).calc()) // 1.152670834125
```

as-formula在多线程环境下会有线程不安全的问题(详见FormulaTest.threadTest方法)。所以暂时不建议将其设为公用静态变量。