package com.mybankingapplication.hctbank.repository;

import com.mybankingapplication.hctbank.model.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress,Long> {
}
