package expr;

import core.Formula;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class FormulaTest {

    @Test
    public void directTest() {
        Assert.assertEquals(0.1 + 0.2, 0.30000000000000004, 0);
        Formula f = new Formula("0.1 + 0.2");
        Assert.assertEquals(f.calc().doubleValue(), 0.3, 0);
        Assert.assertEquals(f.calc().doubleValue(), 0.3, 0);
    }

    @Test
    public void algebraTest() {
        Formula at = new Formula("(1 + i) ^ t");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("i", 0.0485);
        parameters.put("t", 5);
        Assert.assertEquals(at.calc(parameters).doubleValue(), 1.2671912750046956, 0);
        parameters.put("t", 3);
        Assert.assertEquals(at.calc(parameters).doubleValue(), 1.152670834125, 0);
    }

    @Test
    public void threadTest() {
        Random random = new Random();
        Formula formula = new Formula("m * a");
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            Thread t = new Thread(() -> {
                Map<String, Object> parameters = new HashMap<>();
                int m = random.nextInt(20);
                parameters.put("m", m);
                int a = random.nextInt(20);
                parameters.put("a", a);
                try {
                    Assert.assertEquals(m * a, formula.calc(parameters).intValue());
                } catch (Error e)

                {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
            t.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
