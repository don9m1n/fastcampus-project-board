package com.fastcampus.projectboard.domain.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SearchType {

    TITLE("제목"),
    CONTENT("본문"),
    ID("아이디"),
    NICKNAME("닉네임"),
    HASHTAG("해시태그");

    private final String description;
}
