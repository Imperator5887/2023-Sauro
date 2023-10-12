package frc.robot.subsystems.mecanisms;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class solenoidSubsystem extends SubsystemBase {

  private final Solenoid solenoide;

  private static solenoidSubsystem instance;

  /** Creates a new ExampleSubsystem. */
  public solenoidSubsystem() {

    solenoide = new Solenoid(PneumaticsModuleType.REVPH, 14);
    
  }

  @Override
  public void periodic() {

    SmartDashboard.putBoolean("Solenoid State", solenoide.get());

  }

  public static solenoidSubsystem getInstance(){

    if(instance == null){
      instance = new solenoidSubsystem();
    }

    return instance;

  }

  public void set(boolean on){
    
    solenoide.set(on);

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
