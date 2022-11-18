package com.example.swtod.domain.teaching.staff;

import com.example.swtod.domain.teaching.staff.dto.AssignedGroupsDto;
import com.example.swtod.domain.teaching.staff.dto.PYSURecordDto;
import com.example.swtod.domain.teaching.staff.management.PYSUMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PYSUService {
    private final PYSURepository pysuRepository;
    private final PYSUMapper mapper;

    @Transactional
    public void assignGroupToUser(Long userId, Long subjectId, AssignedGroupsDto groupsDto) {
        List<PlanYearSubjectUser> planYearSubjectUsers = mapper.mapRequestDataToEntity(userId, subjectId, groupsDto);
        pysuRepository.saveAll(planYearSubjectUsers);
    }

    public List<PYSURecordDto> getTeachingStaff() {
        List<PlanYearSubjectUser> planYearSubjectUsers = pysuRepository.findAll();
        return mapper.mapEntitiesToDtos(planYearSubjectUsers);
    }

    public List<PYSURecordDto> getTeachingStaff(Long userId, Long subjectId) {
        List<PlanYearSubjectUser> planYearSubjectUsers = pysuRepository
                .findPlanYearSubjectUsersByUserIdAndSubjectId(userId, subjectId);

        return mapper.mapEntitiesToDtos(planYearSubjectUsers);
    }

    @Transactional
    public void changeGroupAssignment(Long userId, Long subjectId, AssignedGroupsDto groupsDto) {
        List<PYSURecordDto> pysuRecordDtos = getTeachingStaff(userId, subjectId);
        pysuRecordDtos.forEach(recordDto -> updateAssignedGroups(recordDto, groupsDto));

        List<PlanYearSubjectUser> planYearSubjectUsers = mapper.mapDtosToEntities(pysuRecordDtos);
        pysuRepository.saveAll(planYearSubjectUsers);
    }

    @Transactional
    public void deleteGroupAssignment(Long userId, Long subjectId) {
        pysuRepository.deletePlanYearSubjectUserByUserIdAndSubjectId(userId, subjectId);
    }

    private void updateAssignedGroups(PYSURecordDto pysuRecordDto, AssignedGroupsDto groupsDto) {
        pysuRecordDto.setGroupsPerLecture(groupsDto.getLectureGroupsNumber());
        pysuRecordDto.setGroupsPerExercise(groupsDto.getExerciseGroupsNumber());
        pysuRecordDto.setGroupsPerLaboratory(groupsDto.getLaboratoryGroupsNumber());
        pysuRecordDto.setGroupsPerProject(groupsDto.getProjectGroupsNumber());
        pysuRecordDto.setGroupsPerSeminary(groupsDto.getSeminaryGroupsNumber());

    }
}
