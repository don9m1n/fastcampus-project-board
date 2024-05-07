package com.fastcampus.projectboard.domain.home;

import com.fastcampus.projectboard.common.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfig.class)
@WebMvcTest(controllers = HomeController.class)
class HomeControllerTest {

    private final MockMvc mvc;

    public HomeControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    void givenNothing_whenRequestingRootPage_thenRedirectToArticlesIndex() throws Exception {

        // Given

        // When & Then
        mvc.perform(get("/"))
                .andExpect(status().isOk());

    }
}