package org.ebook.userservice.repository;

import org.ebook.userservice.dao.User;
import org.ebook.userservice.repository.querybuilder.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    public User createUser(User user) {

        String sql = """
                    INSERT INTO users (name, password, user_type_id, created_by)
                    VALUES (
                        ?,
                        ?,
                        (SELECT id FROM user_type WHERE type_name = ?),
                        ?
                    )
                    RETURNING 
                        id,
                        name,
                        password,
                        is_active,
                        created_at,
                        created_by,
                        (SELECT type_name FROM user_type WHERE id = users.user_type_id) AS type_name
                """;

        return jdbcTemplate.queryForObject(
                sql,
                userRowMapper,
                user.getName(),
                user.getPassword(),
                user.getUserType(),
                user.getCreatedBy()
        );


    }


}
