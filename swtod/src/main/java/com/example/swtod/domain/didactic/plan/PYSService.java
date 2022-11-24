package com.example.swtod.domain.didactic.plan;

import com.example.swtod.domain.common.plan.year.PlanYearService;
import com.example.swtod.domain.common.subject.SubjectService;
import com.example.swtod.domain.didactic.plan.dto.PYSRecordDto;
import com.example.swtod.domain.didactic.plan.management.PYSCsvProcessor;
import com.example.swtod.domain.didactic.plan.management.PYSMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PYSService {
    private final PYSRepository PYSRepository;
    private final PlanYearService planYearService;
    private final SubjectService subjectService;

    private final PYSCsvProcessor processor;
    private final PYSMapper mapper;

    public void savePlanYearSubjects(MultipartFile csvFile, String facultyName) throws IOException {
        List<PlanYearSubject> planYearSubjects = processor.processPlanYearSubjectCsv(csvFile, facultyName);
        PYSRepository.saveAll(planYearSubjects);
    }

    @Transactional
    public void removeDidacticPlan() {
        PYSRepository.deleteAll();
        planYearService.removeAllData();
        subjectService.removeAllData();
    }

    @Transactional
    public void removeDidacticPlanSingle(Long subjectId) {
        List<PlanYearSubject> planYearSubjects = PYSRepository
                .findPlanYearSubjectBySubjectId(subjectId);

        planYearSubjects.forEach(planYearSubject -> PYSRepository
                .deletePlanYearSubjectById(planYearSubject.getId()));

        if (!planYearSubjects.isEmpty())
            removeSingleRelatedEntities(planYearSubjects.get(0));
    }

    public List<PYSRecordDto> getDidacticPlan() {
        List<PlanYearSubject> planYearSubjects = PYSRepository.findAll();
        return mapper.mapEntitiesToDtos(planYearSubjects);
    }

    public void savePlanYearSubjectSingle(PYSRecordDto recordDto) {
        List<PlanYearSubject> planYearSubjects = mapper.mapDtosToEntities(List.of(recordDto));
        PYSRepository.saveAll(planYearSubjects);
    }

    public List<PYSRecordDto> getDidacticPlanBySubjectId(Long subjectId) {
        List<PlanYearSubject> planYearSubjects = PYSRepository
                .findPlanYearSubjectBySubjectId(subjectId);

        return mapper.mapEntitiesToDtos(planYearSubjects);
    }

    public PYSRecordDto getSubjectBySubjectId(Long subjectId) {
        List<PlanYearSubject> planYearSubjects = PYSRepository
                .findPlanYearSubjectBySubjectId(subjectId);

        List<PYSRecordDto> dtos = mapper.mapEntitiesToDtos(planYearSubjects);

        return dtos.get(0);
    }

    public void updatePlanYearSubjectSingle(Long subjectId, PYSRecordDto updatingDto) {
        List<PYSRecordDto> recordDtos = getDidacticPlanBySubjectId(subjectId);
        recordDtos.forEach(recordDto -> updatePlanYearSubjectData(recordDto, updatingDto));

        List<PlanYearSubject> planYearSubjectsUpdated = mapper.mapDtosToEntities(recordDtos);
        PYSRepository.saveAll(planYearSubjectsUpdated);
    }

    private void removeSingleRelatedEntities(PlanYearSubject planYearSubject) {
        Long planYearId = planYearSubject.getPlanYear().getId();
        Long subjectId = planYearSubject.getSubject().getId();

        planYearService.removeById(planYearId);
        subjectService.removeById(subjectId);
    }

    private void updatePlanYearSubjectData(PYSRecordDto recordDto, PYSRecordDto updatingDto) {
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
