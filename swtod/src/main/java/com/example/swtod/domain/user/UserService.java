package com.example.swtod.domain.user;

import com.example.swtod.common.exception.PasswordsNotEqualException;
import com.example.swtod.common.exception.UserNotFoundException;
import com.example.swtod.common.exception.UsernameTakenException;
import com.example.swtod.common.mailing.MailSenderService;
import com.example.swtod.domain.teaching.staff.PYSURepository;
import com.example.swtod.domain.teaching.staff.PlanYearSubjectUser;
import com.example.swtod.domain.user.admin.dto.AdminUpdateUserDto;
import com.example.swtod.domain.user.admin.dto.CreateUserDto;
import com.example.swtod.domain.user.dto.ChangePasswordDto;
import com.example.swtod.domain.user.dto.UpdateUserDto;
import com.example.swtod.domain.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.example.swtod.domain.common.status.StatusConst.ACCEPTED;
import static com.example.swtod.domain.common.status.StatusConst.REJECTED;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PYSURepository pysuRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSenderService mailSenderService;

    public User getLoggedInUser(String username) {
        return userRepository
                .findUserByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundException(String.format(
                                "User with username '%s' not found",
                                username)));
    }

    public List<UserDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getName(),
                        user.getSurname(),
                        user.getUsername(),
                        user.getTitle(),
                        user.getPosition().getName(),
                        user.getDob().toString(),
                        user.isAdmin(),
                        user.isActive()))
                .toList();
    }



    public UserDto getUserByUsername(String username) {
        User user = userRepository
                .findUserByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundException(String.format("User with username '%s' not found", username)));

        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getTitle(),
                user.getPosition().getName(),
                user.getDob().toString(),
                user.isAdmin(),
                user.isActive());
    }

    public UserDto getUserById(Long userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(String.format("User with id %d not found", userId)));

        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getTitle(),
                user.getPosition().getName(),
                user.getDob().toString(),
                user.isAdmin(),
                user.isActive());
    }

    @Transactional
    public void createUser(CreateUserDto userDto) {
        Optional<User> optionalUser = userRepository
                .findUserByUsername(userDto.getUsername());

        if (optionalUser.isPresent())
            throw new UsernameTakenException(String.format(
                    "User with username '%s' already exists",
                    userDto.getUsername()));

        String password = generatePassword();
        mailSenderService.sendEmail(userDto.getUsername(), password);

        String encodedPassword = passwordEncoder.encode(password);
        User user = CreateUserDto.mapToUser(userDto, encodedPassword);

        userRepository.save(user);
    }

    @Transactional
    public void updateUserDataByUser(Long id, UpdateUserDto userDto) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format(
                        "User with id '%s' not found",
                        id)));

        UpdateUserDto.mapToUser(user, userDto);
        userRepository.save(user);
    }

    @Transactional
    public void updateUserDataByAdmin(Long id, AdminUpdateUserDto userDto) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format(
                        "User with id '%s' not found",
                        id)));

        AdminUpdateUserDto.mapToUser(user, userDto);
        userRepository.save(user);
    }

    @Transactional
    public void resetPassword(String username) {
        userRepository
                .findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(String.format(
                                "User username %s not found",
                                username)));

        String password = generatePassword();
        mailSenderService.sendEmail(username, password);

        String encodedPassword = passwordEncoder.encode(password);
        userRepository.changePassword(encodedPassword, username);
    }

    @Transactional
    public void updatePassword(ChangePasswordDto passwordDto) {
        String newPassword = passwordDto.getNewPassword();
        String repeatedNewPassword = passwordDto.getRepeatedNewPassword();

        if (!newPassword.equals(repeatedNewPassword))
            throw new PasswordsNotEqualException("Given passwords are not equal");

        String encodedPassword = passwordEncoder.encode(newPassword);
        userRepository.changePassword(encodedPassword, passwordDto.getUsername());
    }

    @Transactional
    public void deactivateAccount(Long id) {
        userRepository.modifyAccountActivationFlag(id, false);
    }

    @Transactional
    public void activateAccount(Long id) {
        userRepository.modifyAccountActivationFlag(id, true);
    }

    public void acceptGroupsAssignmentForSubject(Long userId, Long subjectId) {
        List<PlanYearSubjectUser> planYearSubjectUsers =
                pysuRepository.findPlanYearSubjectUsersByUserIdAndSubjectId(userId, subjectId);

        planYearSubjectUsers.forEach(pysu -> pysu.getStatus().setName(ACCEPTED));
        pysuRepository.saveAll(planYearSubjectUsers);
    }

    public void rejectGroupsAssignmentForSubject(Long userId, Long subjectId) {
        List<PlanYearSubjectUser> planYearSubjectUsers =
                pysuRepository.findPlanYearSubjectUsersByUserIdAndSubjectId(userId, subjectId);

        planYearSubjectUsers.forEach(pysu -> pysu.getStatus().setName(REJECTED));
        pysuRepository.saveAll(planYearSubjectUsers);
    }

    private String generatePassword() {
        return new Random()
                .ints(12, 33, 123)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
