package com.network.parser.ipstatistics.services;

import com.google.common.base.Strings;
import com.network.parser.ipstatistics.DTOs.IpStatisticDTO;
import com.network.parser.ipstatistics.repositories.IpStatisticRepository;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpStatisticService {


    @Autowired
    private IpStatisticRepository ipStatisticRepository;

    public IpStatisticDTO getOneAddressStatistic(String ipAddress) {
        ipIsValid(ipAddress);
        return null;
    }

    private void ipIsValid(String ipAddress) {
        InetAddressValidator validator = InetAddressValidator.getInstance();
        if (Strings.isNullOrEmpty(ipAddress) || !validator.isValid(ipAddress)) {
            throw new IllegalArgumentException("ip address null or empty or not valid");
        }
    }
}
