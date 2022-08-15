package org.simple.core.sliding;

import java.util.concurrent.atomic.AtomicInteger;

public class LimitCore {
    /* 滑动窗口 */
    private volatile AtomicInteger[] timeSlices;
    /* 限流规则 */
    private LimitRule limitRule;

    public LimitCore(LimitRule limitRule) {
        this.limitRule = limitRule;
        //初始化
        timeSlices = new AtomicInteger[limitRule.getTimeSliceSize()];
        for (int i = 0; i < limitRule.getTimeSliceSize(); i++) {
            timeSlices[i] = new AtomicInteger(0);
        }
    }

    /***
     * 获取当前窗口
     * @author simple
     * @date 2022/8/15 09:18
     * @return Long
     */
    private Long getWindowIndex() {
        long timeMillis = System.currentTimeMillis();
        return ((timeMillis / limitRule.getTimeMillisPerSlice()) + limitRule.getTimeSliceSize()) % limitRule.getTimeSliceSize();
    }


    public boolean get() {
        int windowIndex = getWindowIndex().intValue();

        //计算窗口请求数量
        int num = 0;
        for (int i = 0; i < limitRule.getWindowSize(); i++) {
            num += timeSlices[(windowIndex + limitRule.getTimeSliceSize() - i) % limitRule.getTimeSliceSize()].get();
        }

        //清空窗口值
        this.clearTimeSlices(windowIndex, (windowIndex + limitRule.getTimeSliceSize() - limitRule.getWindowSize()) % limitRule.getTimeSliceSize());
        if (num > limitRule.getThreshold()) {
            System.out.println("false index:" + windowIndex + " num:" + num);
            return false;
        }

        System.out.println("true index:" + windowIndex + " num:" + num);

        //当前窗口数量+1
        timeSlices[windowIndex].incrementAndGet();

        return true;
    }


    /***
     * 对旧的时间窗口进行清除，
     * 在并发低会出现时间片清除不及时的情况
     * 高并发时会出现提前清除
     * 误差不大
     * @author simple
     * @date 2022/8/15 09:54
     * @param currentIndex 当前窗口index
     * @param endValue 结束值
     */
    private void clearTimeSlices(int currentIndex, int endValue) {
        for (int i = (currentIndex + 1) % limitRule.getTimeSliceSize();
             i != endValue;
             i = (i + 1) % limitRule.getTimeSliceSize()) {
            timeSlices[i].addAndGet(0);
        }
    }

}
