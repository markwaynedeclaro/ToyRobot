package com.exam.ToyRobot;

import com.exam.ToyRobot.model.Robot;
import com.exam.ToyRobot.model.Table;
import com.exam.ToyRobot.service.RobotService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static com.exam.ToyRobot.config.Resources.MAIN_RB;

@Log4j2
@RunWith(SpringRunner.class)
@Profile("test")
@SpringBootTest()
public class RobotServiceTest {

    private Table table;

    @Autowired
    @Qualifier("myRobot")
    RobotService robotService;

    @Before
    public void setUp() throws Exception {
        table =  new Table(Integer.parseInt(MAIN_RB.get("table.length")),Integer.parseInt(MAIN_RB.get("table.width")));
    }

    @After
    public void tearDown() throws Exception {
        table = null;
    }


    @Test
    public void robotReachOneOfTheEdgeLimitTestCase() {

        Robot robot = new Robot();
        robot = robotService.placeRobot(2, 3, "NORTH", table);
        assertEquals(" get X location", 2, robot.getXLocation());
        assertEquals(" get Y location", 3, robot.getYLocation());
        assertEquals(" get direction", "NORTH", robot.getDirection());

        robot = robotService.turnLeft(robot);
        assertEquals(" get location", "2,3,WEST", robotService.reportLocation(robot));

        robot = robotService.move(robot, table);
        assertTrue(robot.isValidMove());
        assertEquals(" get location", "1,3,WEST", robotService.reportLocation(robot));

        robot = robotService.turnRight(robot);
        robot = robotService.turnRight(robot);
        assertEquals(" get location", "1,3,EAST", robotService.reportLocation(robot));

        robot = robotService.move(robot, table);
        assertTrue(robot.isValidMove());
        assertEquals(" get location", "2,3,EAST", robotService.reportLocation(robot));

        robot = robotService.move(robot, table);
        assertTrue(robot.isValidMove());
        assertEquals(" get location", "3,3,EAST", robotService.reportLocation(robot));

        robot = robotService.move(robot, table);
        assertTrue(robot.isValidMove());
        assertEquals(" get location", "4,3,EAST", robotService.reportLocation(robot));

        robot = robotService.move(robot, table);
        assertTrue(robot.isValidMove());
        assertEquals(" get location", "5,3,EAST", robotService.reportLocation(robot));

        robot = robotService.move(robot, table);
        assertFalse(robot.isValidMove());
        assertEquals(" get X location", 5, robot.getXLocation());
        assertEquals(" get Y location", 3, robot.getYLocation());
        assertEquals(" get direction", "EAST", robot.getDirection());
        assertEquals(" get location", "5,3,EAST", robotService.reportLocation(robot));
    }

    @Test
    public void waitForClearanceBeforeMovementTestCase() {

        Robot robot = new Robot();

        assertEquals(" get X location", 0, robot.getXLocation());
        assertEquals(" get Y location", 0, robot.getYLocation());
        assertEquals(" get direction", null, robot.getDirection());

        robot = robotService.move(robot, table);
        assertFalse(robot.isValidMove());
        assertEquals(" get X location", 0, robot.getXLocation());
        assertEquals(" get Y location", 0, robot.getYLocation());
        assertEquals(" get direction", null, robot.getDirection());
        assertEquals(" get location", "0,0,null", robotService.reportLocation(robot));

        robot = robotService.turnRight(robot);
        assertEquals(" get X location", 0, robot.getXLocation());
        assertEquals(" get Y location", 0, robot.getYLocation());
        assertEquals(" get direction", null, robot.getDirection());
        assertEquals(" get location", "0,0,null", robotService.reportLocation(robot));

        robot = robotService.turnLeft(robot);
        assertEquals(" get X location", 0, robot.getXLocation());
        assertEquals(" get Y location", 0, robot.getYLocation());
        assertEquals(" get direction", null, robot.getDirection());
        assertEquals(" get location", "0,0,null", robotService.reportLocation(robot));

        robot = robotService.placeRobot(2, 1, "EAST", table);
        assertEquals(" get X location", 2, robot.getXLocation());
        assertEquals(" get Y location", 1, robot.getYLocation());
        assertEquals(" get direction", "EAST", robot.getDirection());

        robot = robotService.turnRight(robot);
        robot = robotService.turnRight(robot);
        assertEquals(" get location", "2,1,WEST", robotService.reportLocation(robot));

        robot = robotService.move(robot, table);
        assertTrue(robot.isValidMove());
        assertEquals(" get location", "1,1,WEST", robotService.reportLocation(robot));

        robot = robotService.turnLeft(robot);
        assertEquals(" get location", "1,1,SOUTH", robotService.reportLocation(robot));

        robot = robotService.move(robot, table);
        assertTrue(robot.isValidMove());
        assertEquals(" get location", "1,0,SOUTH", robotService.reportLocation(robot));

        robot = robotService.move(robot, table);
        assertFalse(robot.isValidMove());
        assertEquals(" get location", "1,0,SOUTH", robotService.reportLocation(robot));
    }


    @Test
    public void allInputsAreValidTestCase() {

        Robot robot = new Robot();
        robot = robotService.placeRobot(2, 3, "NORTH", table);
        assertEquals(" get X location", 2, robot.getXLocation());
        assertEquals(" get Y location", 3, robot.getYLocation());
        assertEquals(" get direction", "NORTH", robot.getDirection());

        robot = robotService.turnLeft(robot);
        robot = robotService.move(robot, table);
        assertTrue(robot.isValidMove());
        robot = robotService.turnRight(robot);
        robot = robotService.turnRight(robot);
        robot = robotService.move(robot, table);
        assertTrue(robot.isValidMove());
        robot = robotService.move(robot, table);
        assertTrue(robot.isValidMove());
        robot = robotService.move(robot, table);
        assertTrue(robot.isValidMove());
        robot = robotService.move(robot, table);
        assertTrue(robot.isValidMove());
        assertEquals(" get X location", 5, robot.getXLocation());
        assertEquals(" get Y location", 3, robot.getYLocation());
        assertEquals(" get direction", "EAST", robot.getDirection());
        assertEquals(" get location", "5,3,EAST", robotService.reportLocation(robot));
    }


    @Test
    public void negativeTestingForPlaceMethodTestCase() {

        Robot robot = new Robot();

        robot = robotService.placeRobot(2, 3, "SOMEWHERE", table);
        assertEquals(" get X location", 0, robot.getXLocation());
        assertEquals(" get Y location", 0, robot.getYLocation());
        assertEquals(" get direction", null, robot.getDirection());

        robot = robotService.placeRobot(5, 3, "north", table);
        assertEquals(" get X location", 5, robot.getXLocation());
        assertEquals(" get Y location", 3, robot.getYLocation());
        assertEquals(" get direction", "NORTH", robot.getDirection());

        robot = robotService.placeRobot(table.getLength() + 1, 3, "NORTH", table);
        assertEquals(" get X location", 0, robot.getXLocation());
        assertEquals(" get Y location", 0, robot.getYLocation());
        assertEquals(" get direction", null, robot.getDirection());

        robot = robotService.placeRobot(3, table.getWidth() + 1, "NORTH", table);
        assertEquals(" get X location", 0, robot.getXLocation());
        assertEquals(" get Y location", 0, robot.getYLocation());
        assertEquals(" get direction", null, robot.getDirection());

        robot = robotService.placeRobot(-1, 5, "NORTH", table);
        assertEquals(" get X location", 0, robot.getXLocation());
        assertEquals(" get Y location", 0, robot.getYLocation());
        assertEquals(" get direction", null, robot.getDirection());
    }


}

