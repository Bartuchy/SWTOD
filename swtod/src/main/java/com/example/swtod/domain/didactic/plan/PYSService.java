package com.example.swtod.domain.didactic.plan;

import com.example.swtod.common.exception.UnsignedGroupsException;
import com.example.swtod.domain.common.plan.year.PlanYearService;
import com.example.swtod.domain.common.subject.SubjectService;
import com.example.swtod.domain.didactic.plan.dto.PYSRecordDto;
import com.example.swtod.domain.didactic.plan.management.PYSCsvProcessor;
import com.example.swtod.domain.didactic.plan.management.PYSMapper;
import com.example.swtod.domain.teaching.staff.PYSURepository;
import com.example.swtod.domain.teaching.staff.PlanYearSubjectUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.example.swtod.domain.common.classes.type.ClassesTypeConst.*;

@Service
@RequiredArgsConstructor
public class PYSService {
    private final PYSRepository pysRepository;
    private final PYSURepository pysuRepository;
    private final PlanYearService planYearService;
    private final SubjectService subjectService;

    private final PYSCsvProcessor processor;
    private final PYSMapper mapper;

    public void savePlanYearSubjects(MultipartFile csvFile, String facultyName, String academicYear) throws IOException {
        List<PlanYearSubject> planYearSubjects = processor.processPlanYearSubjectCsv(csvFile, facultyName, academicYear);
        pysRepository.saveAll(planYearSubjects);
    }

    @Transactional
    public void removeDidacticPlan() {
        pysRepository.deleteAll();
        planYearService.removeAllData();
        subjectService.removeAllData();
    }

    @Transactional
    public void removeDidacticPlanRecord(Long subjectId) {
        List<PlanYearSubject> planYearSubjects = pysRepository
                .findPlanYearSubjectBySubjectId(subjectId);

        planYearSubjects.forEach(planYearSubject -> pysRepository
                .deletePlanYearSubjectById(planYearSubject.getId()));

        if (!planYearSubjects.isEmpty())
            removeSingleRelatedEntities(planYearSubjects.get(0));
    }

    public List<PYSRecordDto> getDidacticPlan() {
        List<PlanYearSubject> planYearSubjects = pysRepository.findAll();
        return mapper.mapEntitiesToDtos(planYearSubjects);
    }

    public void checkIfAllGroupsAreAssigned(String academicYear) {
        List<PlanYearSubject> planYearSubjects = pysRepository.findPlanYearSubjectByAcademicYear(academicYear);
        List<PlanYearSubjectUser> planYearSubjectUsers = pysuRepository.findAll();

        subtractAssignedGroupsFromPys(planYearSubjects, planYearSubjectUsers);

        if (planYearSubjects.stream().anyMatch(pys -> pys.getGroupsNumber() > 0)) {
            throw new UnsignedGroupsException("There are groups without assignment");
        }

    }

    public List<PYSRecordDto> getDidacticPlanByAcademicYearWithAllGroups(String academicYear) {
        List<PlanYearSubject> planYearSubjects = pysRepository.findPlanYearSubjectByAcademicYear(academicYear);
        return mapper.mapEntitiesToDtos(planYearSubjects);
    }

    public List<PYSRecordDto> getDidacticPlanByAcademicYear(String academicYear) {
        List<PlanYearSubject> planYearSubjects = pysRepository.findPlanYearSubjectByAcademicYear(academicYear);
        List<PlanYearSubjectUser> planYearSubjectUsers = pysuRepository.findAll();

        subtractAssignedGroupsFromPys(planYearSubjects, planYearSubjectUsers);
        return mapper.mapEntitiesToDtos(planYearSubjects);
    }

    public void savePlanYearSubjectRecord(PYSRecordDto recordDto) {
        List<PlanYearSubject> planYearSubjects = mapper.mapDtosToEntities(List.of(recordDto));
        pysRepository.saveAll(planYearSubjects);
    }

    public List<PYSRecordDto> getDidacticPlanBySubjectId(Long subjectId) {
        List<PlanYearSubject> planYearSubjects = pysRepository
                .findPlanYearSubjectBySubjectId(subjectId);

        return mapper.mapEntitiesToDtos(planYearSubjects);
    }

    public PYSRecordDto getSubjectBySubjectId(Long subjectId) {
        List<PlanYearSubject> planYearSubjects = pysRepository
                .findPlanYearSubjectBySubjectId(subjectId);

        List<PYSRecordDto> dtos = mapper.mapEntitiesToDtos(planYearSubjects);

        return dtos.get(0);
    }

    public void updatePlanYearSubjectRecord(Long subjectId, PYSRecordDto updatingDto) {
        List<PlanYearSubject> planYearSubjectUsers = pysRepository.findPlanYearSubjectBySubjectId(subjectId);
        planYearSubjectUsers.forEach(pys -> updatePlanYearSubjectData(pys, updatingDto));
        pysRepository.saveAll(planYearSubjectUsers);

    }

    private void removeSingleRelatedEntities(PlanYearSubject planYearSubject) {
        Long planYearId = planYearSubject.getPlanYear().getId();
        Long subjectId = planYearSubject.getSubject().getId();

        planYearService.removeById(planYearId);
        subjectService.removeById(subjectId);
    }

    private void subtractAssignedGroupsFromPys(List<PlanYearSubject> planYearSubjects, List<PlanYearSubjectUser> planYearSubjectUsers) {
        for (PlanYearSubject pys: planYearSubjects) {
            for (PlanYearSubjectUser pysu: planYearSubjectUsers) {
                if (pys.getId().equals(pysu.getPlanYearSubject().getId())) {
                    pys.setGroupsNumber(pys.getGroupsNumber() - pysu.getGroupsNumber());
                }
            }
        }
    }

    private void updatePlanYearSubjectData(PlanYearSubject pys, PYSRecordDto updatingDto) {
        pys.getFaculty().setName(updatingDto.getFacultyName());
        pys.getPlanYear().setYear(updatingDto.getYear());
        pys.getSubject().getFieldOfStudies().setName(updatingDto.getFieldOfStudiesName());
        pys.getStudiesType().setName(updatingDto.getTypeOfStudiesName());
        pys.getSubject().setName(updatingDto.getSubjectName());
        pys.setWeeksNumber(updatingDto.getWeeksPerSemester());

        if (pys.getClassesType().getName().equals(LECTURE_NAME)) {
            pys.setHoursPerWeek(updatingDto.getLectureHoursNumberPerWeek());
            pys.setGroupsNumber(updatingDto.getGroupsPerLecture());
            //recordDto.setLectureHoursNumber(updatingDto.getLectureHoursNumber());

        }

        if (pys.getClassesType().getName().equals(EXERCISE_NAME)) {
            pys.setHoursPerWeek(updatingDto.getExerciseHoursNumberPerWeek());
            pys.setGroupsNumber(updatingDto.getGroupsPerExercise());
            //recordDto.setExerciseHoursNumber(updatingDto.getExerciseHoursNumber());
        }

        if (pys.getClassesType().getName().equals(LABORATORY_NAME)) {
            pys.setHoursPerWeek(updatingDto.getLaboratoryHoursNumberPerWeek());
            pys.setGroupsNumber(updatingDto.getGroupsPerLaboratory());
           // recordDto.setLaboratoryHoursNumber(updatingDto.getLaboratoryHoursNumber());
        }

        if (pys.getClassesType().getName().equals(PROJECT_NAME)) {
            pys.setHoursPerWeek(updatingDto.getProjectHoursNumberPerWeek());
            pys.setGroupsNumber(updatingDto.getGroupsPerProject());
            //recordDto.setProjectHoursNumber(updatingDto.getProjectHoursNumber());
        }

        if (pys.getClassesType().getName().equals(SEMINARY_NAME)) {
            pys.setHoursPerWeek(updatingDto.getSeminaryHoursNumberPerWeek());
            pys.setGroupsNumber(updatingDto.getGroupsPerSeminary());
            //recordDto.setSeminaryHoursNumber(updatingDto.getSeminaryHoursNumber());
        }
        pys.setStudentsNumber(updatingDto.getNumberOfStudents());

        pys.setSemester(updatingDto.getSemesterType().charAt(0));
        //pys.set(updatingDto.getHoursTotal());

    }
}
