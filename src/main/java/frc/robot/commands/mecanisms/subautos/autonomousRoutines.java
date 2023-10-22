/**
 * Writen by Armando Mac Beath
 * 
 * {@MÆTH}
 */
package frc.robot.commands.mecanisms.subautos;

import javax.print.attribute.standard.Finishings;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.pivotingConstants;
import frc.robot.Constants.wristConstants;
import frc.robot.commands.mecanisms.intake.intakeCommand;
import frc.robot.commands.mecanisms.pivoting.pivotPIDCommand;
import frc.robot.commands.mecanisms.wrist.setWristPosition;
import frc.robot.subsystems.mecanisms.intakeSubsystem;
import frc.robot.subsystems.mecanisms.pivotingSubsystem;
import frc.robot.subsystems.mecanisms.solenoidSubsystem;
import frc.robot.subsystems.mecanisms.telescopicSubsystem;
import frc.robot.subsystems.mecanisms.wristSubsystem;
import frc.utils.TrajectoryReader;

public class autonomousRoutines {

    private final wristSubsystem wrist;
    private final intakeSubsystem intake;
    private final pivotingSubsystem pivot;
    private final telescopicSubsystem telescopic;
    private final solenoidSubsystem solenoid;

    private static PathPlannerTrajectory leaveCommunity = PathPlanner.loadPath("leaveCommunity", new PathConstraints(3.5, 1));  


    public autonomousRoutines(){

        wrist = wristSubsystem.getInstance();
        intake = intakeSubsystem.getInstance();
        pivot = pivotingSubsystem.getInstance();
        telescopic = telescopicSubsystem.getInstance();
        solenoid = solenoidSubsystem.getInstance();

    } 

    public static Command placeCubeAndLeave(){

        return 
        
        Commands.parallel(

        superstructureAutos.superPlaceCubeMid(),
        
        Commands.waitSeconds(0.8),

        superstructureAutos.homePosition(),

        Commands.waitSeconds(0.8),

        TrajectoryReader.readTrajectory(leaveCommunity, true)

        );

    }

    
    
}
