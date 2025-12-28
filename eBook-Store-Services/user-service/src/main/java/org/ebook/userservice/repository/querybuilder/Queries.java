package org.ebook.userservice.repository.querybuilder;

public class Queries {

    static public String userTypeSearchByName= """
            select id from user_type where user_type=?
            """;

}
