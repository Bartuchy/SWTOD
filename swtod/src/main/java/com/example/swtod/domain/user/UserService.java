package com.example.swtod.domain.user;

import com.example.swtod.configs.exception.PasswordsNotEqualException;
import com.example.swtod.configs.exception.UserNotFoundException;
import com.example.swtod.configs.mailing.MailSenderService;
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
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSenderService mailSenderService;

    public User login(String username) {
        return userRepository
                .findUserByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundException(String.format("User with username '%s' not found", username)));
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
                        user.isAdmin()))
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
                user.isAdmin());
    }

    @Transactional
    public void createUser(CreateUserDto userDto) {
        String encodedPassword = createPasswordAndSendEmail(userDto.getUsername());
        User user = CreateUserDto.mapToUser(userDto, encodedPassword);
        userRepository.save(user);
    }

    @Transactional
    public void updateUserDataByUser(Long id, UpdateUserDto userDto) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id '%s' not found", id)));

        UpdateUserDto.mapToUser(user, userDto);
        userRepository.save(user);
    }

    @Transactional
    public void updateUserDataByAdmin(Long id, AdminUpdateUserDto userDto) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id '%s' not found", id)));

        AdminUpdateUserDto.mapToUser(user, userDto);
        userRepository.save(user);
    }

    @Transactional
    public void resetPassword(String username) {
        String encodedPassword = createPasswordAndSendEmail(username);
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

    private String createPasswordAndSendEmail(String username) {
        String password = generatePassword();
        log.info(password);
        mailSenderService.sendEmail(username, password);

        return passwordEncoder.encode(password);
    }

    private String generatePassword() {
        return new Random()
                .ints(10, 33, 123)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
