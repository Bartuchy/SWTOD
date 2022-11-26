package com.example.swtod.common.csv;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface Mapper<T, U> {
    List<U> mapRecordsToDtos(List<List<String>> records, String facultyName, String academicYear);
    List<T> mapDtosToEntities(List<U> recordDtos);
    List<U> mapEntitiesToDtos(List<T> entity);

    default List<String> mapEmptyValuesToZeros(List<String> values) {
        List<String> mappedValues = new ArrayList<>();
        for (String value : values) {
            addElemToMappingList(value, mappedValues);
        }
        return mappedValues;
    }

    private void addElemToMappingList(String str, List<String> mappedValues) {
        if (Objects.equals(str, "")) {
            mappedValues.add("0");
        } else {
            mappedValues.add(str);
        }
    }
}
