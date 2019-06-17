package com.example.springbootruanjian.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TaskReply {
    public static final int UNFINISH = 0;
    public static final int FINISH_ONTIME = 1;
    public static final int FINISH_INTIME = 2;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User teacher;
    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;
    private String review;
    private int achive = 0;
    @Version
    private LocalDateTime updatetime;
    public TaskReply(int id){this.id=id;}
}
