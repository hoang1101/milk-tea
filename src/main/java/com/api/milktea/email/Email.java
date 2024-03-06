package com.api.milktea.email;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    @NotBlank(message = "Email cannot be black")
    private String toEmail;
    @NotBlank(message = "Subject cannot be black")
    private String subject;
    @NotBlank(message = "Body cannot be black")
    private String body;
//    @NotBlank(message = "Attachment cannot be blank")
    private String attachment;
}
