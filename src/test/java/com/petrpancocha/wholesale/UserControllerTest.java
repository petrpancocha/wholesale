package com.petrpancocha.wholesale;

import com.petrpancocha.wholesale.controller.UserController;
import com.petrpancocha.wholesale.model.User;
import com.petrpancocha.wholesale.repository.UserMyBatisRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserMyBatisRepository userRepository;

    @Test
    public void testGetUserById() throws Exception {
        long userId = 1l;
        String loginName = "pancochap";
        String firstName = "Petr";
        String lastName = "Pancocha";

        User mockUser = new User();
        mockUser.setId(userId);
        mockUser.setLoginName(loginName);
        mockUser.setFirstName(firstName);
        mockUser.setLastName(lastName);

        when(userRepository.findById(userId)).thenReturn(mockUser);

        this.mockMvc
                .perform(get(String.format("/users/%d", userId)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.loginName").value(loginName))
                .andExpect(jsonPath("$.firstName").value(firstName))
                .andExpect(jsonPath("$.lastName").value(lastName));
    }

    // TODO: add more tests
}