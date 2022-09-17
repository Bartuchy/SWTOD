package com.example.swtod.user;

import com.example.swtod.configs.exception.UserNotFoundException;
import com.example.swtod.configs.mailing.MailSenderService;
import com.example.swtod.user.dto.CreateUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSenderService mailSenderService;

    public void createUser(CreateUserDto userDto) {
        String password = generatePassword();
        log.info(password);
        mailSenderService.sendEmail(userDto.getUsername(), password);

        String encodedPassword = passwordEncoder.encode(password);
        User user = CreateUserDto.mapToUser(userDto, encodedPassword);

        userRepository.save(user);
    }

    public User login(String username) {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    private String generatePassword() {
        return new Random()
                .ints(10, 33, 122)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
