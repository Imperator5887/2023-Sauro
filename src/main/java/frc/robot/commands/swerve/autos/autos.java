/**
 * Writen by Armando Mac Beath
 * 
 * {@MÆTH}
 */
package frc.robot.commands.swerve.autos;

import java.util.function.Supplier;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.mecanisms.telescopic.setTelescopicVelocityCommand;
import frc.robot.commands.swerve.limelight.autoAlign;
import frc.robot.subsystems.mecanisms.solenoidSubsystem;
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
            TrajectoryReader.readTrajectory(holonomic, true));
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

    public static Command extendTelescopic(Supplier<Double> velocity){

        if(velocity.get() >=0.1 || velocity.get() <= -0.1){}
       return Commands.parallel(
        new InstantCommand(
            () -> solenoid.set(false), solenoid),
        new setTelescopicVelocityCommand(velocity));

    }


    
}