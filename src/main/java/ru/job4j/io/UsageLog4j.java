package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte bt = 125;
        short shrt = 16255;
        int intgr = 2575764;
        long lng = 6873459927495L;
        char chr = 'A';
        float flt = 67.432f;
        double dbl = 375.465484839022585;
        boolean bln = true;

        LOG.debug("\nByte: {}, \nShort: {}, \nInteger: {}, \nLong: {}, \nChar: {}, \nFloat: {}, \nDouble: {}, \nBoolean: {}",
                bt, shrt, intgr, lng, chr, flt, dbl, bln);
    }
}