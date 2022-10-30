package com.example.swtod.domain.didactic.plan.management.transfer;

import com.example.swtod.domain.didactic.plan.dto.PlanYearSubjectDto;
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

    public PYSRelatedEntitiesTransporter getAllRelatedEntities(PlanYearSubjectDto planYearSubjectDto) {
        return new PYSRelatedEntitiesTransporter(
                this.getStudiesType(planYearSubjectDto),
                this.getPlanYear(planYearSubjectDto),
                this.getSubject(planYearSubjectDto),
                this.getFaculty(planYearSubjectDto));

    }

    public StudiesType getStudiesType(PlanYearSubjectDto planYearSubjectDto) {
        return studiesTypeService.getStudiesTypeByName(planYearSubjectDto.getTypeOfStudiesName());
    }

    public PlanYear getPlanYear(PlanYearSubjectDto planYearSubjectDto) {
        return planYearService.saveNewPlanYear(planYearSubjectDto.getSubjectName(), planYearSubjectDto.getYear());
    }

    public Subject getSubject(PlanYearSubjectDto planYearSubjectDto) {
        int degreeNumber = calculateDegreeNumber(planYearSubjectDto);
        int semesterNumber = calculateSemesterNumber(planYearSubjectDto);

        return subjectService.saveNewSubject(
                planYearSubjectDto.getSubjectName(),
                planYearSubjectDto.getFieldOfStudiesName(),
                degreeNumber,
                planYearSubjectDto.getSemesterType(),
                semesterNumber
        );
    }

    public Faculty getFaculty(PlanYearSubjectDto planYearSubjectDto) {
        return facultyService.getFacultyByName(planYearSubjectDto.getFacultyName());
    }

    private int calculateDegreeNumber(PlanYearSubjectDto planYearSubjectDto) {
        int degreeNumber = 1;

        if (planYearSubjectDto.getFieldOfStudiesName().charAt(0) == 'M')
            degreeNumber = 2;

        return degreeNumber;
    }

    private int calculateSemesterNumber(PlanYearSubjectDto planYearSubjectDto) {
        int semesterNumber = planYearSubjectDto.getYear() * 2 - 1;

        if (planYearSubjectDto.getSemesterType().charAt(0) == 'L')
            semesterNumber = planYearSubjectDto.getYear() * 2;

        return semesterNumber;
    }

}
