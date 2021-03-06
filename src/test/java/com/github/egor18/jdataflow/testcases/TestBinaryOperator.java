package com.github.egor18.jdataflow.testcases;

public class TestBinaryOperator
{
    void testBinaryOperator2()
    {
        int x = 5;
        if ((x | 42) == 47) {} //@ALWAYS_TRUE
        if ((x | 0) == 5) {} //@ALWAYS_TRUE
        if ((-x | 42) == -5) {} //@ALWAYS_TRUE
        if ((x | -1) == -1) {} //@ALWAYS_TRUE
        if (((-x) | (-42)) == -1){} //@ALWAYS_TRUE

        if ((x & 42) == 0) {} //@ALWAYS_TRUE
        if ((x & 0) == 0) {} //@ALWAYS_TRUE
        if ((-x & 42) == 42) {} //@ALWAYS_TRUE
        if ((x & -1) == 5) {} //@ALWAYS_TRUE
        if (((-x) & (-42)) == -46) {} //@ALWAYS_TRUE

        if ((x ^ 42) == 47) {} //@ALWAYS_TRUE
        if ((x ^ 0) == 5) {} //@ALWAYS_TRUE
        if ((-x ^ 42) == -47) {} //@ALWAYS_TRUE
        if ((x ^ -1) == -6) {} //@ALWAYS_TRUE
        if (((-x) ^ (-42)) == 45) {} //@ALWAYS_TRUE

        int y = -5;
        if ((y | 42) == -5) {} //@ALWAYS_TRUE
        if ((y | 3) == -5) {} //@ALWAYS_TRUE
        if ((y | -3) == -1) {} //@ALWAYS_TRUE
        if ((y & 42) == 42) {} //@ALWAYS_TRUE
        if ((y & 3) == 3) {} //@ALWAYS_TRUE
        if ((y & -3) == -7) {} //@ALWAYS_TRUE
        if ((y ^ 42) == -47) {} //@ALWAYS_TRUE
        if ((y ^ 3) == -8) {} //@ALWAYS_TRUE
        if ((y ^ -3) == 6) {} //@ALWAYS_TRUE
    }

    void testBinaryOperator3()
    {
        boolean a = true;
        boolean b = false;
        boolean c = true;
        if (a & b) {} //@ALWAYS_FALSE
        if (a | b) {} //@ALWAYS_TRUE
        if (a ^ b) {} //@ALWAYS_TRUE
        if (a ^ c) {} //@ALWAYS_FALSE
    }

    void testBinaryOperator4()
    {
        int x = 5;
        if ((x >> 1) == 2) {} //@ALWAYS_TRUE
        if ((x >> 2) == 1) {} //@ALWAYS_TRUE
        if ((x << 1) == 10) {} //@ALWAYS_TRUE
        if ((x << 2) == 20) {} //@ALWAYS_TRUE

        int y = -5;
        if ((y >>> 1) == 2147483645) {} //@ALWAYS_TRUE
        if ((y >>> 2) == 1073741822) {} //@ALWAYS_TRUE
        if ((y >> 1) == -3) {} //@ALWAYS_TRUE
        if ((y >> 2) == -2) {} //@ALWAYS_TRUE
    }

    void testBinaryOperator1()
    {
        int x = 5;
        if (x == 5) {} //@ALWAYS_TRUE
        if (x != 5) {} //@ALWAYS_FALSE
        if (x == 1 + 1) {} //@ALWAYS_FALSE
        if (x == 1 + 1 + 2 + 1) {} //@ALWAYS_TRUE
        if (x < 3) {} //@ALWAYS_FALSE
        if (x < 6) {} //@ALWAYS_TRUE
        if (x <= 5) {} //@ALWAYS_TRUE
        if (x <= 3) {} //@ALWAYS_FALSE
        if (x > 3) {} //@ALWAYS_TRUE
        if (x >= 5) {} //@ALWAYS_TRUE

        int y = 5;
        if (x == y) {} //@ALWAYS_TRUE
        if (x != y) {} //@ALWAYS_FALSE
        if (x == x) {} //@ALWAYS_TRUE
        if (x != x) {} //@ALWAYS_FALSE
        x = 6;
        if (x != y) {} //@ALWAYS_TRUE
    }

    void testDiv1(int x)
    {
        int y = x / 5;
        if (x == y) {} //ok
        int z = 100;
        if (z / 10 == 10) {} //@ALWAYS_TRUE
        int a = -100;
        if (a / 10 == -10) {} //@ALWAYS_TRUE
    }

    void testMod1()
    {
        int dividend = 103;
        int divisor = -10;
        if (dividend % divisor == -3) {} //@ALWAYS_FALSE
        if (dividend % divisor == 3) {} //@ALWAYS_TRUE
        dividend = -dividend;
        if (dividend % divisor == -3) {} ///@ALWAYS_TRUE
        if (dividend % divisor == 3) {} ///@ALWAYS_FALSE
    }

    void testMod2(int x)
    {
        int y = x % 5;
        if (y == 5) {} //@ALWAYS_FALSE
        int z = 100;
        if (z % 5 == 0) {} //@ALWAYS_TRUE
        char c1 = 0;
        if (c1 % 5 == 0) {} //@ALWAYS_TRUE
        char c2 = 100;
        if (c2 % 3 == 1) {} //@ALWAYS_TRUE
        byte b = -123;
        if (b % 5 == -3) {} //@ALWAYS_TRUE
    }

    void testMod3()
    {
        int x = -103;
        if (x % 10 == -3) {} //@ALWAYS_TRUE
        int y = -103;
        if (y % -10 == -3) {} //@ALWAYS_TRUE
        int z = 103;
        if (z % -10 == -3) {} //@ALWAYS_FALSE
    }

    void testMod4(int divisor)
    {
        int x = -100;
        if (x % divisor > 0) {} //@ALWAYS_FALSE
        if (x % divisor == 0) {} //ok
    }

    void testShift1()
    {
        if (('z' << 3) == 976) {} //@ALWAYS_TRUE
        if (('z' << 3L) == 976) {} //@ALWAYS_TRUE
        if ((3 << 'a') == 6) {} //@ALWAYS_TRUE
        if ((3L << 'a') == 25769803776L) {} //@ALWAYS_TRUE
        if (('a' << 'b') == 388) {} //@ALWAYS_TRUE

        if ((1 << 3) == 8) {} //@ALWAYS_TRUE
        if ((1 << 3L) == 8) {} //@ALWAYS_TRUE
        if ((1 << 50) == 262144) {} //@ALWAYS_TRUE
        if ((1 << 50L) == 262144) {} //@ALWAYS_TRUE
        if ((1 << 18) == 262144) {} //@ALWAYS_TRUE

        if ((10 << 3) == 80) {} //@ALWAYS_TRUE
        if ((10 << 3L) == 80) {} //@ALWAYS_TRUE

        if ((1L << 3) == 8) {} //@ALWAYS_TRUE
        if ((1L << 3L) == 8) {} //@ALWAYS_TRUE
        if ((1L << 50) == 1125899906842624L) {} //@ALWAYS_TRUE
        if ((1L << 50L) == 1125899906842624L) {} //@ALWAYS_TRUE
        if ((1L << 18) == 262144) {} //@ALWAYS_TRUE
    }

    void testShift2()
    {
        if (('z' >> 3) == 15) {} //@ALWAYS_TRUE
        if (('z' >> 3L) == 15) {} //@ALWAYS_TRUE
        if ((3 >> 'a') == 1) {} //@ALWAYS_TRUE
        if ((3L >> 'a') == 0) {} //@ALWAYS_TRUE
        if (('a' >> 'b') == 24) {} //@ALWAYS_TRUE

        if ((1 >> 3) == 0) {} //@ALWAYS_TRUE
        if ((1 >> 3L) == 0) {} //@ALWAYS_TRUE
        if ((1 >> 50) == 0) {} //@ALWAYS_TRUE
        if ((1 >> 50L) == 0) {} //@ALWAYS_TRUE
        if ((1 >> 18) == 0) {} //@ALWAYS_TRUE

        if ((10 >> 3) == 1) {} //@ALWAYS_TRUE
        if ((10 >> 3L) == 1) {} //@ALWAYS_TRUE

        if ((100L >> 3) == 12) {} //@ALWAYS_TRUE
        if ((100L >> 3L) == 12) {} //@ALWAYS_TRUE
        if ((100L >> 50) == 0) {} //@ALWAYS_TRUE
        if ((100L >> 50L) == 0) {} //@ALWAYS_TRUE
        if ((100L >> 18) == 0) {} //@ALWAYS_TRUE
    }

    void testShift3()
    {
        if (('z' >>> 3) == 15) {} //@ALWAYS_TRUE
        if (('z' >>> 3L) == 15) {} //@ALWAYS_TRUE
        if ((3 >>> 'a') == 1) {} //@ALWAYS_TRUE
        if ((3L >>> 'a') == 0) {} //@ALWAYS_TRUE
        if (('a' >>> 'b') == 24) {} //@ALWAYS_TRUE

        if ((1 >>> 3) == 0) {} //@ALWAYS_TRUE
        if ((1 >>> 3L) == 0) {} //@ALWAYS_TRUE
        if ((1 >>> 50) == 0) {} //@ALWAYS_TRUE
        if ((1 >>> 50L) == 0) {} //@ALWAYS_TRUE
        if ((1 >>> 18) == 0) {} //@ALWAYS_TRUE

        if ((10 >>> 3) == 1) {} //@ALWAYS_TRUE
        if ((10 >>> 3L) == 1) {} //@ALWAYS_TRUE

        if ((100L >>> 3) == 12) {} //@ALWAYS_TRUE
        if ((100L >>> 3L) == 12) {} //@ALWAYS_TRUE
        if ((100L >>> 50) == 0) {} //@ALWAYS_TRUE
        if ((100L >>> 50L) == 0) {} //@ALWAYS_TRUE
        if ((100L >>> 18) == 0) {} //@ALWAYS_TRUE
    }

    void testShift4(short s, byte b)
    {
        s = 0b1100;
        b = 2;
        if ((s >> b) == 0b11) {} //@ALWAYS_TRUE
    }
}
