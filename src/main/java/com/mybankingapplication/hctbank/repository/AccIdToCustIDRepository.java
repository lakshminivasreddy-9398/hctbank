package com.mybankingapplication.hctbank.repository;

import com.mybankingapplication.hctbank.model.CustToAccMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccIdToCustIDRepository extends JpaRepository<CustToAccMap,Long> {
}
