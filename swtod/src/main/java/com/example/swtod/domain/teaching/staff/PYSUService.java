package com.example.swtod.domain.teaching.staff;

import com.example.swtod.configs.exception.UserNotFoundException;
import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import com.example.swtod.domain.didactic.plan.PlanYearSubjectRepository;
import com.example.swtod.domain.user.User;
import com.example.swtod.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PYSUService {
    private final PYSURepository pysuRepository;
    private final PlanYearSubjectRepository planYearSubjectRepository;
    private final UserRepository userRepository;

    public void assignGroupToUser(Long userId, Long subjectId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException(String.format("User with id '%d' not found", userId)));
        List<PlanYearSubject> planYearSubjects = planYearSubjectRepository.findPlanYearSubjectBySubjectId(subjectId);


    }
}
