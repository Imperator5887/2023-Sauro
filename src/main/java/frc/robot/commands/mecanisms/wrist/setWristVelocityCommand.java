/**
 * Writen by Armando Mac Beath
 * 
 * {@MÆTH}
 */

package frc.robot.commands.mecanisms.wrist;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.mecanisms.wristSubsystem;

public class setWristVelocityCommand extends CommandBase {


    private final wristSubsystem wrist;

    private final Supplier<Double> velocity;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public setWristVelocityCommand(Supplier<Double> velocity) {

    this.wrist = wristSubsystem.getInstance();

    this.velocity = velocity;    

    addRequirements(wrist);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    wrist.setVelocity(velocity.get());
    
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
