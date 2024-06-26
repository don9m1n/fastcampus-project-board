package com.fastcampus.projectboard.domain.articlecomment.controller;

import com.fastcampus.projectboard.config.TestSecurityConfig;
import com.fastcampus.projectboard.domain.articlecomment.dto.ArticleCommentDto;
import com.fastcampus.projectboard.domain.articlecomment.dto.request.ArticleCommentRequest;
import com.fastcampus.projectboard.domain.articlecomment.service.ArticleCommentService;
import com.fastcampus.projectboard.util.FormDataEncoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("댓글 컨트롤러 테스트")
@Import({TestSecurityConfig.class, FormDataEncoder.class})
@WebMvcTest(ArticleCommentController.class)
class ArticleCommentControllerTest {

    private final MockMvc mvc;
    private final FormDataEncoder formDataEncoder;

    @MockBean
    private ArticleCommentService articleCommentService;

    public ArticleCommentControllerTest(@Autowired MockMvc mvc, @Autowired FormDataEncoder formDataEncoder) {
        this.mvc = mvc;
        this.formDataEncoder = formDataEncoder;
    }

    @WithUserDetails(value = "test123", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @DisplayName("[POST] 댓글 등록 - 정상 호출")
    @Test
    void givenArticleCommentInfo_whenRequesting_thenSaveNewArticleComment() throws Exception {
        // given
        long articleId = 1L;
        ArticleCommentRequest request = ArticleCommentRequest.of(articleId, "댓글댓글");
        willDoNothing().given(articleCommentService).saveArticleComment(any(ArticleCommentDto.class));

        // when
        mvc.perform(post("/comments/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(formDataEncoder.encode(request))
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/articles/" + articleId))
                .andExpect(redirectedUrl("/articles/" + articleId));

        // then
        then(articleCommentService).should().saveArticleComment(any(ArticleCommentDto.class));
    }

    @WithUserDetails(value = "test123", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @DisplayName("[POST] 댓글 삭제 - 정상 호출")
    @Test
    void givenArticleCommentId_whenRequesting_thenDeleteArticleComment() throws Exception {
        // given
        long articleId = 1L;
        long articleCommentId = 1L;
        String userId = "test123";
        willDoNothing().given(articleCommentService).deleteArticleComment(articleCommentId, userId);

        // when
        mvc.perform(post("/comments/" + articleCommentId + "/delete")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(formDataEncoder.encode(Map.of("articleId", articleId)))
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/articles/" + articleId))
                .andExpect(redirectedUrl("/articles/" + articleId));

        // then
        then(articleCommentService).should().deleteArticleComment(articleCommentId, userId);
    }

    @WithUserDetails(value = "test123", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @DisplayName("[POST] 대댓글 등록 - 정상 호출")
    @Test
    void givenArticleCommentInfoWithParentCommentId_whenRequesting_thenSaveNewChildComment() throws Exception {
        // given
        long articleId = 1L;
        ArticleCommentRequest request = ArticleCommentRequest.of(articleId, 1L, "댓글댓글");
        willDoNothing().given(articleCommentService).saveArticleComment(any(ArticleCommentDto.class));

        // when
        mvc.perform(post("/comments/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(formDataEncoder.encode(request))
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/articles/" + articleId))
                .andExpect(redirectedUrl("/articles/" + articleId));

        // then
        then(articleCommentService).should().saveArticleComment(any(ArticleCommentDto.class));
    }

}