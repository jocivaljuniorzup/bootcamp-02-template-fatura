package br.com.zup.jocivaldias.cardstatement.repository;

import br.com.zup.jocivaldias.cardstatement.entity.Card;
import br.com.zup.jocivaldias.cardstatement.entity.CardStatement;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardStatementRepository extends JpaRepository<CardStatement, UUID> {

//    Optional<CardStatement> findByEndDateBeforeAndCard(LocalDate effectiveOn, Card card, Pageable pageable);

    Optional<CardStatement> findTop1ByEndDateAfterAndCardOrderByEndDateAsc(LocalDate toLocalDate, Card card);
}
