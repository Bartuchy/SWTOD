package com.example.swtod.domain.teaching.staff;

import com.example.swtod.domain.didactic.plan.PlanYearSubjectRepository;
import com.example.swtod.domain.teaching.staff.dto.AssignedGroupsDto;
import com.example.swtod.domain.teaching.staff.management.PYSUMapper;
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
    private final PYSUMapper mapper;

    public void assignGroupToUser(Long userId, Long subjectId, AssignedGroupsDto groupsDto) {
        List<PlanYearSubjectUser> planYearSubjectUsers = mapper.mapRequestDataToEntity(userId, subjectId, groupsDto);
        pysuRepository.saveAll(planYearSubjectUsers);
    }
}
