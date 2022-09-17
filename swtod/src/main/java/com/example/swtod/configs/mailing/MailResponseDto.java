package com.example.swtod.configs.mailing;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MailResponseDto {
    private String subject;
    private String message;
    private String status;
}
