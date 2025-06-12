package com.ucoshopapi.ucoshopproccesantenclient.repositories.frequently_question;

import com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question.DomainFrequentlyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrequentlyQuestionRepository extends JpaRepository<DomainFrequentlyQuestion, Long> {
}