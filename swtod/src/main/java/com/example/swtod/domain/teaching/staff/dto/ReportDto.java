package com.example.swtod.domain.teaching.staff.dto;

import com.example.swtod.domain.teaching.staff.PlanYearSubjectUser;
import com.example.swtod.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static com.example.swtod.domain.pensum.PensumManager.*;

@Getter
@AllArgsConstructor
public class ReportDto {
    List<ReportRecord> reportRecords;

    private String userNameSurname;
    private String  positionName;
    private Integer pensum;

    private Integer totalNSHours;
    private Integer totalSTHours;
    private Integer totalHours;

    public ReportDto(List<ReportRecord> reportRecords, List<PlanYearSubjectUser> planYearSubjectUsers, User user) {
        this.reportRecords = reportRecords;
        this.userNameSurname = user.getName() + " " + user.getSurname();
        this.positionName = user.getPosition().getName();
        this.pensum = user.getPensum();

        this.totalNSHours = calculateNSHoursPerYear(planYearSubjectUsers);
        this.totalSTHours = calculateSTHoursPerYear(planYearSubjectUsers);
        this.totalHours = countActualPensumValue(planYearSubjectUsers);
    }
}
