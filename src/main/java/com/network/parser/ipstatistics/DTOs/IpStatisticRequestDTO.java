package com.network.parser.ipstatistics.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IpStatisticRequestDTO {

    List<String> ipAddresses;

}
