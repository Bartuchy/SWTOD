package com.example.swtod.domain.teaching.staff;

import com.example.swtod.domain.common.plan.year.PlanYear;
import com.example.swtod.domain.common.plan.year.PlanYearRepository;
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
    private final PlanYearRepository planYearRepository;
    private final PYSUMapper mapper;

    @Transactional
    public void assignGroupToUser(Long userId, Long subjectId, AssignedGroupsDto groupsDto) {
        List<PlanYearSubjectUser> planYearSubjectUsers = mapper.mapRequestDataToEntity(userId, subjectId, groupsDto);
        pysuRepository.saveAll(planYearSubjectUsers);
    }

    public List<PYSURecordDto> getTeachingStaff(String academicYear, String userNameSurname, String subjectName) {
        List<PlanYearSubjectUser> planYearSubjectUsers = pysuRepository
                .findPlanYearSubjectUsersByAcademicYear(academicYear);

        List<PYSURecordDto> pysuRecordDtos = mapper.mapEntitiesToDtos(planYearSubjectUsers);

        pysuRecordDtos = filterStaffByUserNameSurname(userNameSurname, pysuRecordDtos);
        pysuRecordDtos = filterStaffBySubjectName(subjectName, pysuRecordDtos);

        return pysuRecordDtos;
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

    public List<String> getAcademicYearsDistinct() {
        return planYearRepository
                .findAll()
                .stream()
                .map(PlanYear::getName)
                .distinct()
                .toList();
    }

    private void updateAssignedGroups(PYSURecordDto pysuRecordDto, AssignedGroupsDto groupsDto) {
        pysuRecordDto.setGroupsPerLecture(groupsDto.getLectureGroupsNumber());
        pysuRecordDto.setGroupsPerExercise(groupsDto.getExerciseGroupsNumber());
        pysuRecordDto.setGroupsPerLaboratory(groupsDto.getLaboratoryGroupsNumber());
        pysuRecordDto.setGroupsPerProject(groupsDto.getProjectGroupsNumber());
        pysuRecordDto.setGroupsPerSeminary(groupsDto.getSeminaryGroupsNumber());
    }

    private List<PYSURecordDto> filterStaffByUserNameSurname(String userNameSurname, List<PYSURecordDto> pysuRecordDtos) {
        if (userNameSurname != null) {
            pysuRecordDtos = pysuRecordDtos
                    .stream()
                    .filter(recordDto -> {
                        String lowercaseNameSurname = recordDto.getUserNameSurname().toLowerCase();
                        return lowercaseNameSurname.startsWith(userNameSurname.toLowerCase());
                    })
                    .toList();
        }

        return pysuRecordDtos;
    }

    private List<PYSURecordDto> filterStaffBySubjectName(String subjectName, List<PYSURecordDto> pysuRecordDtos) {
        if (subjectName != null) {
            pysuRecordDtos = pysuRecordDtos
                    .stream()
                    .filter(recordDto -> {
                        String lowercaseSubjectName = recordDto.getSubjectName().toLowerCase();
                        return lowercaseSubjectName.startsWith(subjectName.toLowerCase());
                    })
                    .toList();
        }

        return pysuRecordDtos;
    }
}
