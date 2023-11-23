package com.gfour.ccoms.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private Integer userId;
    private String userName;
    private String password;
    private String designation;
}
