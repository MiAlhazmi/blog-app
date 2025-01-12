package com.in4c.blogApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.in4c.blogApp.model.Hashtag;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    boolean existsByTag(String tag);

    @Query(value = "SELECT COUNT(*) FROM Hashtag t WHERE LOWER(t.tag) = LOWER(:param)", nativeQuery = true)
    int existsByTagIgnoreCase(@Param("param") String tag);

    Optional<Hashtag> findByTag(String tag);

    @Query(value = """
                SELECT
                    t.id AS id,
                    t.tag AS tag
                FROM
                    Hashtag t
                WHERE
                    LOWER(t.tag) = LOWER(:param)
                LIMIT 1
            """, nativeQuery = true)
    Hashtag findByTagIgnoreCase(@Param("param") String tag);
}
