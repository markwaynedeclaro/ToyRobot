package com.exam.ToyRobot.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * This handles geo related activities
 */
@Log4j2
@Service
public class GeoService {

    private static final String[] DIRECTIONS = {"NORTH", "SOUTH", "EAST", "WEST"};

    /**
     * Checks if the direction given is a valid direction
     * @param direction the input direction
     * @return True if valid
     */
    public boolean isValidDirection (String direction) {
        if (Arrays.asList(DIRECTIONS).contains(direction.toUpperCase())) {
            return true;
        }
        return false;
    }


}
