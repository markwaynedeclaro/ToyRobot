package com.exam.ToyRobot.service;


import com.exam.ToyRobot.model.Robot;
import com.exam.ToyRobot.model.Table;

/**
 * This is the interface for a robot
 */
public interface RobotService {

    /**
     * handles the initialization of Robot location and orientation
     * @param x X-axis location
     * @param y Y-axis location
     * @param f The direction where the robot is initially facing
     * @param table
     * @return
     */
    public Robot placeRobot (int x, int y, String f, Table table);

    /**
     * Handles the movement of the robot
     * @return True if robot is able to move
     */
    public Robot move (Robot robot, Table table);

    /**
     * Handles the turning of robot orientation to its left
     */
    public Robot turnLeft (Robot robot);

    /**
     * Handles the turning of robot orientation to its right
     */
    public Robot turnRight (Robot robot);

    /**
     * Reports the current location of the robot
     * @return
     */
    public String reportLocation (Robot robot);

}
