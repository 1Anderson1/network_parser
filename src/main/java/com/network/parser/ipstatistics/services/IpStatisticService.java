package com.network.parser.ipstatistics.services;

import com.google.common.base.Strings;
import com.network.parser.ipstatistics.DTOs.IpStatisticResponseDTO;
import com.network.parser.ipstatistics.entities.IpStatistic;
import com.network.parser.ipstatistics.mappers.IpStatisticMapper;
import com.network.parser.ipstatistics.repositories.IpStatisticRepository;
import com.network.parser.ipstatistics.utils.GoogleParserUtil;
import com.network.parser.ipstatistics.utils.IpGeneratorUtil;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class IpStatisticService {

    private final IpStatisticMapper ipStatisticMapper = Mappers.getMapper(IpStatisticMapper.class);
    @Autowired
    private IpStatisticRepository ipStatisticRepository;

    public IpStatisticResponseDTO getOneAddressStatistic(String ipAddress) throws IOException {
        if (!ipIsValid(ipAddress)) {
            throw new IllegalArgumentException("ip address not valid");
        }
        long resultsCount = GoogleParserUtil.getResultsCount(ipAddress);
        return new IpStatisticResponseDTO(ipAddress, resultsCount);
    }

    public IpStatisticResponseDTO getOneAddressStatisticFromDb(String ipAddress) throws IOException {
        if (!ipIsValid(ipAddress)) {
            throw new IllegalArgumentException("ip address not valid");
        }
        IpStatistic ipStatistic = ipStatisticRepository.findByIpAddress(ipAddress);
        return ipStatisticMapper.toDto(ipStatistic);
    }

    public List<IpStatisticResponseDTO> generateSaveAndGetMultipleIpStatistics(List<String> ipAddresses) {
        List<String> filteredAddresses = filterAndValidateAddresses(ipAddresses);
        List<IpStatisticResponseDTO> resultIpStatistic = createIpStatisticDTOByIpAddresses(filteredAddresses);
        createIpStatisticEntityByDTOAndSave(resultIpStatistic);
        return resultIpStatistic;
    }

    private void createIpStatisticEntityByDTOAndSave(List<IpStatisticResponseDTO> ipStatisticResponseDTOS) {
        List<IpStatistic> ipStatistics = ipStatisticMapper.toEntityList(ipStatisticResponseDTOS);
        ipStatisticRepository.saveAll(ipStatistics);
    }

    private List<IpStatisticResponseDTO> createIpStatisticDTOByIpAddresses(List<String> ipAddresses) {
        List<IpStatisticResponseDTO> resultIpStatistic = new ArrayList<>();
        ipAddresses.forEach(ipAddress -> {
            try {
                resultIpStatistic.add(new IpStatisticResponseDTO(ipAddress, GoogleParserUtil.getResultsCount(ipAddress)));
                TimeUnit.SECONDS.sleep(1);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        return resultIpStatistic;
    }

    private List<String> filterAndValidateAddresses(List<String> ipAddresses) throws IllegalArgumentException {
        List<String> filteredAddresses = ipAddresses.stream().filter(this::ipIsValid).collect(Collectors.toList());
        if (filteredAddresses.isEmpty()) {
            throw new IllegalArgumentException("ip addresses not valid");
        }
        return filteredAddresses;
    }

    private boolean ipIsValid(String ipAddress) {
        InetAddressValidator validator = InetAddressValidator.getInstance();
        return !Strings.isNullOrEmpty(ipAddress)
                && validator.isValid(ipAddress)
                && IpGeneratorUtil.getAllPrivateIpAddressesEndedWithOne().contains(ipAddress);
    }
}
