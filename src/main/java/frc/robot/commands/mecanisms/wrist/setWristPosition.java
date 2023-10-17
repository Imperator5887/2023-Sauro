/**
 * Writen by Armando Mac Beath
 * 
 * {@MÆTH}
 */

package frc.robot.commands.mecanisms.wrist;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.mecanisms.wristSubsystem;

public class setWristPosition extends CommandBase {

    private final wristSubsystem wrist;
    private final double position;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public setWristPosition(double position) {

    this.wrist = wristSubsystem.getInstance();

    this.position = position;

    addRequirements(wrist);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    wrist.setGoal(position);
    
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
