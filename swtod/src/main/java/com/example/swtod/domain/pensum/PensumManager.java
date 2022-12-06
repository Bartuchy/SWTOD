package com.example.swtod.domain.pensum;

import com.example.swtod.domain.teaching.staff.PlanYearSubjectUser;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.example.swtod.domain.common.status.StatusConst.ACCEPTED;

public class PensumManager {

    public static BigDecimal calculatePercentOfOvertimeHours(int expectedPensum, int actualPensum) {
        if (expectedPensum > actualPensum) {
            return BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP);
        }

        double percentValue = actualPensum / (expectedPensum * 1.0);
        return BigDecimal.valueOf(percentValue).setScale(2, RoundingMode.HALF_UP);
    }

    public static Boolean checkIfPensumIsCorrect(int expectedPensum, int actualPensum) {
        int overtimeHoursNumber = calculateOvertimeHoursNumber(expectedPensum, actualPensum);
        int maxOvertime = expectedPensum * 3;

        return overtimeHoursNumber <= maxOvertime && expectedPensum <= actualPensum;
    }

    public static Integer calculateOvertimeHoursNumber(int expectedPensum, int actualPensum) {
        if (expectedPensum > actualPensum) {
            return 0;
        }
        return actualPensum - expectedPensum;
    }

    public static int countActualPensumValue(List<PlanYearSubjectUser> planYearSubjectUsers) {
        int actualPensum = 0;

        for (PlanYearSubjectUser pysu : planYearSubjectUsers) {
            actualPensum = addHoursIfMeetPredicate(pysu, actualPensum, isPysuStatusAccepted(pysu));
        }

        return actualPensum;
    }

    public static int calculateNSHoursPerYear(List<PlanYearSubjectUser> planYearSubjectUsers) {
        int hoursCount = 0;
        boolean predicate;

        for (PlanYearSubjectUser pysu : planYearSubjectUsers) {
            predicate = isPysuStatusAccepted(pysu) && isPysuStudiesTypeNS(pysu);
            hoursCount = addHoursIfMeetPredicate(pysu, hoursCount, predicate);
        }

        return hoursCount;
    }

    public static int calculateSTHoursPerYear(List<PlanYearSubjectUser> planYearSubjectUsers) {
        int hoursCount = 0;
        boolean predicate;

        for (PlanYearSubjectUser pysu : planYearSubjectUsers) {
            predicate = isPysuStatusAccepted(pysu) && isPysuStudiesTypeST(pysu);
            hoursCount = addHoursIfMeetPredicate(pysu, hoursCount, predicate);
        }

        return hoursCount;

    }

    private static int addHoursIfMeetPredicate(PlanYearSubjectUser pysu, int actualPensum, boolean predicate) {
        if (predicate) {
            actualPensum += pysu.getGroupsNumber() *
                    pysu.getPlanYearSubject().getWeeksNumber() *
                    pysu.getPlanYearSubject().getHoursPerWeek();
        }
        return actualPensum;
    }

    private static boolean isPysuStatusAccepted(PlanYearSubjectUser pysu) {
        return pysu.getStatus().getName().equals(ACCEPTED);
    }

    private static boolean isPysuStudiesTypeNS(PlanYearSubjectUser pysu) {
        return pysu.getPlanYearSubject().getStudiesType().getName().endsWith("NS");
    }

    private static boolean isPysuStudiesTypeST(PlanYearSubjectUser pysu) {
        return pysu.getPlanYearSubject().getStudiesType().getName().endsWith("ST");
    }
}
