package com.ucoshopapi.ucoshopproccesantenclient.repositories.frequently_question;

import com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question.UserDomainFrequentlyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFrequentlyQuestionRepository extends JpaRepository<UserDomainFrequentlyQuestion, Long> {
}
