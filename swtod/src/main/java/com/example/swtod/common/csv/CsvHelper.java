package com.example.swtod.common.csv;

import com.example.swtod.domain.teaching.staff.dto.ReportDto;
import com.example.swtod.domain.teaching.staff.dto.ReportRecord;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class CsvHelper {
    public ByteArrayInputStream tutorialsToCSV(ReportDto reportDto) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        List<ReportRecord> reportRecords = reportDto.getReportRecords();

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            List<String> columnHeaders = Arrays.asList("Nazwa przedmiotu", "Wydział", "Kierunek", "Semestr",
                    "liczba tyg.", "W godz.tyg.", "W nr.gr.", "C godz.tyg.", "C nr.gr.", "L godz.tyg.", "L nr.gr.",
                    "P godz.tyg.", "P nr.gr.", "S godz.tyg.", "S nr.gr.", "godz. w roku");
            csvPrinter.printRecord(columnHeaders);


            for (ReportRecord reportRecord : reportRecords) {
                List<String> data = Arrays.asList(
                        reportRecord.getSubjectName(),
                        reportRecord.getFacultyName(),
                        reportRecord.getFieldOfStudiesName(),
                        String.valueOf(reportRecord.getSemesterNumber()),
                        String.valueOf(reportRecord.getNoWeeks()),
                        String.valueOf(reportRecord.getLectureHoursPerWeek()),
                        String.valueOf(reportRecord.getGroupsPerLecture()),
                        String.valueOf(reportRecord.getExerciseHoursPerWeek()),
                        String.valueOf(reportRecord.getGroupsPerExercise()),
                        String.valueOf(reportRecord.getLaboratoryHoursPerWeek()),
                        String.valueOf(reportRecord.getGroupsPerLaboratory()),
                        String.valueOf(reportRecord.getProjectHoursPerWeek()),
                        String.valueOf(reportRecord.getGroupsPerProject()),
                        String.valueOf(reportRecord.getSeminaryHoursPerWeek()),
                        String.valueOf(reportRecord.getGroupsPerSeminary()),
                        String.valueOf(reportRecord.getTotalHoursInYear())
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
