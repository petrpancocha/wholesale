package com.petrpancocha.wholesale.dto;

import com.petrpancocha.wholesale.model.User;

public class UserDto {
    private Long id;
    private String loginName;
    private String firstName;
    private String lastName;

    public UserDto(User user) {
        this.id = user.getId();
        this.loginName = user.getLoginName();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

    public Long getId() {
        return this.id;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
}
