package com.example.swtod.domain.teaching.staff;

import com.example.swtod.domain.teaching.staff.dto.AssignedGroupsDto;
import com.example.swtod.domain.teaching.staff.dto.PYSURecordDto;
import com.example.swtod.domain.teaching.staff.management.PYSUMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PYSUService {
    private final PYSURepository pysuRepository;
    private final PYSUMapper mapper;

    public void assignGroupToUser(Long userId, Long subjectId, AssignedGroupsDto groupsDto) {
        List<PlanYearSubjectUser> planYearSubjectUsers = mapper.mapRequestDataToEntity(userId, subjectId, groupsDto);
        pysuRepository.saveAll(planYearSubjectUsers);
    }

    public List<PYSURecordDto> getTeachingStaff() {
        List<PlanYearSubjectUser> planYearSubjectUsers = pysuRepository.findAll();
        return mapper.mapEntitiesToDtos(planYearSubjectUsers);
    }
}
