package com.ucoshopapi.ucoshopproccesantenclient.repositories.frequently_question;


import com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question.DomainQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<DomainQuestion, Long> {
}
