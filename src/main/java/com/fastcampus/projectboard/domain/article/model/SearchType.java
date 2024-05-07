package com.fastcampus.projectboard.domain.article.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SearchType {

    TITLE, CONTENT, ID, NICKNAME, HASHTAG;

}
