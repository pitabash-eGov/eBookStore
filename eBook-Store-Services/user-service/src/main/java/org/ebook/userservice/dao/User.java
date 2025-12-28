package org.ebook.userservice.dao;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private Long id;
    private String name;
    private String password;
    private String userType;
    private Boolean isActive;

    private LocalDateTime createdAt;
    private String createdBy;

    // getters & setters
}
