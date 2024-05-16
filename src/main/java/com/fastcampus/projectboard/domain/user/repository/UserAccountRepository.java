package com.fastcampus.projectboard.domain.user.repository;

import com.fastcampus.projectboard.domain.user.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}
