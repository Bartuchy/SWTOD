package com.example.swtod.domain.pensum.dto;

import com.example.swtod.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@AllArgsConstructor
public class PensumRecordDto {
    private String name;
    private String surname;
    private String title;
    private String position;
    private Integer actualPensum;
    private Integer expectedPensum;
    private Boolean isCorrect;
    private Integer overtimeHoursNumber;
    private BigDecimal percentOfOvertimeHours;

    public PensumRecordDto(User user, int actualPensum) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.title = user.getTitle();
        this.position = user.getPosition().getName();
        this.actualPensum = actualPensum;
        this.expectedPensum = user.getPensum();
        this.overtimeHoursNumber = calculateOvertimeHoursNumber(user.getPensum(), actualPensum);
        this.percentOfOvertimeHours = calculatePercentOfOvertimeHours(user.getPensum(), actualPensum);
        this.isCorrect = checkIfPensumIsCorrect(user.getPensum(), actualPensum);
    }

    private Integer calculateOvertimeHoursNumber(int expectedPensum, int actualPensum) {
        if (expectedPensum > actualPensum) {
            return 0;
        }
        return actualPensum - expectedPensum;
    }

    private BigDecimal calculatePercentOfOvertimeHours(int expectedPensum, int actualPensum) {
        if (expectedPensum > actualPensum) {
            return BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP);
        }

        double percentValue = actualPensum / (expectedPensum * 1.0);
        return BigDecimal.valueOf(percentValue).setScale(2, RoundingMode.HALF_UP);
    }

    private Boolean checkIfPensumIsCorrect(int expectedPensum, int actualPensum) {
        int overtimeHoursNumber = calculateOvertimeHoursNumber(expectedPensum, actualPensum);
        int maxOvertime = expectedPensum * 3;

        return overtimeHoursNumber <= maxOvertime && expectedPensum <= actualPensum;
    }
}
