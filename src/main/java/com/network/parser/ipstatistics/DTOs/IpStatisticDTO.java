package com.network.parser.ipstatistics.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IpStatisticDTO {

    private String ipAddress;

    private int addressCount;
}
