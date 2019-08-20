package com.xiaofan0408.test;

import com.xiaofan0408.anno.AsmLog;

import java.util.concurrent.TimeUnit;

/**
 * @author xuzefan  2019/8/12 14:40
 */
public class Test3 {

    @AsmLog
    public void test()  {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test asm log");
    }

    @AsmLog
    public int add(int a,int b)  {

        for (int i = 0;i<10000000;i++) {

        }
        return a + b;
    }
}
