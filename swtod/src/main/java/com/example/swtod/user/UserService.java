package com.example.swtod.user;

import com.example.swtod.configs.exception.UserNotFoundException;
import com.example.swtod.configs.mailing.MailSenderService;
import com.example.swtod.user.dto.CreateUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .findByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundException(String.format("User with username '%s' not found", username)));
    }

    @Transactional
    public void createUser(CreateUserDto userDto) {
        String encodedPassword  = createPasswordAndSendEmail(userDto.getUsername());
        User user = CreateUserDto.mapToUser(userDto, encodedPassword);

        userRepository.save(user);
    }

    @Transactional
    public void resetPassword(String username) {
        String encodedPassword = createPasswordAndSendEmail(username);
        userRepository.changePassword(encodedPassword, username);
    }

    @Transactional
    public void updatePassword(String password, String username) {
        String encodedPassword = passwordEncoder.encode(password);
        userRepository.changePassword(encodedPassword, username);
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
