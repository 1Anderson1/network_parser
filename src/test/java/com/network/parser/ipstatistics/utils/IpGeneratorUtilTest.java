package com.network.parser.ipstatistics.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IpGeneratorUtilTest {

    @Test
    void getAllIpEndedWithOne() {
        List<String> allIpEndedWithOne = IpGeneratorUtil.getAllPrivateIpAddressesEndedWithOne();
        assertEquals(allIpEndedWithOne.size(), 69888);
    }
}