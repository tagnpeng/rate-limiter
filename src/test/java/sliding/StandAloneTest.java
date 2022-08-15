package sliding;

import org.junit.jupiter.api.Test;
import org.simple.core.sliding.LimitCore;
import org.simple.core.sliding.LimitRule;

public class StandAloneTest {

    @Test
    public void windowTest() {
        //一秒请求5次
        LimitRule limitRule = new LimitRule(10, 100, 5);

        LimitCore limitCore = new LimitCore(limitRule);

        while (true){
            if (limitCore.get()){
                System.out.println("ok");
            }
        }
    }

    @Test
    public void tokenTest() {
        org.simple.core.token.LimitRule limitRule = new org.simple.core.token.LimitRule(1000, 5);
        org.simple.core.token.LimitCore limitCore = new org.simple.core.token.LimitCore(limitRule);

        while (true){
            if (limitCore.get()){
                System.out.println("ok");
            }
        }
    }

}
