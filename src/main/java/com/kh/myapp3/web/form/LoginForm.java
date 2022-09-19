package com.kh.myapp3.web.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginForm {
  @NotBlank
  @Email(regexp = ".+@.+\\..+")
  private String email;
  @NotBlank
  @Size(min = 0, max=10)
  private String pw;
}
