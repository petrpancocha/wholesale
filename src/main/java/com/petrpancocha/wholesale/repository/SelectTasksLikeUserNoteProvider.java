package com.petrpancocha.wholesale.repository;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class SelectTasksLikeUserNoteProvider implements ProviderMethodResolver {
    public static String findByLikeUserNote(final String userNote) {
        return new SQL() {{
            SELECT("*");
            FROM("tasks");
            WHERE("user_note like '%' || #{userNote} || '%'");
        }}.toString();
    }
}
