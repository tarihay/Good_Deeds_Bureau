package ru.nsu.gorin.shift.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.nsu.gorin.shift.repository.model.UserEntity;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public UserEntity toUser(){
        UserEntity user = new UserEntity();
        user.setId(id);
        user.setNickname(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        return user;
    }

    public static UserDto fromUser(UserEntity user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getNickname());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());

        return userDto;
    }
}