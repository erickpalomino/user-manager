package com.epg.user.manager.models.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterRequestDto {
    private String userName;
    private String userPassword;
    private String userEmail;

}
