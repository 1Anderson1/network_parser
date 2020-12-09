package com.network.parser.ipstatistics.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GoogleParserUtilTest {

    @Test
    void getSearchResultCount() throws IOException {
        long test = GoogleParserUtil.getResultsCount("test");
        assertTrue(test > 0);
    }
}