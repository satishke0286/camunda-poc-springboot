package com.citizen.camunda.poc.repo;

import com.citizen.camunda.poc.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Provider SET status = :status where id = :providerId")
    int updateStatusById(Long providerId, String status);
}
