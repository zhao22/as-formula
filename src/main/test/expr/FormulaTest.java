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
        Assert.assertEquals(at.set("i", 0.0485).set("t", 5).calc().doubleValue(), 1.2671912750046956, 0);
        Assert.assertEquals(at.set("t", 3).calc().doubleValue(), 1.152670834125, 0);
    }

    @Test
    public void clearAndSetMapTest() {
        Formula at = new Formula("(1 + i) ^ t");
        Assert.assertEquals(at.set("i", 0.0485).set("t", 3).calc().doubleValue(), 1.152670834125, 0);
        at.clear();
        Map<String, Object> map = new HashMap<>();
        map.put("i", 0.0485);
        map.put("t", 5);
        Assert.assertEquals(at.set(map).calc().doubleValue(), 1.26719127500469553125, 0);
    }

    @Test
    public void threadTest() {
        Random random = new Random();
        Formula formula = new Formula("m * a");
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            Thread t = new Thread(() -> {
                int m = random.nextInt(20);
                int a = random.nextInt(20);
                try {
                    Assert.assertEquals(m * a, formula.set("m", m).set("a", a).calc().intValue());
                } catch (Error e) {
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
