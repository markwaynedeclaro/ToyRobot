package com.exam.ToyRobot.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class Table {

    private int length;
    private int width;

    public Table(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public Table(){};

}
