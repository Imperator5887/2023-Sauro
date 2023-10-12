/**
 * Writen by Armando Mac Beath
 * 
 * {@MÆTH}
 */

package frc.utils;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.swerve.LimeLightObject;
import frc.robot.subsystems.swerve.swerveSusbsystem;
import frc.utils.Shuffleboard.Autoselector;

public class AutoUtils {
    protected static Autoselector autoselector = new Autoselector();
    protected static swerveSusbsystem swerve = swerveSusbsystem.getInstance();
    protected static LimeLightObject limelight = LimeLightObject.getInstance();

    public static Command getAuto(){
        return Autoselector.getAutoSelected();
    }
}
