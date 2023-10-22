/**
 * Writen by Armando Mac Beath
 * 
 * {@MÆTH}
 */

package frc.robot.commands.mecanisms.subautos;

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

public class superstructureAutos {

    private final wristSubsystem wrist;
    private final intakeSubsystem intake;
    private final pivotingSubsystem pivot;
    private final telescopicSubsystem telescopic;
    private final solenoidSubsystem solenoid;

    public superstructureAutos(){

        wrist = wristSubsystem.getInstance();
        intake = intakeSubsystem.getInstance();
        pivot = pivotingSubsystem.getInstance();
        telescopic = telescopicSubsystem.getInstance();
        solenoid = solenoidSubsystem.getInstance();

    } 

    public static Command pickingPose(){

        return 
        Commands.parallel(
            new pivotPIDCommand(pivotingConstants.pickingPose),
            new setWristPosition(wristConstants.pickingPose)
        );

    }

    public static Command pickCube(){

        return 
        Commands.parallel(
            new pivotPIDCommand(pivotingConstants.pickingPose),
            new setWristPosition(wristConstants.pickingPose)
        );

    }

    public static Command homePosition(){

        return 
        Commands.parallel(
            new pivotPIDCommand(pivotingConstants.homePosition),
            new setWristPosition(wristConstants.homePose)
        );

    }

    public static Command placeCubeMid(){

        return 
        Commands.parallel(
            new pivotPIDCommand(pivotingConstants.placePosition),
            new setWristPosition(wristConstants.cubeMidPose)
        );

    }


    public static Command superPlaceCubeMid(){

        return 
        Commands.sequence(
            placeCubeMid(),
            new intakeCommand(true)

        );

    }

    public static Command placeCubeHigh(){

        return 
        Commands.parallel(
            new pivotPIDCommand(pivotingConstants.placePosition),
            new setWristPosition(wristConstants.cubeHighPose)
        );

    }
    
}
