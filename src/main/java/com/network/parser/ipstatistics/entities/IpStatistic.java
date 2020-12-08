package com.network.parser.ipstatistics.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ip_statistic")
public class IpStatistic {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "address_cound")
    private int addressCount = 0;
}
