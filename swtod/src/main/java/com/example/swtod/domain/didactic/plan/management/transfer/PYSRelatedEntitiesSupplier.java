package com.example.swtod.domain.didactic.plan.management.transfer;

import com.example.swtod.domain.didactic.plan.dto.PlanYearSubjectRecordDto;
import com.example.swtod.domain.faculty.Faculty;
import com.example.swtod.domain.faculty.FacultyService;
import com.example.swtod.domain.plan.year.PlanYear;
import com.example.swtod.domain.plan.year.PlanYearService;
import com.example.swtod.domain.studies.type.StudiesType;
import com.example.swtod.domain.studies.type.StudiesTypeService;
import com.example.swtod.domain.subject.Subject;
import com.example.swtod.domain.subject.SubjectService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@RequiredArgsConstructor
public class PYSRelatedEntitiesSupplier {
    private final StudiesTypeService studiesTypeService;
    private final PlanYearService planYearService;
    private final SubjectService subjectService;
    private final FacultyService facultyService;

    public PYSRelatedEntitiesTransporter getAllRelatedEntities(PlanYearSubjectRecordDto planYearSubjectRecordDto) {
        return new PYSRelatedEntitiesTransporter(
                this.getStudiesType(planYearSubjectRecordDto),
                this.getPlanYear(planYearSubjectRecordDto),
                this.getSubject(planYearSubjectRecordDto),
                this.getFaculty(planYearSubjectRecordDto));

    }

    public StudiesType getStudiesType(PlanYearSubjectRecordDto planYearSubjectRecordDto) {
        return studiesTypeService.getStudiesTypeByName(planYearSubjectRecordDto.getTypeOfStudiesName());
    }

    public PlanYear getPlanYear(PlanYearSubjectRecordDto planYearSubjectRecordDto) {
        return planYearService.saveNewPlanYear(planYearSubjectRecordDto.getSubjectName(), planYearSubjectRecordDto.getYear());
    }

    public Subject getSubject(PlanYearSubjectRecordDto planYearSubjectRecordDto) {
        int degreeNumber = calculateDegreeNumber(planYearSubjectRecordDto);
        int semesterNumber = calculateSemesterNumber(planYearSubjectRecordDto);

        return subjectService.saveNewSubject(
                planYearSubjectRecordDto.getSubjectName(),
                planYearSubjectRecordDto.getFieldOfStudiesName(),
                degreeNumber,
                planYearSubjectRecordDto.getSemesterType(),
                semesterNumber
        );
    }

    public Faculty getFaculty(PlanYearSubjectRecordDto planYearSubjectRecordDto) {
        return facultyService.getFacultyByName(planYearSubjectRecordDto.getFacultyName());
    }

    private int calculateDegreeNumber(PlanYearSubjectRecordDto planYearSubjectRecordDto) {
        int degreeNumber = 1;

        if (planYearSubjectRecordDto.getFieldOfStudiesName().charAt(0) == 'M')
            degreeNumber = 2;

        return degreeNumber;
    }

    private int calculateSemesterNumber(PlanYearSubjectRecordDto planYearSubjectRecordDto) {
        int semesterNumber = planYearSubjectRecordDto.getYear() * 2 - 1;

        if (planYearSubjectRecordDto.getSemesterType().charAt(0) == 'L')
            semesterNumber = planYearSubjectRecordDto.getYear() * 2;

        return semesterNumber;
    }

}
