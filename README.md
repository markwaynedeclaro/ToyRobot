# ToyRobot


The application is a simulation of a toy robot moving on a square tabletop, of dimensions 5 units x 5 units. There are no other obstructions on the table surface. The robot is free to roam around the surface of the table, but must be prevented from falling to destruction. Any movement that would result in the robot falling from the table must be prevented, however further valid movement commands must still be allowed.

COMMANDS: PLACE X,Y,F - will put the toy robot on the table in position X,Y and facing NORTH, SOUTH, EAST or WEST. MOVE - will move the toy robot one unit forward in the direction it is currently facing. LEFT, RIGHT - will rotate the robot 90 degrees in the specified direction without changing the position of the robot. REPORT - will announce the X,Y and orientation of the robot.

The origin (0,0) can be considered to be the SOUTH WEST most corner. The first valid command to the robot is a PLACE command, after that, any sequence of commands may be issued, in any order, including another PLACE command. The application should discard all commands in the sequence until a valid PLACE command has been executed. A robot that is not on the table can choose to ignore the MOVE, LEFT, RIGHT and REPORT commands.

## Constraints 
The toy robot must not fall off the table during movement. This also includes the initial placement of the toy robot. Any move that would cause the robot to fall must be ignored.

## Configuration
You can configure the size of the table in **application.properties**


## Example Input and Output:

a)

PLACE 0,0,NORTH

MOVE

REPORT

Output:  **0,1,NORTH**


b)

PLACE 0,0,NORTH

LEFT

REPORT

Output:  **0,0,WEST**


c)

PLACE 1,2,EAST

MOVE

MOVE

LEFT

MOVE

REPORT

Output:  **3,3,NORTH**

 
 

## MVN Run Configuration 
  
*mvn spring-boot:run
  
  
## Running Unit Test 
Make sure to do the following first before triggering unit tests:
- This application uses Spring Profiling feature. Make sure that the value of ****spring.profiles.active**** is **test** when running unit tests.
- In file **ToyRobotApplication.java**, comment out line #28 (//robotJob.startRobotController();)
