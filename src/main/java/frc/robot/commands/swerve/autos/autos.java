/**
 * Writen by Armando Mac Beath
 * 
 * {@MÆTH}
 */
package frc.robot.commands.swerve.autos;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.swerve.limelight.autoAlign;
import frc.utils.AutoUtils;
import frc.utils.TrajectoryReader;

public class autos extends AutoUtils {

    private static PathPlannerTrajectory holonomic = PathPlanner.loadPath("New Path", new PathConstraints(1.5, 1));
    private static PathPlannerTrajectory goForward = PathPlanner.loadPath("autoForward", new PathConstraints(3, 3));
    private static PathPlannerTrajectory defaultAuto = PathPlanner.loadPath("Default Auto", new PathConstraints(1, 1));
   

    public static Command autoForward(){
        return Commands.sequence(TrajectoryReader.readTrajectory(goForward, true));
    }


    public static Command alignAuto(){

        return Commands.sequence(
            TrajectoryReader.readTrajectory(holonomic, true),
            new autoAlign(swerve, limelight, true));
    }

    public static Command holonomic(){
        return Commands.sequence(TrajectoryReader.readTrajectory(holonomic, true),
        new autoAlign(swerve, limelight, true));
    }
    public static Command autoDefault(){
        return Commands.sequence(
            TrajectoryReader.readTrajectory(defaultAuto, true)
        );
    }


    
}