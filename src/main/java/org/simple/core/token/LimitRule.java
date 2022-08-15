package org.simple.core.token;

import org.simple.core.sliding.GradeRule;

public class LimitRule {
    /* 业务标识 */
    private String mark;
    /* 最大令牌数量 */
    private int maxTokenNum;
    /* 单位时间 */
    private int timeMillis;
    /* 规则 */
    private GradeRule gradeRule = GradeRule.discard;

    public LimitRule(int timeMillis, int maxTokenNum) {
        this.timeMillis = timeMillis;
        this.maxTokenNum = maxTokenNum;
    }

    public LimitRule(int timeMillis, int maxTokenNum, GradeRule gradeRule) {
        this(timeMillis, maxTokenNum);
        this.gradeRule = gradeRule;
    }

    public String getMark() {
        return mark;
    }


    public int getTimeMillis() {
        return timeMillis;
    }

    public GradeRule getGradeRule() {
        return gradeRule;
    }

    public int getMaxTokenNum() {
        return maxTokenNum;
    }

}
