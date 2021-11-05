package com.maeinghome.tool;

import com.maeinghome.util.times.Sequence;
import com.maeinghome.util.times.SystemClockEnum;

public class toolTest {
    public static void main1(String[] args) {
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

    public static void main(String[] args) {
        String a = "1__2_3";
        String[] split = a.split("_", 2);
        System.out.println(split);
    }
}
