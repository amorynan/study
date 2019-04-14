package com.company.saimawangoj;

public class Test {
    public static void main(String args[]) {
        byte a = 127;
        byte b = 127;
        int s = a + b;
        System.out.println(a);
    }

    private static int test() {

        int temp = 1;

        try {

            System.out.println(temp);

            int res = ++temp;
            return res;

        } catch (Exception e) {

            System.out.println(temp);

            return ++temp;

        } finally {

            ++temp;

            System.out.println(temp);

        }





    }
}
