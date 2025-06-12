package com.ucoshopapi.ucoshopproccesantenclient.repositories.frequently_question;

import com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question.DomainAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<DomainAnswer, Long> {
    List<DomainAnswer> findByPreguntaId(Long preguntaId);
}
