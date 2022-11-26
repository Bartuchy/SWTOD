package com.example.swtod.domain.didactic.plan.management.transfer;

import com.example.swtod.domain.common.faculty.Faculty;
import com.example.swtod.domain.common.faculty.FacultyService;
import com.example.swtod.domain.common.plan.year.PlanYear;
import com.example.swtod.domain.common.plan.year.PlanYearService;
import com.example.swtod.domain.common.studies.type.StudiesType;
import com.example.swtod.domain.common.studies.type.StudiesTypeService;
import com.example.swtod.domain.common.subject.Subject;
import com.example.swtod.domain.common.subject.SubjectService;
import com.example.swtod.domain.didactic.plan.dto.PYSRecordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PYSRelatedEntitiesSupplier {
    private final StudiesTypeService studiesTypeService;
    private final PlanYearService planYearService;
    private final SubjectService subjectService;
    private final FacultyService facultyService;

    public PYSRelatedEntitiesTransporter getAllRelatedEntities(PYSRecordDto PYSRecordDto) {
        return new PYSRelatedEntitiesTransporter(
                this.getStudiesType(PYSRecordDto),
                this.getPlanYear(PYSRecordDto),
                this.getSubject(PYSRecordDto),
                this.getFaculty(PYSRecordDto));

    }

    public StudiesType getStudiesType(PYSRecordDto PYSRecordDto) {
        return studiesTypeService.getStudiesTypeByName(PYSRecordDto.getTypeOfStudiesName());
    }

    public PlanYear getPlanYear(PYSRecordDto PYSRecordDto) {
        return planYearService.saveNewPlanYear(PYSRecordDto.getAcademicYear(), PYSRecordDto.getYear());
    }

    public Subject getSubject(PYSRecordDto PYSRecordDto) {
        int degreeNumber = calculateDegreeNumber(PYSRecordDto);
        int semesterNumber = calculateSemesterNumber(PYSRecordDto);

        return subjectService.saveNewSubject(
                PYSRecordDto.getSubjectName(),
                PYSRecordDto.getFieldOfStudiesName(),
                degreeNumber,
                PYSRecordDto.getSemesterType(),
                semesterNumber
        );
    }

    public Faculty getFaculty(PYSRecordDto PYSRecordDto) {
        return facultyService.getFacultyByName(PYSRecordDto.getFacultyName());
    }

    private int calculateDegreeNumber(PYSRecordDto PYSRecordDto) {
        int degreeNumber = 1;

        if (PYSRecordDto.getFieldOfStudiesName().charAt(0) == 'M')
            degreeNumber = 2;

        return degreeNumber;
    }

    private int calculateSemesterNumber(PYSRecordDto PYSRecordDto) {
        int semesterNumber = PYSRecordDto.getYear() * 2 - 1;

        if (PYSRecordDto.getSemesterType().charAt(0) == 'L')
            semesterNumber = PYSRecordDto.getYear() * 2;

        return semesterNumber;
    }

}
