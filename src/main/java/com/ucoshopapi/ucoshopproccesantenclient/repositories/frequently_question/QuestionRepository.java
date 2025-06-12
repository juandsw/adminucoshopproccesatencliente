package com.ucoshopapi.ucoshopapi.repositories.frequently_question;


import com.ucoshopapi.ucoshopapi.domain.frequently_question.DomainQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<DomainQuestion, Long> {
}
