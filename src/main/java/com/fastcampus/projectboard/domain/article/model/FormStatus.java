package com.fastcampus.projectboard.domain.article.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormStatus {

    CREATE("저장", false),
    UPDATE("수정", true);

    @Getter private final String description;
    @Getter private final Boolean update;

}
