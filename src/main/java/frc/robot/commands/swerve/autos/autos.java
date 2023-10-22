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
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.mecanisms.telescopic.setTelescopicVelocityCommand;
import frc.robot.commands.swerve.limelight.autoAlign;
import frc.robot.subsystems.mecanisms.solenoidSubsystem;
import frc.utils.AutoUtils;
import frc.utils.TrajectoryReader;

public class autos extends AutoUtils {

    private static PathPlannerTrajectory leaveComm = PathPlanner.loadPath("leaveCommunity", new PathConstraints(4, 3));
    private static PathPlannerTrajectory goForward = PathPlanner.loadPath("autoForward", new PathConstraints(3, 3));
    private static PathPlannerTrajectory defaultAuto = PathPlanner.loadPath("Default Auto", new PathConstraints(1, 1));
   

    public static Command autoForward(){
        return Commands.sequence(TrajectoryReader.readTrajectory(goForward, true));
    }


    public static Command leaveComm(){

        return Commands.sequence(
            TrajectoryReader.readTrajectory(leaveComm, true));
    }

    public static Command holonomic(){
        return Commands.sequence(TrajectoryReader.readTrajectory(leaveComm, true),
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

    public static Command placeAndLeave(){

        return Commands.parallel(
            Commands.sequence(
            new InstantCommand(
                () ->  intake.setPiecePickingMode(true),
                intake
            ),
            new InstantCommand(
                () ->  intake.stop(),
                intake
            )
            ),
            Commands.waitSeconds(2),

            leaveComm()
            
            );
        
    }

    public static Command placeOnGround(){

        return Commands.sequence(
            new RunCommand(
                () ->  intake.setPiecePickingMode(true),
                intake
            ).withTimeout(2),

            Commands.waitSeconds(2),

            leaveComm()
    
            
            );
        
    }





    
}