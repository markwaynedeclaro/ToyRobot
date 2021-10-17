package com.exam.ToyRobot.service.impl;

import com.exam.ToyRobot.model.Robot;
import com.exam.ToyRobot.model.Table;
import com.exam.ToyRobot.service.GeoService;
import com.exam.ToyRobot.service.RobotService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service("myRobot")
public class RobotServiceImpl implements RobotService {

    @Autowired
    GeoService geoService;


    /**
     * handles the initialization of Robot location and orientation
     *     Places the robot on the table if the parameters are of valid values
     *     Assumption is that the table has a 5x5 dimension
     *     Valid directions are NORTH, SOUTH, EAST and WEST
     * @param x X-axis location
     * @param y Y-axis location
     * @param f The direction where the robot is initially facing
     * @param table
     * @return
     */
    @Override
    public Robot placeRobot(int x, int y, String f, Table table) {
        if ((x > table.getLength() || x < 0 ) || (y > table.getWidth() || y < 0 ) || !geoService.isValidDirection(f)) {
            return new Robot(0, 0, null, false);
        }
        return new Robot(x, y, f.toUpperCase(), true);
    }

    /**
     * Handles the movement of the robot
     *     will return TRUE if:
     *     robot has already landed on the table (cleared = true)
     *     and
     *     the intended next position is still on the table
     * @param robot
     * @return True if robot is able to move
     */
    @Override
    public Robot move(Robot robot, Table table) {
        if (!robot.isCleared() || robot.getDirection() == null) {
            robot.setValidMove(false);
        } else {
            int tempXLoc = robot.getXLocation();
            int tempYLoc = robot.getYLocation();
            switch (robot.getDirection()) {
                case "NORTH":
                    tempYLoc++;
                    break;
                case "SOUTH":
                    tempYLoc--;
                    break;
                case "WEST":
                    tempXLoc--;
                    break;
                case "EAST":
                    tempXLoc++;
                    break;
            }
            if ((tempXLoc > table.getLength() || tempXLoc < 0 ) || (tempYLoc > table.getWidth() || tempYLoc < 0 )) {
                robot.setValidMove(false);
            } else {
                robot.setValidMove(true);
                robot.setXLocation(tempXLoc);
                robot.setYLocation(tempYLoc);
            }

        }
        return robot;
    }

    /**
     * Handles the turning of robot orientation to its left
     *       will change the value of direction (to the left of current orientation) if:
     *       robot has already landed on the table (cleared = true)
     *       and
     *       direction value is a valid value
     * @param robot
     */
    @Override
    public Robot turnLeft(Robot robot) {
        if (robot.isCleared() || robot.getDirection() != null) {
            switch (robot.getDirection()) {
                case "NORTH":
                    robot.setDirection("WEST");
                    break;
                case "SOUTH":
                    robot.setDirection("EAST");
                    break;
                case "WEST":
                    robot.setDirection("SOUTH");
                    break;
                case "EAST":
                    robot.setDirection("NORTH");
                    break;
            }
        }
        return robot;
    }

    /**
     * Handles the turning of robot orientation to its right
     *       will change the value of direction (to the right of current orientation) if:
     *       robot has already landed on the table (cleared = true)
     *       and
     *       direction value is a valid value
     * @param robot
     */
    @Override
    public Robot turnRight(Robot robot) {
        if (robot.isCleared() || robot.getDirection() != null) {
            switch (robot.getDirection()) {
                case "NORTH":
                    robot.setDirection("EAST");
                    break;
                case "SOUTH":
                    robot.setDirection("WEST");
                    break;
                case "WEST":
                    robot.setDirection("NORTH");
                    break;
                case "EAST":
                    robot.setDirection("SOUTH");
                    break;
            }
        }
        return robot;
    }

    /**
     * Reports the current location of the robot
     * @param robot
     * @return
     */
    @Override
    public String reportLocation(Robot robot) {
        return robot.getXLocation() + "," + robot.getYLocation() + "," + robot.getDirection();
    }

}
