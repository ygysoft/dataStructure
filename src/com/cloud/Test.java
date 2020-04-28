package com.cloud;

/**
 * Test
 *
 * @Descript:
 * @Author: yanggy
 * @Date: 2020/4/28 16:10
 */
public class Test {
    public static void main(String[] args) {
        Array arr = new Array();
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);

        System.out.println(arr.get(5));

        arr.set(5, 3);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);

        arr.removeLast();
        System.out.println(arr);

        arr.removeAllElement(3);
        System.out.println(arr);
    }
}
