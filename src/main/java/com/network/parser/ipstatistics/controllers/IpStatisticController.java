package com.network.parser.ipstatistics.controllers;

import com.network.parser.ipstatistics.DTOs.IpStatisticRequestDTO;
import com.network.parser.ipstatistics.DTOs.IpStatisticResponseDTO;
import com.network.parser.ipstatistics.services.IpStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class IpStatisticController {

    @Autowired
    private IpStatisticService ipStatisticService;

    @PostMapping(value = "/generate")
    @ResponseBody
    public List<IpStatisticResponseDTO> generateStatistic(@RequestBody IpStatisticRequestDTO requestDto) {
        try {
            return ipStatisticService.generateSaveAndGetMultipleIpStatistics(requestDto.getIpAddresses());
        } catch (Exception e) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
    }

    @GetMapping(value = "/getOne")
    @ResponseBody
    public IpStatisticResponseDTO getOneAddress(@RequestParam(name = "ip") String ipAddress) {
        try {
            return ipStatisticService.getOneAddressStatistic(ipAddress);
        } catch (Exception e) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
    }

    @GetMapping(value = "/getOneFromDb")
    @ResponseBody
    public IpStatisticResponseDTO getOneAddressFromDb(@RequestParam(name = "ip") String ipAddress) {
        try {
            return ipStatisticService.getOneAddressStatisticFromDb(ipAddress);
        } catch (Exception e) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
    }
}
