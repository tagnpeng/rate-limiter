package org.simple.core.sliding;

public class LimitRule {
    /* 业务标识 */
    private String mark;
    /* 时间队列的总长度 */
    private int timeSliceSize;
    /* 每个时间片的时长(单位ms) */
    private int timeMillisPerSlice;
    /* 窗口长度 */
    private int windowSize;
    /* 最大阈值 */
    private int threshold;
    /* 规则 */
    private GradeRule gradeRule = GradeRule.discard;

    public LimitRule(int windowSize, int timeMillisPerSlice, int threshold) {
        this.threshold = threshold;
        this.timeMillisPerSlice = timeMillisPerSlice;
        this.windowSize = windowSize;
        // 保证存储在至少两个window
        this.timeSliceSize = windowSize * 2 + 1;
    }

    public LimitRule(int windowSize, int timeMillisPerSlice, int threshold, GradeRule gradeRule) {
        this(windowSize, timeMillisPerSlice, threshold);
        this.gradeRule = gradeRule;
    }

    public String getMark() {
        return mark;
    }

    public int getTimeSliceSize() {
        return timeSliceSize;
    }

    public int getTimeMillisPerSlice() {
        return timeMillisPerSlice;
    }

    public int getWindowSize() {
        return windowSize;
    }

    public GradeRule getGradeRule() {
        return gradeRule;
    }

    public int getThreshold() {
        return threshold;
    }
}
