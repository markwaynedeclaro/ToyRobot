package com.exam.ToyRobot.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class Robot {

    /**
     * X-axis location
     */
    private int xLocation;

    /**
     * Y-axis location
     */
    private int yLocation;

    /**
     * Orientation
     */
    private String direction;

    /**
     * If robot has landed on the table
     */
    private boolean cleared;

    /**
     * Response from the robot
     */
    private boolean validMove;

    public Robot(int xLocation, int yLocation, String direction, boolean cleared) {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.direction = direction;
        this.cleared = cleared;
    }

    public Robot(){};

}
