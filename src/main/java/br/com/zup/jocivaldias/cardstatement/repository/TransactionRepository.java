package br.com.zup.jocivaldias.cardstatement.repository;

import br.com.zup.jocivaldias.cardstatement.entity.CardStatement;
import br.com.zup.jocivaldias.cardstatement.entity.Transaction;
import br.com.zup.jocivaldias.cardstatement.entity.enums.CardStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findAllByCardStatementAndCardStatus(CardStatement cardStatement, CardStatus processed, Pageable pageable);

}
