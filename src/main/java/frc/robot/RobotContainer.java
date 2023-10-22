// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.mecanisms.intake.idleIntake;
import frc.robot.commands.mecanisms.intake.intakeCommand;
import frc.robot.commands.mecanisms.pivoting.pivotingCommand;
import frc.robot.commands.mecanisms.subautos.autonomousRoutines;
import frc.robot.commands.mecanisms.subautos.superstructureAutos;
import frc.robot.commands.mecanisms.telescopic.setSolenoid;
import frc.robot.commands.mecanisms.telescopic.setTelescopicVelocityCommand;
import frc.robot.commands.swerve.swerveDriveComando;
import frc.robot.commands.swerve.autos.autos;
import frc.robot.commands.swerve.limelight.autoAlign;
import frc.robot.subsystems.mecanisms.pivotingSubsystem;
import frc.robot.subsystems.mecanisms.telescopicSubsystem;
import frc.robot.subsystems.mecanisms.wristSubsystem;
import frc.robot.subsystems.swerve.LimeLightObject;
import frc.robot.subsystems.swerve.swerveSusbsystem;

public class RobotContainer {

  public static Joystick driverJoytick = new Joystick(OIConstants.kDriverControllerPort);
  private final Joystick controlPlacer = new Joystick(OIConstants.kPlacerControllerPort);

  private swerveSusbsystem swerveSubsystem;
  private LimeLightObject limelight;

    
  private final wristSubsystem wrist;

  private final telescopicSubsystem telescopic;

  private final pivotingSubsystem pivot;


  public RobotContainer() {

    wrist = wristSubsystem.getInstance();
    telescopic = telescopicSubsystem.getInstance();
    pivot = pivotingSubsystem.getInstance();

    swerveSubsystem = swerveSusbsystem.getInstance();
        limelight  = LimeLightObject.getInstance();

          swerveSubsystem.setDefaultCommand(new swerveDriveComando(
                swerveSubsystem,
                () -> -driverJoytick.getRawAxis(OIConstants.kDriverYAxis),
                () -> driverJoytick.getRawAxis(OIConstants.kDriverXAxis),
                () -> driverJoytick.getRawAxis(OIConstants.kDriverRotAxis),
                () -> !driverJoytick.getRawButton(OIConstants.kDriverFieldOrientedButtonIdx),
                true
                ));

    /*wrist.setDefaultCommand(new setWristVelocityCommand(
      () -> controlPlacer.getRawAxis(1)
    ));
/* */
     telescopic.setDefaultCommand(
      new setTelescopicVelocityCommand(
        () -> controlPlacer.getRawAxis(1)
      ));

      

       pivot.setDefaultCommand(
        new pivotingCommand(
          () -> controlPlacer.getRawAxis(5)
        )
      );


      
    configureBindings();
  }

  private void configureBindings() {
 
 
 
   //PICKING POSE
  new JoystickButton(controlPlacer, 2).whileTrue(superstructureAutos.pickingPose());

  //HOME POSITION
  new JoystickButton(controlPlacer, 3).whileTrue(superstructureAutos.homePosition());

  //PLace cube mid position
  new JoystickButton(controlPlacer, 4).whileTrue(superstructureAutos.placeCubeMid());

   //PLace cube mid position
   //new JoystickButton(controlPlacer, 7).whileTrue(superstructureAutos.placeCubeHigh());

    /** INTAKE */
    new JoystickButton(controlPlacer, 5).whileTrue(new intakeCommand(true));

    new JoystickButton(controlPlacer, 5).whileFalse(new idleIntake(true));

    new JoystickButton(controlPlacer, 6).whileTrue(new intakeCommand(false));

    new JoystickButton(controlPlacer, 6).whileFalse(new idleIntake(false));


    /** SOLENOIID */
    new JoystickButton(controlPlacer, 7).whileFalse(new setSolenoid(false));
    
    new JoystickButton(controlPlacer, 8).whileFalse(new setSolenoid(true));

    /**AUTOALIGN */
    new JoystickButton(driverJoytick, 5).whileTrue(new autoAlign(swerveSubsystem, limelight, true));
    
  }

  public Command getAutonomousCommand() {
    return autos.placeAndLeave();
    //return superstructureAutos.superPlaceCubeMid();
  }

}
