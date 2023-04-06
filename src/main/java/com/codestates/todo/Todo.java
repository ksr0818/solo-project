package com.codestates.todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;
    @Column(nullable = false, updatable = false, unique = true)
    private String title;
    @Column(nullable = false)
    private boolean completed;
    @Column(nullable = false, updatable = false, unique = true)
    private Integer todoOrder;
}
