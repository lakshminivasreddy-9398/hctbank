package com.mybankingapplication.hctbank.repository;

import com.mybankingapplication.hctbank.model.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails,Long> {
}
