package frc.robot.commands.mecanisms.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.mecanisms.intakeSubsystem;

public class idleIntake extends CommandBase {

    private final intakeSubsystem intake;
   // private final boolean pickingCone;
   private final boolean pickedCone;
  /**  
   * Creates a new idleIntake Command.
   *
   * @param pickedCone if the picked piece was a cone
   */
  public idleIntake(Boolean pickedCone) {

    this.pickedCone = pickedCone;
    this.intake = intakeSubsystem.getInstance();
    
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    intake.setIdleMode(pickedCone);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
