package com.yo.day1.repository;

import com.yo.day1.domain.entity.RefreshTokenSession;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenSessionRepository extends JpaRepository<RefreshTokenSession, Long> {

  Optional<RefreshTokenSession> findByJti(String jti);

  List<RefreshTokenSession> findByUserIdAndRevokedAtIsNull(Long userId);

  List<RefreshTokenSession> findByExpiresAtBeforeAndRevokedAtIsNull(Instant now);
}
