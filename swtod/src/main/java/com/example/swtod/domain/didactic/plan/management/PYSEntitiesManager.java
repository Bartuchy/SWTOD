package com.example.swtod.domain.didactic.plan.management;

import com.example.swtod.domain.classes.type.ClassesType;
import com.example.swtod.domain.classes.type.ClassesTypeRepository;
import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import com.example.swtod.domain.didactic.plan.dto.PlanYearSubjectDto;
import com.example.swtod.domain.didactic.plan.management.transfer.PYSRelatedEntitiesTransporter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class PYSEntitiesManager {

    private final ClassesTypeRepository classesTypeRepository;

    public void addLectureEntityIfPossible(List<PlanYearSubject> planYearSubjects,
                                           PlanYearSubjectDto planYearSubjectDto,
                                           PYSRelatedEntitiesTransporter transporter) {
        if (planYearSubjectDto.getLectureHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName("W").orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            planYearSubjectDto.getGroupsPerLecture(),
                            planYearSubjectDto.getWeeksPerSemester(),
                            planYearSubjectDto.getLectureHoursNumberPerWeek(),
                            planYearSubjectDto.getNumberOfStudents(),
                            planYearSubjectDto.getSemesterType().charAt(0),
                            planYearSubjectDto.getYear(),
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
                                            PlanYearSubjectDto planYearSubjectDto,
                                            PYSRelatedEntitiesTransporter transporter) {
        if (planYearSubjectDto.getExerciseHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName("C").orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            planYearSubjectDto.getGroupsPerExercise(),
                            planYearSubjectDto.getWeeksPerSemester(),
                            planYearSubjectDto.getExerciseHoursNumberPerWeek(),
                            planYearSubjectDto.getNumberOfStudents(),
                            planYearSubjectDto.getSemesterType().charAt(0),
                            planYearSubjectDto.getYear(),
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
                                              PlanYearSubjectDto planYearSubjectDto,
                                              PYSRelatedEntitiesTransporter transporter) {
        if (planYearSubjectDto.getLaboratoryHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName("L").orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            planYearSubjectDto.getGroupsPerLaboratory(),
                            planYearSubjectDto.getWeeksPerSemester(),
                            planYearSubjectDto.getLaboratoryHoursNumberPerWeek(),
                            planYearSubjectDto.getNumberOfStudents(),
                            planYearSubjectDto.getSemesterType().charAt(0),
                            planYearSubjectDto.getYear(),
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
                                           PlanYearSubjectDto planYearSubjectDto,
                                           PYSRelatedEntitiesTransporter transporter) {
        if (planYearSubjectDto.getProjectHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName("P").orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            planYearSubjectDto.getGroupsPerProject(),
                            planYearSubjectDto.getWeeksPerSemester(),
                            planYearSubjectDto.getProjectHoursNumberPerWeek(),
                            planYearSubjectDto.getNumberOfStudents(),
                            planYearSubjectDto.getSemesterType().charAt(0),
                            planYearSubjectDto.getYear(),
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
                                            PlanYearSubjectDto planYearSubjectDto,
                                            PYSRelatedEntitiesTransporter transporter) {
        if (planYearSubjectDto.getSeminaryHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName("S").orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            planYearSubjectDto.getGroupsPerSeminary(),
                            planYearSubjectDto.getWeeksPerSemester(),
                            planYearSubjectDto.getSeminaryHoursNumberPerWeek(),
                            planYearSubjectDto.getNumberOfStudents(),
                            planYearSubjectDto.getSemesterType().charAt(0),
                            planYearSubjectDto.getYear(),
                            classesType,
                            transporter.getStudiesType(),
                            transporter.getPlanYear(),
                            transporter.getSubject(),
                            transporter.getFaculty()
                    )
            );
        }
    }

    public void addLectureDtoIfPossible(List<PlanYearSubjectDto> planYearSubjectDtos,
                                        PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals("W")) {
            planYearSubjectDtos.add(
                    new PlanYearSubjectDto(
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

    public void addExerciseDtoIfPossible(List<PlanYearSubjectDto> planYearSubjectDtos,
                                         PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals("C")) {
            planYearSubjectDtos.add(
                    new PlanYearSubjectDto(
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

    public void addLaboratoryDtoIfPossible(List<PlanYearSubjectDto> planYearSubjectDtos,
                                           PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals("L")) {
            planYearSubjectDtos.add(
                    new PlanYearSubjectDto(
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

    public void addProjectDtoIfPossible(List<PlanYearSubjectDto> planYearSubjectDtos,
                                        PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals("P")) {
            planYearSubjectDtos.add(
                    new PlanYearSubjectDto(
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

    public void addSeminaryDtoIfPossible(List<PlanYearSubjectDto> planYearSubjectDtos,
                                         PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals("S")) {
            planYearSubjectDtos.add(
                    new PlanYearSubjectDto(
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

    public void setDtosLectureFields(PlanYearSubjectDto planYearSubjectDto,
                                     PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals("W")) {
            planYearSubjectDto.setLectureHoursNumberPerWeek(planYearSubject.getHoursPerWeek());
            planYearSubjectDto.setGroupsPerLecture(planYearSubject.getGroupsNumber());
            planYearSubjectDto.setLectureHoursNumber(planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber());
        }
    }

    public void setDtosExerciseFields(PlanYearSubjectDto planYearSubjectDto,
                                      PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals("C")) {
            planYearSubjectDto.setExerciseHoursNumberPerWeek(planYearSubject.getHoursPerWeek());
            planYearSubjectDto.setGroupsPerExercise(planYearSubject.getGroupsNumber());
            planYearSubjectDto.setExerciseHoursNumber(planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber());
        }
    }

    public void setDtosLaboratoryFields(PlanYearSubjectDto planYearSubjectDto,
                                        PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals("L")) {

            planYearSubjectDto.setLaboratoryHoursNumberPerWeek(planYearSubject.getHoursPerWeek());
            planYearSubjectDto.setGroupsPerLaboratory(planYearSubject.getGroupsNumber());
            planYearSubjectDto.setLaboratoryHoursNumber(planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber());
        }
    }

    public void setDtosProjectFields(PlanYearSubjectDto planYearSubjectDto,
                                     PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals("P")) {
            planYearSubjectDto.setProjectHoursNumberPerWeek(planYearSubject.getHoursPerWeek());
            planYearSubjectDto.setGroupsPerProject(planYearSubject.getGroupsNumber());
            planYearSubjectDto.setProjectHoursNumber(planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber());
        }
    }

    public void setDtosSeminaryFields(PlanYearSubjectDto planYearSubjectDto,
                                      PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals("S")) {
            planYearSubjectDto.setLectureHoursNumberPerWeek(planYearSubject.getHoursPerWeek());
            planYearSubjectDto.setGroupsPerLecture(planYearSubject.getGroupsNumber());
            planYearSubjectDto.setLectureHoursNumber(planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber());
        }
    }
}
