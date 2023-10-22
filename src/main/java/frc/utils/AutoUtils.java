/**
 * Writen by Armando Mac Beath
 * 
 * {@MÆTH}
 */

package frc.utils;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.mecanisms.intakeSubsystem;
import frc.robot.subsystems.mecanisms.solenoidSubsystem;
import frc.robot.subsystems.swerve.LimeLightObject;
import frc.robot.subsystems.swerve.swerveSusbsystem;
import frc.utils.Shuffleboard.Autoselector;

public class AutoUtils {
    protected static Autoselector autoselector = new Autoselector();
    protected static swerveSusbsystem swerve = swerveSusbsystem.getInstance();
    protected static LimeLightObject limelight = LimeLightObject.getInstance();
    protected static solenoidSubsystem solenoid = solenoidSubsystem.getInstance();
    protected static intakeSubsystem intake = intakeSubsystem.getInstance();

    public static Command getAuto(){
        return Autoselector.getAutoSelected();
    }
}
