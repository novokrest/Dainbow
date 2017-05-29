package com.oneuse.dainbow.books;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Konstantin Novokreshchenov (knovokresch@yamoney.ru)
 * @since 27.05.2017
 */
public class LoggerTest {
    private static final Logger LOG = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test() {
        LOG.info("LoggerTest.test()");
    }
}
