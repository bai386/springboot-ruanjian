package com.example.springbootruanjian.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TaskReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User teacher;
    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;
    public TaskReply(int id){this.id=id;}
}
