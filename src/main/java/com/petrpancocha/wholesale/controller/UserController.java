package com.petrpancocha.wholesale.controller;

import com.petrpancocha.wholesale.dto.UserDto;
import com.petrpancocha.wholesale.model.User;
import com.petrpancocha.wholesale.repository.UserMyBatisRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@Api(value = "Users API")
/**
 * Note: UserController does not provide methods for CRUD operations with users. Users are populated and managed
 * using another tools (e.g. direct DB operations)
 */
public class UserController {

    @Autowired
    private UserMyBatisRepository userRepository;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());

    }

    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable long id) {
        User user = userRepository.findById(id);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: id=" + id);
        }

        return new UserDto(user);
    }
}
