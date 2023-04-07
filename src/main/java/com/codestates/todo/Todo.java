package com.codestates.todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;
    @Column(nullable = false, unique = true)
    private String title;
    @Column(nullable = false)
    private Boolean completed;
    @Column(nullable = false, unique = true)
    private Integer todoOrder;

}
