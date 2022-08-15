package sliding;

import org.junit.jupiter.api.Test;
import org.simple.core.sliding.LimitCore;
import org.simple.core.sliding.LimitRule;

public class StandAloneTest {

    @Test
    public void test() {
        //一秒请求10次
        LimitRule limitRule = new LimitRule(1000, 1000, 5);

        LimitCore limitCore = new LimitCore(limitRule);

        for (int i = 0; i < 100; i++) {
            if (limitCore.get()) {
                //模拟业务
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("请求成功了");
            } else {
                System.out.println("请求被拒绝了");
            }
        }
    }

}
