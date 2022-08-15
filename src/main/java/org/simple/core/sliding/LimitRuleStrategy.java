package org.simple.core.sliding;

public interface LimitRuleStrategy {
    LimitRule get(int windowSize, int timeMillisPerSlice);
    LimitRule get(int windowSize, int timeMillisPerSlice, GradeRule gradeRule);
}
