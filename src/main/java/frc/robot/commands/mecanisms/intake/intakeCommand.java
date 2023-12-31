/**
 * Writen by Armando Mac Beath
 * 
 * {@MÆTH}
 */

package frc.robot.commands.mecanisms.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.mecanisms.intakeSubsystem;

public class intakeCommand extends CommandBase {

    private final intakeSubsystem intake;
   // private final boolean pickingCone;
   private final boolean isPickingCone;
  /**  
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public intakeCommand(Boolean isPickingCone) {

    this.isPickingCone = isPickingCone;
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

  intake.setPiecePickingMode(isPickingCone);    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.stop();
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
