package com.network.parser.ipstatistics.utils;

import com.google.common.net.InetAddresses;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;


public class IpGeneratorUtil {

    public final static String PRIVATE_NETWORK_START_MUSK_24_BIT = "10.0.0.1";
    public final static String PRIVATE_NETWORK_END_MUSK_24_BIT = "10.255.255.1";
    public final static String PRIVATE_NETWORK_START_MUSK_20_BIT = "172.16.0.1";
    public final static String PRIVATE_NETWORK_END_MUSK_20_BIT = "172.31.255.1";
    public final static String PRIVATE_NETWORK_START_MUSK_16_BIT = "192.168.0.1";
    public final static String PRIVATE_NETWORK_END_MUSK_16_BIT = "192.168.255.1";
    private final static int IP_NEXT_STEP = 256;
    private static final List<String> ipAddresses = new ArrayList<>();

    public static List<String> getAllPrivateIpAddressesEndedWithOne() {
        if (!ipAddresses.isEmpty()) {
            return ipAddresses;
        }
        generateIpAddressesForRangeEndedWithOne(PRIVATE_NETWORK_START_MUSK_16_BIT, PRIVATE_NETWORK_END_MUSK_16_BIT);
        generateIpAddressesForRangeEndedWithOne(PRIVATE_NETWORK_START_MUSK_20_BIT, PRIVATE_NETWORK_END_MUSK_20_BIT);
        generateIpAddressesForRangeEndedWithOne(PRIVATE_NETWORK_START_MUSK_24_BIT, PRIVATE_NETWORK_END_MUSK_24_BIT);
        return ipAddresses;
    }

    private static void generateIpAddressesForRangeEndedWithOne(String startIp, String endIp) {
        ipAddresses.add(startIp);
        String nextIpAddress = getNextIpEndedWithOne(startIp);
        ipAddresses.add(nextIpAddress);
        while (!nextIpAddress.equals(endIp)) {
            nextIpAddress = getNextIpEndedWithOne(nextIpAddress);
            ipAddresses.add(nextIpAddress);
        }
    }

    private static String getNextIpEndedWithOne(String ipAddress) {
        InetAddress correctIpAddress = InetAddresses.forString(ipAddress);
        int nextIpIntValue = InetAddresses.coerceToInteger(correctIpAddress);
        return InetAddresses.toAddrString(InetAddresses.fromInteger(nextIpIntValue + IP_NEXT_STEP));
    }
}
