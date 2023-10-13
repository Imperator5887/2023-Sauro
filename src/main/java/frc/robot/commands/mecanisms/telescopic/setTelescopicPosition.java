package frc.robot.commands.mecanisms.telescopic;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.mecanisms.telescopicSubsystem;

public class setTelescopicPosition extends CommandBase {

    private final telescopicSubsystem arm;
    //private final double desiredGoal;
    private final double goal;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public setTelescopicPosition(double goal) {

    arm = telescopicSubsystem.getInstance();

    this.goal = goal;
    
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

    arm.setVelocity(goal);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return arm.isInGolePosition(goal);
  }
}