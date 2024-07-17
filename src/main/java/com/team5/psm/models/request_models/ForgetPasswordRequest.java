package com.team5.psm.models.request_models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForgetPasswordRequest {
    private String email;
    LocalDate createdAt;
    private String newPassword;
    private String newPasswordConfirm;
}
