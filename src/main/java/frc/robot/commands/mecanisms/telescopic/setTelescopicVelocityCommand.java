package frc.robot.commands.mecanisms.telescopic;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.mecanisms.telescopicSubsystem;

public class setTelescopicVelocityCommand extends CommandBase {

    private final telescopicSubsystem arm;
    //private final double desiredGoal;
    private final Supplier<Double> velocity;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public setTelescopicVelocityCommand(Supplier<Double> velocity) {

    arm = telescopicSubsystem.getInstance();

    this.velocity = velocity;
    
    addRequirements(arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    arm.periodic();

    arm.setVelocity(velocity.get());
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return arm.isInGolePosition();
  }
}