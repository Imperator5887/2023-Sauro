package frc.robot.subsystems.mecanisms;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**Creates the subsystem for the solenoid */
public class solenoidSubsystem extends SubsystemBase {

  /**Creates the solenoid */
  private final Solenoid solenoide;

  /**Instance of the subsystem */
  private static solenoidSubsystem instance;

  /** Creates a new ExampleSubsystem. */
  public solenoidSubsystem() {

    /**
     * Initialization for the solenoid
     */
    solenoide = new Solenoid(PneumaticsModuleType.REVPH, 14);
    
  }

  @Override
  public void periodic() {

    /**
     * SHUFFLEBOARD
     */
    SmartDashboard.putBoolean("Solenoid State", solenoide.get());

  }

  /**
   * Gets the instance of the solenoid
   * @return {@solenoidSubsystem} singleton instance
   */
  public static solenoidSubsystem getInstance(){

    if(instance == null){
      instance = new solenoidSubsystem();
    }

    return instance;

  }

  /**
   * Sets the solenoid state
   * @param on
   */
  public void set(boolean on){
    
    solenoide.set(on);

  }

}
