package com.example.swtod.domain.didactic.plan;

import com.example.swtod.domain.didactic.plan.dto.PlanYearSubjectRecordDto;
import com.example.swtod.domain.didactic.plan.management.PlanYearSubjectCsvProcessor;
import com.example.swtod.domain.didactic.plan.management.PlanYearSubjectMapper;
import com.example.swtod.domain.common.plan.year.PlanYearService;
import com.example.swtod.domain.common.subject.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanYearSubjectService {
    private final PlanYearSubjectRepository planYearSubjectRepository;
    private final PlanYearService planYearService;
    private final SubjectService subjectService;

    private final PlanYearSubjectCsvProcessor processor;
    private final PlanYearSubjectMapper mapper;

    public void savePlanYearSubjects(MultipartFile csvFile, String facultyName) throws IOException {
        List<PlanYearSubject> planYearSubjects = processor.processPlanYearSubjectCsv(csvFile, facultyName);
        planYearSubjectRepository.saveAll(planYearSubjects);
    }

    @Transactional
    public void removeDidacticPlan() {
        planYearSubjectRepository.deleteAll();
        planYearService.removeAllData();
        subjectService.removeAllData();
    }

    @Transactional
    public void removeDidacticPlanSingle(Long subjectId) {
        List<PlanYearSubject> planYearSubjects = planYearSubjectRepository
                .findPlanYearSubjectBySubjectId(subjectId);

        planYearSubjects.forEach(planYearSubject -> planYearSubjectRepository
                .deletePlanYearSubjectById(planYearSubject.getId()));

        if (!planYearSubjects.isEmpty())
            removeSingleRelatedEntities(planYearSubjects.get(0));
    }

    public List<PlanYearSubjectRecordDto> getDidacticPlan() {
        List<PlanYearSubject> planYearSubjects = planYearSubjectRepository.findAll();
        return mapper.mapEntitiesToDtos(planYearSubjects);
    }

    public void savePlanYearSubjectSingle(PlanYearSubjectRecordDto recordDto) {
        List<PlanYearSubject> planYearSubjects = mapper.mapDtosToEntities(List.of(recordDto));
        planYearSubjectRepository.saveAll(planYearSubjects);
    }

    public List<PlanYearSubjectRecordDto> getDidacticPlanBySubjectId(Long subjectId) {
        List<PlanYearSubject> planYearSubjects = planYearSubjectRepository
                .findPlanYearSubjectBySubjectId(subjectId);

        return mapper.mapEntitiesToDtos(planYearSubjects);
    }

    public void updatePlanYearSubjectSingle(Long subjectId, PlanYearSubjectRecordDto updatingDto) {
        List<PlanYearSubjectRecordDto> recordDtos = getDidacticPlanBySubjectId(subjectId);
        recordDtos.forEach(recordDto -> updatePlanYearSubjectData(recordDto, updatingDto));

        List<PlanYearSubject> planYearSubjectsUpdated = mapper.mapDtosToEntities(recordDtos);
        planYearSubjectRepository.saveAll(planYearSubjectsUpdated);
    }

    private void removeSingleRelatedEntities(PlanYearSubject planYearSubject) {
        Long planYearId = planYearSubject.getPlanYear().getId();
        Long subjectId = planYearSubject.getSubject().getId();

        planYearService.removeById(planYearId);
        subjectService.removeById(subjectId);
    }

    private void updatePlanYearSubjectData(PlanYearSubjectRecordDto recordDto, PlanYearSubjectRecordDto updatingDto) {
        recordDto.setFacultyName(updatingDto.getFacultyName());
        recordDto.setYear(updatingDto.getYear());
        recordDto.setFieldOfStudiesName(updatingDto.getFieldOfStudiesName());
        recordDto.setTypeOfStudiesName(updatingDto.getTypeOfStudiesName());
        recordDto.setSubjectName(updatingDto.getSubjectName());
        recordDto.setWeeksPerSemester(updatingDto.getWeeksPerSemester());
        recordDto.setLectureHoursNumberPerWeek(updatingDto.getLectureHoursNumberPerWeek());
        recordDto.setExerciseHoursNumberPerWeek(updatingDto.getExerciseHoursNumberPerWeek());
        recordDto.setLaboratoryHoursNumberPerWeek(updatingDto.getLaboratoryHoursNumberPerWeek());
        recordDto.setProjectHoursNumberPerWeek(updatingDto.getProjectHoursNumberPerWeek());
        recordDto.setSeminaryHoursNumberPerWeek(updatingDto.getSeminaryHoursNumberPerWeek());
        recordDto.setNumberOfStudents(updatingDto.getNumberOfStudents());
        recordDto.setGroupsPerLecture(updatingDto.getGroupsPerLecture());
        recordDto.setLectureHoursNumber(updatingDto.getLectureHoursNumber());
        recordDto.setGroupsPerExercise(updatingDto.getGroupsPerExercise());
        recordDto.setExerciseHoursNumber(updatingDto.getExerciseHoursNumber());
        recordDto.setGroupsPerLaboratory(updatingDto.getGroupsPerLaboratory());
        recordDto.setLaboratoryHoursNumber(updatingDto.getLaboratoryHoursNumber());
        recordDto.setGroupsPerProject(updatingDto.getGroupsPerProject());
        recordDto.setProjectHoursNumber(updatingDto.getProjectHoursNumber());
        recordDto.setGroupsPerSeminary(updatingDto.getGroupsPerSeminary());
        recordDto.setSeminaryHoursNumber(updatingDto.getSeminaryHoursNumber());
        recordDto.setSemesterType(updatingDto.getSemesterType());
        recordDto.setHoursTotal(updatingDto.getHoursTotal());

    }
}
