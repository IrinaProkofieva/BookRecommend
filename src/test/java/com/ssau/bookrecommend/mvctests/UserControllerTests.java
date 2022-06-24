package com.ssau.bookrecommend.mvctests;

import com.ssau.bookrecommend.controller.UserController;
import com.ssau.bookrecommend.model.User;
import com.ssau.bookrecommend.service.UserService;
import com.ssau.bookrecommend.service.impl.MyUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@MockBean(MyUserDetailsService.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    private final String login = "Irina";
    private final String password = "Irina";

    @Test
    public void givenUser_whenSignUp_thenStatus201() throws Exception {
        User irina = new User(login, password);
        given(userService.signUp(login, password)).willReturn(irina);

        mvc.perform(post("/v1/users/sign_up")
                        .queryParam("login",login)
                        .queryParam("password", password))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId",is(irina.getId())));

    }

    @Test
    public void givenUser_whenSingIn_thenStatus200() throws Exception {
        User irina = new User(login, password);
        given(userService.signIn(login, password)).willReturn(irina);
        mvc.perform(post("/v1/users/sign_in")
                .queryParam("login", login)
                .queryParam("password", password))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId",is(irina.getId())));
    }

}
