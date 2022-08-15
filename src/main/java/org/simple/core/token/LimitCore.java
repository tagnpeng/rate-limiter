package org.simple.core.token;


import org.simple.core.sliding.GradeRule;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class LimitCore {
    private AtomicInteger token = new AtomicInteger(0);

    private LimitRule limitRule;

    public LimitCore(LimitRule limitRule){
        this.limitRule = limitRule;
        token.updateAndGet((o) -> limitRule.getMaxTokenNum());
        //添加令牌
        new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2, new ThreadPoolExecutor.DiscardOldestPolicy())
                .scheduleAtFixedRate(() -> {
                    int var = token.get();
                    int tokensAddedEachTime = limitRule.getMaxTokenNum() >> 1;
                    if (var + tokensAddedEachTime <= limitRule.getMaxTokenNum()) {
                        token.addAndGet(tokensAddedEachTime);
                    }
                }, 0, limitRule.getTimeMillis(), TimeUnit.MILLISECONDS);
    }

    public boolean get() {
        int var= token.get();
        if (limitRule.getGradeRule().equals(GradeRule.discard)){
            if(var > 0 && token.compareAndSet(var, var-1)){
                return true;
            }
            return false;
        }else {
            while (var > 0 && !token.compareAndSet(var, var-1)){
                var = token.get();
            }
            return true;
        }
    }


}
