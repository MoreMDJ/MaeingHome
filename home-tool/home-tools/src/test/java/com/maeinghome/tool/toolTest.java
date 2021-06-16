package com.maeinghome.tool;

import com.maeinghome.tool.times.Sequence;
import com.maeinghome.tool.times.SystemClockEnum;

import java.util.Arrays;

public class toolTest {
    public static void main(String[] args) {
        Sequence s = new Sequence(2,true,true);
        long l = SystemClockEnum.INSTANCE.initialize().currentTimeMillis();
        for (long i = 0 ; i < Long.MAX_VALUE; i++) {
            SystemClockEnum.INSTANCE.currentTimeMillis();
        }
        System.out.println("高并发：" + (SystemClockEnum.INSTANCE.currentTimeMillis() - l));
        long l1 = System.currentTimeMillis();
        for (int i = 0 ; i < Integer.MAX_VALUE; i++) {
            System.currentTimeMillis();
        }
        System.out.println("系统：" + (System.currentTimeMillis() - l1));
    }
}
