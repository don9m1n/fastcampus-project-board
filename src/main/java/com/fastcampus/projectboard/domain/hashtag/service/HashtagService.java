package com.fastcampus.projectboard.domain.hashtag.service;

import com.fastcampus.projectboard.domain.hashtag.model.Hashtag;
import com.fastcampus.projectboard.domain.hashtag.repository.HashtagRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class HashtagService {
    private final HashtagRepository hashtagRepository;

    public HashtagService(HashtagRepository hashtagRepository) {
        this.hashtagRepository = hashtagRepository;
    }

    public Set<String> parseHashtagNames(String content) {
        if (content == null) {
            return Set.of();
        }

        // \w: A-Za-z0-9_ (대문자, 소문자, 숫자, 언더스코어), 가-힣: 한글, +: 하나 이상 나옴
        Pattern pattern = Pattern.compile("#[\\w가-힣]+");
        Matcher matcher = pattern.matcher(content.strip()); // strip(): 전후 공백제거

        Set<String> result = new HashSet<>();

        // 매칭된 해시태그가 있으면 계속해서 반복한다.
        while (matcher.find()) {
            // 매칭된 해시태그를 저장할 때 #을 제거해서 저장한다.
            result.add(matcher.group().replace("#", ""));
        }

        // return Unmodifiable Set
        return Set.copyOf(result);
    }

    public Set<Hashtag> findHashtagsByNames(Set<String> hashtagNames) {
        return new HashSet<>(hashtagRepository.findByHashtagNameIn(hashtagNames));
    }


    // 어떤 게시글에도 포함되지 않는 해시태그를 제거하는 메서드
    public void deleteHashtagWithoutArticles(Long hashtagId) {
        Hashtag hashtag = hashtagRepository.getReferenceById(hashtagId);

        // 해당 해시태그를 가진 게시글이 없다면 해당 해시태그를 삭제
        if (hashtag.getArticles().isEmpty()) {
            hashtagRepository.delete(hashtag);
        }
    }
}
