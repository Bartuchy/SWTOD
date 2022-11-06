package com.example.swtod.domain.didactic.plan.management;

import com.example.swtod.domain.classes.type.ClassesType;
import com.example.swtod.domain.classes.type.ClassesTypeRepository;
import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import com.example.swtod.domain.didactic.plan.dto.PlanYearSubjectRecordDto;
import com.example.swtod.domain.didactic.plan.management.transfer.PYSRelatedEntitiesTransporter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class PYSEntitiesManager {

    private final ClassesTypeRepository classesTypeRepository;

    public void addLectureEntityIfPossible(List<PlanYearSubject> planYearSubjects,
                                           PlanYearSubjectRecordDto planYearSubjectRecordDto,
                                           PYSRelatedEntitiesTransporter transporter) {
        if (planYearSubjectRecordDto.getLectureHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName("W").orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            planYearSubjectRecordDto.getGroupsPerLecture(),
                            planYearSubjectRecordDto.getWeeksPerSemester(),
                            planYearSubjectRecordDto.getLectureHoursNumberPerWeek(),
                            planYearSubjectRecordDto.getNumberOfStudents(),
                            planYearSubjectRecordDto.getSemesterType().charAt(0),
                            planYearSubjectRecordDto.getYear(),
                            classesType,
                            transporter.getStudiesType(),
                            transporter.getPlanYear(),
                            transporter.getSubject(),
                            transporter.getFaculty()
                    )
            );
        }
    }

    public void addExerciseEntityIfPossible(List<PlanYearSubject> planYearSubjects,
                                            PlanYearSubjectRecordDto planYearSubjectRecordDto,
                                            PYSRelatedEntitiesTransporter transporter) {
        if (planYearSubjectRecordDto.getExerciseHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName("C").orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            planYearSubjectRecordDto.getGroupsPerExercise(),
                            planYearSubjectRecordDto.getWeeksPerSemester(),
                            planYearSubjectRecordDto.getExerciseHoursNumberPerWeek(),
                            planYearSubjectRecordDto.getNumberOfStudents(),
                            planYearSubjectRecordDto.getSemesterType().charAt(0),
                            planYearSubjectRecordDto.getYear(),
                            classesType,
                            transporter.getStudiesType(),
                            transporter.getPlanYear(),
                            transporter.getSubject(),
                            transporter.getFaculty()
                    )
            );
        }
    }

    public void addLaboratoryEntityIfPossible(List<PlanYearSubject> planYearSubjects,
                                              PlanYearSubjectRecordDto planYearSubjectRecordDto,
                                              PYSRelatedEntitiesTransporter transporter) {
        if (planYearSubjectRecordDto.getLaboratoryHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName("L").orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            planYearSubjectRecordDto.getGroupsPerLaboratory(),
                            planYearSubjectRecordDto.getWeeksPerSemester(),
                            planYearSubjectRecordDto.getLaboratoryHoursNumberPerWeek(),
                            planYearSubjectRecordDto.getNumberOfStudents(),
                            planYearSubjectRecordDto.getSemesterType().charAt(0),
                            planYearSubjectRecordDto.getYear(),
                            classesType,
                            transporter.getStudiesType(),
                            transporter.getPlanYear(),
                            transporter.getSubject(),
                            transporter.getFaculty()
                    )
            );
        }
    }

    public void addProjectEntityIfPossible(List<PlanYearSubject> planYearSubjects,
                                           PlanYearSubjectRecordDto planYearSubjectRecordDto,
                                           PYSRelatedEntitiesTransporter transporter) {
        if (planYearSubjectRecordDto.getProjectHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName("P").orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            planYearSubjectRecordDto.getGroupsPerProject(),
                            planYearSubjectRecordDto.getWeeksPerSemester(),
                            planYearSubjectRecordDto.getProjectHoursNumberPerWeek(),
                            planYearSubjectRecordDto.getNumberOfStudents(),
                            planYearSubjectRecordDto.getSemesterType().charAt(0),
                            planYearSubjectRecordDto.getYear(),
                            classesType,
                            transporter.getStudiesType(),
                            transporter.getPlanYear(),
                            transporter.getSubject(),
                            transporter.getFaculty()
                    )
            );
        }
    }

    public void addSeminaryEntityIfPossible(List<PlanYearSubject> planYearSubjects,
                                            PlanYearSubjectRecordDto planYearSubjectRecordDto,
                                            PYSRelatedEntitiesTransporter transporter) {
        if (planYearSubjectRecordDto.getSeminaryHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName("S").orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            planYearSubjectRecordDto.getGroupsPerSeminary(),
                            planYearSubjectRecordDto.getWeeksPerSemester(),
                            planYearSubjectRecordDto.getSeminaryHoursNumberPerWeek(),
                            planYearSubjectRecordDto.getNumberOfStudents(),
                            planYearSubjectRecordDto.getSemesterType().charAt(0),
                            planYearSubjectRecordDto.getYear(),
                            classesType,
                            transporter.getStudiesType(),
                            transporter.getPlanYear(),
                            transporter.getSubject(),
                            transporter.getFaculty()
                    )
            );
        }
    }

    public void addLectureDtoIfPossible(List<PlanYearSubjectRecordDto> planYearSubjectRecordDtos,
                                        PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals("W")) {
            planYearSubjectRecordDtos.add(
                    new PlanYearSubjectRecordDto(
                            planYearSubject.getSubject().getId(),
                            planYearSubject.getFaculty().getName(),
                            planYearSubject.getPlanYear().getYear(),
                            planYearSubject.getSubject().getFieldOfStudies().getName(),
                            planYearSubject.getStudiesType().getName(),
                            planYearSubject.getSubject().getName(),
                            planYearSubject.getWeeksNumber(),
                            planYearSubject.getHoursPerWeek(),
                            0.0,
                            0.0,
                            0.0,
                            0.0,
                            planYearSubject.getStudentsNumber(),
                            planYearSubject.getGroupsNumber(),
                            planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber(),
                            0,
                            0.0,
                            0,
                            0.0,
                            0,
                            0.0,
                            0,
                            0.0,
                            String.valueOf(planYearSubject.getSemester()),
                            planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber()
                    )
            );
        }
    }

    public void addExerciseDtoIfPossible(List<PlanYearSubjectRecordDto> planYearSubjectRecordDtos,
                                         PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals("C")) {
            planYearSubjectRecordDtos.add(
                    new PlanYearSubjectRecordDto(
                            planYearSubject.getSubject().getId(),
                            planYearSubject.getFaculty().getName(),
                            planYearSubject.getPlanYear().getYear(),
                            planYearSubject.getSubject().getFieldOfStudies().getName(),
                            planYearSubject.getStudiesType().getName(),
                            planYearSubject.getSubject().getName(),
                            planYearSubject.getWeeksNumber(),
                            0.0,
                            planYearSubject.getHoursPerWeek(),
                            0.0,
                            0.0,
                            0.0,
                            planYearSubject.getStudentsNumber(),
                            0,
                            0.0,
                            planYearSubject.getGroupsNumber(),
                            planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber(),
                            0,
                            0.0,
                            0,
                            0.0,
                            0,
                            0.0,
                            String.valueOf(planYearSubject.getSemester()),
                            planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber()
                    )
            );
        }
    }

    public void addLaboratoryDtoIfPossible(List<PlanYearSubjectRecordDto> planYearSubjectRecordDtos,
                                           PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals("L")) {
            planYearSubjectRecordDtos.add(
                    new PlanYearSubjectRecordDto(
                            planYearSubject.getSubject().getId(),
                            planYearSubject.getFaculty().getName(),
                            planYearSubject.getPlanYear().getYear(),
                            planYearSubject.getSubject().getFieldOfStudies().getName(),
                            planYearSubject.getStudiesType().getName(),
                            planYearSubject.getSubject().getName(),
                            planYearSubject.getWeeksNumber(),
                            0.0,
                            0.0,
                            planYearSubject.getHoursPerWeek(),
                            0.0,
                            0.0,
                            planYearSubject.getStudentsNumber(),
                            0,
                            0.0,
                            0,
                            0.0,
                            planYearSubject.getGroupsNumber(),
                            planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber(),
                            0,
                            0.0,
                            0,
                            0.0,
                            String.valueOf(planYearSubject.getSemester()),
                            planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber()
                    )
            );
        }
    }

    public void addProjectDtoIfPossible(List<PlanYearSubjectRecordDto> planYearSubjectRecordDtos,
                                        PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals("P")) {
            planYearSubjectRecordDtos.add(
                    new PlanYearSubjectRecordDto(
                            planYearSubject.getSubject().getId(),
                            planYearSubject.getFaculty().getName(),
                            planYearSubject.getPlanYear().getYear(),
                            planYearSubject.getSubject().getFieldOfStudies().getName(),
                            planYearSubject.getStudiesType().getName(),
                            planYearSubject.getSubject().getName(),
                            planYearSubject.getWeeksNumber(),
                            0.0,
                            0.0,
                            0.0,
                            planYearSubject.getHoursPerWeek(),
                            0.0,
                            planYearSubject.getStudentsNumber(),
                            0,
                            0.0,
                            0,
                            0.0,
                            0,
                            0.0,
                            planYearSubject.getGroupsNumber(),
                            planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber(),
                            0,
                            0.0,
                            String.valueOf(planYearSubject.getSemester()),
                            planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber()
                    )
            );
        }
    }

    public void addSeminaryDtoIfPossible(List<PlanYearSubjectRecordDto> planYearSubjectRecordDtos,
                                         PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals("S")) {
            planYearSubjectRecordDtos.add(
                    new PlanYearSubjectRecordDto(
                            planYearSubject.getSubject().getId(),
                            planYearSubject.getFaculty().getName(),
                            planYearSubject.getPlanYear().getYear(),
                            planYearSubject.getSubject().getFieldOfStudies().getName(),
                            planYearSubject.getStudiesType().getName(),
                            planYearSubject.getSubject().getName(),
                            planYearSubject.getWeeksNumber(),
                            0.0,
                            0.0,
                            0.0,
                            0.0,
                            planYearSubject.getHoursPerWeek(),
                            planYearSubject.getStudentsNumber(),
                            0,
                            0.0,
                            0,
                            0.0,
                            0,
                            0.0,
                            0,
                            0.0,
                            planYearSubject.getGroupsNumber(),
                            planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber(),
                            String.valueOf(planYearSubject.getSemester()),
                            planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber()
                    )
            );
        }
    }

    public void setDtosLectureFields(PlanYearSubjectRecordDto planYearSubjectRecordDto,
                                     PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals("W")) {
            planYearSubjectRecordDto.setLectureHoursNumberPerWeek(planYearSubject.getHoursPerWeek());
            planYearSubjectRecordDto.setGroupsPerLecture(planYearSubject.getGroupsNumber());
            planYearSubjectRecordDto.setLectureHoursNumber(planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber());
        }
    }

    public void setDtosExerciseFields(PlanYearSubjectRecordDto planYearSubjectRecordDto,
                                      PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals("C")) {
            planYearSubjectRecordDto.setExerciseHoursNumberPerWeek(planYearSubject.getHoursPerWeek());
            planYearSubjectRecordDto.setGroupsPerExercise(planYearSubject.getGroupsNumber());
            planYearSubjectRecordDto.setExerciseHoursNumber(planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber());
        }
    }

    public void setDtosLaboratoryFields(PlanYearSubjectRecordDto planYearSubjectRecordDto,
                                        PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals("L")) {

            planYearSubjectRecordDto.setLaboratoryHoursNumberPerWeek(planYearSubject.getHoursPerWeek());
            planYearSubjectRecordDto.setGroupsPerLaboratory(planYearSubject.getGroupsNumber());
            planYearSubjectRecordDto.setLaboratoryHoursNumber(planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber());
        }
    }

    public void setDtosProjectFields(PlanYearSubjectRecordDto planYearSubjectRecordDto,
                                     PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals("P")) {
            planYearSubjectRecordDto.setProjectHoursNumberPerWeek(planYearSubject.getHoursPerWeek());
            planYearSubjectRecordDto.setGroupsPerProject(planYearSubject.getGroupsNumber());
            planYearSubjectRecordDto.setProjectHoursNumber(planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber());
        }
    }

    public void setDtosSeminaryFields(PlanYearSubjectRecordDto planYearSubjectRecordDto,
                                      PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals("S")) {
            planYearSubjectRecordDto.setLectureHoursNumberPerWeek(planYearSubject.getHoursPerWeek());
            planYearSubjectRecordDto.setGroupsPerLecture(planYearSubject.getGroupsNumber());
            planYearSubjectRecordDto.setLectureHoursNumber(planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber());
        }
    }
}
