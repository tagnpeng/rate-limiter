package org.simple.core;

import java.util.concurrent.atomic.AtomicLong;

public class TokenLimit {
    //存放令牌的桶
    private volatile AtomicLong bucket = new AtomicLong(0);
}
