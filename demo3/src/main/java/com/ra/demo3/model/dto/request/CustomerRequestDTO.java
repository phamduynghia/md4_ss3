package com.ra.demo3.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerRequestDTO {
    @NotNull
    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[a-z]+\\.[a-z]{2,6}$",message = "sai dinh dang email")
    private String email;
    @NotBlank
    private String fullName;
    @Length(min = 5, message = "nhap cho tu te")
    private String password;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    @Past
    private LocalDate birthday;
}

