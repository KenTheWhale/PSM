package com.team5.psm.models.request_models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProfileRequest {
	private String name;
	private String phone;
	private LocalDate dob;
	private String address;
}
