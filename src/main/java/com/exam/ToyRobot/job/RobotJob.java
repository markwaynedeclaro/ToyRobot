package com.exam.ToyRobot.job;

import com.exam.ToyRobot.model.Robot;
import com.exam.ToyRobot.model.Table;
import com.exam.ToyRobot.service.RobotService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.exam.ToyRobot.config.Resources.MAIN_RB;

@Log4j2
@Component
public class RobotJob {

    @Autowired
    @Qualifier("myRobot")
    private RobotService robotService;

    private Scanner scanner = new Scanner(System.in);

    public void startRobotController() {
        Table table = new Table(Integer.parseInt(MAIN_RB.get("table.length")),Integer.parseInt(MAIN_RB.get("table.width")));
        Robot robot = new Robot();
        String input = "";

        printMenu();

        do {

            input = getInput(">> ","^[0-6]$");

            switch (input) {
                case "0":
                    printMenu();
                    break;
                case "1":
                    String x,y,f;

                    do {
                        x = getInput("Enter X value : ","^[0-"+table.getLength()+"]$");
                    } while(x.length() == 0);

                    do {
                        y = getInput("Enter Y value : ","^[0-"+table.getWidth()+"]$");
                    } while(y.length() == 0);

                    do {
                        f = getInput("Enter F value (North / South / East / West) : ","^(NORTH|SOUTH|EAST|WEST)$");
                    } while(f.length() == 0);

                    robot = robotService.placeRobot(Integer.parseInt(x), Integer.parseInt(y), f, table);
                    log.debug("===>>> "+ robot.getXLocation() +" "+ robot.getYLocation() +" "+ robot.getDirection() +" "+ robot.isCleared() +" "+ robot.isValidMove());
                    break;
                case "2":
                    robot = robotService.move(robot, table);
                    log.debug("===>>> "+ robot.getXLocation() +" "+ robot.getYLocation() +" "+ robot.getDirection() +" "+ robot.isCleared() +" "+ robot.isValidMove());
                    break;
                case "3":
                    robotService.turnLeft(robot);
                    log.debug("===>>> "+ robot.getXLocation() +" "+ robot.getYLocation() +" "+ robot.getDirection() +" "+ robot.isCleared() +" "+ robot.isValidMove());
                    break;
                case "4":
                    robotService.turnRight(robot);
                    log.debug("===>>> "+ robot.getXLocation() +" "+ robot.getYLocation() +" "+ robot.getDirection() +" "+ robot.isCleared() +" "+ robot.isValidMove());
                    break;
                case "5":
                    System.out.println(robotService.reportLocation(robot));
                    break;
                case "6":
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    break;
            }

        } while(input != null);

    }

    private static void printMenu() {
        System.out.println("******************************");
        System.out.println("0 - Show Menu");
        System.out.println("1 - Place");
        System.out.println("2 - Move");
        System.out.println("3 - Left");
        System.out.println("4 - Right");
        System.out.println("5 - Report");
        System.out.println("6 - Exit");
        System.out.println("******************************\n");
    }


    private String getInput(String message, String mustMatch) {
        System.out.print(message);
        String f = scanner.next();
        scanner.nextLine();

        if (f.toUpperCase().matches(mustMatch))
            return f;

        return "";
    }

}
