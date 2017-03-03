package com.xpanxion.xpert.generators;

import java.util.Random;

public class RandomString {

    private static final char[] symbols = new char[10 + 26 + 26 + 3 + 8];
    private static final char[] chars = new char[52];
    private static final char[] numbers = new char[10];

    static {
        for (int idx = 0; idx < 10; ++idx) {
            numbers[idx] = (char) ('0' + idx);
        }
        for (int idx = 10; idx < 36; ++idx) {
            chars[idx - 10] = (char) ('a' + idx - 10);
        }
        for (int idx = 36; idx < 62; ++idx) {
            chars[idx - 10] = (char) ('A' + idx - 36);
        }
        /**
         * String
         */
        int total = 0;
        for (int idx = 48; idx <= 57; idx++, total++) {
            symbols[total] = (char) idx;
        }// 10 (10)
        for (int idx = 65; idx <= 90; idx++, total++) {
            symbols[total] = (char) idx;
        } // 26 (36)
        for (int idx = 97; idx <= 122; idx++, total++) {
            symbols[total] = (char) idx;
        } // 26 (62)
        for (int idx = 44; idx <= 46; idx++, total++) {
            symbols[total] = (char) idx;
        } // 3 (65)
        int[] copyFrom = {64, 95, 44, 46, 45, 145, 146, 35};
        //@   _   ,   .   -   ‘     ’    #
        for (int idx = 0; idx < copyFrom.length; idx++, total++) {
            symbols[total] = (char) copyFrom[idx];
        } // 8 (73)
        //System.out.println(total);
    }

    private static Random random = new Random();
    private static char[] buf;

    public static String fullString() {
        return new String(symbols);
    }

    public static String nextString(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("length < 1: " + length);
        }
        buf = new char[length];
        //System.out.println(symbols);
        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buf);
    }

    public static String nextNumber(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("length < 1: " + length);
        }
        buf = new char[length];
        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = numbers[random.nextInt(numbers.length)];
        }
        return new String(buf);
    }

    public static String nextCharacter(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("length < 1: " + length);
        }
        buf = new char[length];
        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = chars[random.nextInt(chars.length)];
        }
        return new String(buf);
    }
}
