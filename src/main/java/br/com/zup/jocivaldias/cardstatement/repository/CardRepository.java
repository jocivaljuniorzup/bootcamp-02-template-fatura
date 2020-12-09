package br.com.zup.jocivaldias.cardstatement.repository;

import br.com.zup.jocivaldias.cardstatement.entity.Card;
import br.com.zup.jocivaldias.cardstatement.entity.enums.CardStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {

    Optional<Card> findByCardNumber(String cardNumber);
    List<Card> findAllByStatus(CardStatus unprocessed, Pageable pageRequest);
}
