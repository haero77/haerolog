package com.haerolog.domain.auth.api;

import com.haerolog.domain.auth.service.login.LoginRequest;
import com.haerolog.domain.user.model.User;
import com.haerolog.support.IntegrationTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LoginApiTest extends IntegrationTestSupport {

    @DisplayName("로그인 성공 후 세션을 응답한다.")
    @Test
    void login() throws Exception {
        // given
        super.userRepository.save(
                User.builder()
                        .email("email@email.com")
                        .password("password")
                        .build()
        );

        LoginRequest loginRequest = LoginRequest.builder()
                .email("email@email.com")
                .password("password")
                .build();
        String json = objectMapper.writeValueAsString(loginRequest);

        // expected
        mockMvc.perform(post("/api/v1/login")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.data.accessToken").exists())
                .andDo(print());

        int sessionSize = super.sessionRepository.findAll().size();
        assertThat(sessionSize).isEqualTo(1);
    }

}