// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.mecanisms.pivoting;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.mecanisms.pivotingSubsystem;

public class pivotingCommand extends CommandBase {

  private final pivotingSubsystem pivot;

  private final Supplier<Double> joystickVelocity;

 
  public pivotingCommand(Supplier<Double> joystickVelocity) {

    this.pivot = pivotingSubsystem.getInstance();

    this.joystickVelocity = joystickVelocity;

    addRequirements(pivot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    pivot.setVelocity(joystickVelocity.get());

    SmartDashboard.putNumber("Joystick", joystickVelocity.get());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
