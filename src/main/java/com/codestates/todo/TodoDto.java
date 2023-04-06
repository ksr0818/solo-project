package com.codestates.todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class TodoDto {
    @Getter
    public static class Post {
        private String title;
        private int order;

        private Boolean completed;


    }

    @Getter
    @Setter
    public static class Patch {

        private long todoId;

        private String title;

        private Boolean completed;

        private Integer order;
    }

    @AllArgsConstructor
    @Getter
    public static class Response {
        private long todoId;

        private String title;
        private Integer order;

        private Boolean completed;


    }

}
