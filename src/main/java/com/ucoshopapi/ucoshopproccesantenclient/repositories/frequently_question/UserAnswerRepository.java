package com.ucoshopapi.ucoshopproccesantenclient.repositories.frequently_question;

import com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question.UserDomainAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserDomainAnswer, Long> {
    List<UserDomainAnswer> findByPreguntaId(Long preguntaId);
}
