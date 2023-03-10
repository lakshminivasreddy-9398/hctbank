package com.mybankingapplication.hctbank.repository;

import com.mybankingapplication.hctbank.model.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDetailsRepository extends JpaRepository<Credentials,Long> {
}
