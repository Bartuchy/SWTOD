package com.example.swtod.domain.pensum;

import com.example.swtod.domain.pensum.dto.PensumRecordDto;
import com.example.swtod.domain.teaching.staff.PlanYearSubjectUser;
import com.example.swtod.domain.user.User;
import com.example.swtod.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PensumService {
    private final UserRepository userRepository;

    public List<PensumRecordDto> getPensumForAll() {
        List<PensumRecordDto> pensumRecordDtos = new ArrayList<>();

        List<User> users = userRepository.findAll();
        List<PlanYearSubjectUser> planYearSubjectUsers;
        int actualPensum;


        for (User user : users) {
            actualPensum = 0;
            planYearSubjectUsers = user.getPlanYearSubjectUsers();

            for (PlanYearSubjectUser pysu : planYearSubjectUsers) {
                actualPensum += pysu.getGroupsNumber() * pysu.getPlanYearSubject().getWeeksNumber() * pysu.getPlanYearSubject().getHoursPerWeek();
            }
            pensumRecordDtos.add(new PensumRecordDto(user, actualPensum));

        }
        return pensumRecordDtos;
    }
}
