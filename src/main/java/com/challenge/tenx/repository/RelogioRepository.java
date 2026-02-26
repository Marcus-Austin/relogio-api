package com.challenge.tenx.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.challenge.tenx.entity.Relogio;

public interface RelogioRepository extends JpaRepository<Relogio, UUID>, JpaSpecificationExecutor<Relogio> {

}
