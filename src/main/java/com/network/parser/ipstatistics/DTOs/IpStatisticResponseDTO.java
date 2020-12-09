package com.network.parser.ipstatistics.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IpStatisticResponseDTO {

    private String ipAddress;

    private long addressCount;

}
