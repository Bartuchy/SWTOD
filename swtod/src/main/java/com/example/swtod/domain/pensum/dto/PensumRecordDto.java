package com.example.swtod.domain.pensum.dto;

import com.example.swtod.domain.pensum.PensumManager;
import com.example.swtod.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class PensumRecordDto {
    private Long userId;
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
        this.userId = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.title = user.getTitle();
        this.position = user.getPosition().getName();
        this.actualPensum = actualPensum;
        this.expectedPensum = user.getPensum();
        this.overtimeHoursNumber = PensumManager.calculateOvertimeHoursNumber(user.getPensum(), actualPensum);
        this.percentOfOvertimeHours = PensumManager.calculatePercentOfOvertimeHours(user.getPensum(), actualPensum);
        this.isCorrect = PensumManager.checkIfPensumIsCorrect(user.getPensum(), actualPensum);
    }
}
