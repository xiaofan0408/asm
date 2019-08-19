package com.xiaofan0408;

/**
 * @author xuzefan  2019/8/12 11:12
 */
public class Test01 {

    public static void main(String[] args) {
        printTwo();
    }

    public static void printOne(){
        System.out.println("print One");
    }

    public static void printTwo(){
       printOne();
       printOne();
    }

}
