/**
 * Writen by Armando Mac Beath
 * 
 * {@MÆTH}
 */

package frc.robot.commands.mecanisms.telescopic;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.mecanisms.solenoidSubsystem;

public class setSolenoid extends CommandBase {


    private final solenoidSubsystem solenoid;

    private boolean on;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public setSolenoid(boolean on) {
    this.solenoid = solenoidSubsystem.getInstance();

    this.on = on;
    
    addRequirements(solenoid);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    solenoid.set(on);
    
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
