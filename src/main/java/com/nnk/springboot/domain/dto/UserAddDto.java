package com.nnk.springboot.domain.dto;

import com.nnk.springboot.annotation.ValidPassword;
import com.nnk.springboot.annotation.ValidUsername;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddDto {

	private Integer id;

	@ValidUsername
	@NotBlank(message = "Username is mandatory")
	private String username;

	@ValidPassword
	@NotBlank(message = "Password is mandatory")
	private String password;

	@NotBlank(message = "FullName is mandatory")
	private String fullname;

	@NotBlank(message = "Role is mandatory")
	private String role = "ROLE_USER";

}
