package br.com.zup.jocivaldias.cardstatement.repository;

import br.com.zup.jocivaldias.cardstatement.entity.CardStatementInstalment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardStatementInstallmentRepository extends JpaRepository<CardStatementInstalment, UUID> {


}
