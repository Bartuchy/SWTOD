package com.example.swtod.domain.pensum;

import com.example.swtod.common.exception.UserNotFoundException;
import com.example.swtod.domain.pensum.dto.PensumRecordDto;
import com.example.swtod.domain.teaching.staff.PlanYearSubjectUser;
import com.example.swtod.domain.user.User;
import com.example.swtod.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.swtod.domain.common.status.StatusConst.ACCEPTED;

@Service
@RequiredArgsConstructor
public class PensumService {
    private final UserRepository userRepository;

    public List<PensumRecordDto> getPensumForAll() {
        List<User> users = userRepository.findAll();
        List<PensumRecordDto> pensumRecordDtos = new ArrayList<>();

        for (User user : users) {
            PensumRecordDto pensumRecordDto = createPensumRecordDto(user);
            pensumRecordDtos.add(pensumRecordDto);
        }

        return pensumRecordDtos;
    }

    public PensumRecordDto getPensumByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        return createPensumRecordDto(user);
    }

    public void updateUserPensum(Long userId, Integer pensum) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setPensum(pensum);
        userRepository.save(user);
    }

    private PensumRecordDto createPensumRecordDto(User user) {
        List<PlanYearSubjectUser> planYearSubjectUsers = user.getPlanYearSubjectUsers();
        int actualPensum = countPensumValue(planYearSubjectUsers);
        return new PensumRecordDto(user, actualPensum);
    }

    private int countPensumValue(List<PlanYearSubjectUser> planYearSubjectUsers) {
        int actualPensum = 0;

        for (PlanYearSubjectUser pysu : planYearSubjectUsers) {
            if (pysu.getStatus().getName().equals(ACCEPTED)) {
                actualPensum += pysu.getGroupsNumber() *
                        pysu.getPlanYearSubject().getWeeksNumber() *
                        pysu.getPlanYearSubject().getHoursPerWeek();
            }
        }

        return actualPensum;
    }
}
