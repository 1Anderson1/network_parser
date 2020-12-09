package com.network.parser.ipstatistics.repositories;

import com.network.parser.ipstatistics.entities.IpStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpStatisticRepository extends JpaRepository<IpStatistic, Long> {

    IpStatistic findByIpAddress(String ipAddress);
}
