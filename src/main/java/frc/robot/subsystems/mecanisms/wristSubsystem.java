/**
 * Writen by Armando Mac Beath
 * 
 * {@MÆTH}
 */

package frc.robot.subsystems.mecanisms;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.math.Functions;
import frc.robot.Constants.wristConstants;

/**
 * Creates the subsystem for the wrist
 */
public class wristSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  //subsystem instance
  private static wristSubsystem instance;

  /**
   * Motors and encoders
   */
  private final CANSparkMax motor;

  private final AbsoluteEncoder absoluteEncoder;

  //private final RelativeEncoder encoder;

  /**PID */
  private final ProfiledPIDController pidController;


  /**
   * Subsystem's constrtuctor
   */
  public wristSubsystem() {

    /**
     * Initialization for the motor
     */
    motor = new CANSparkMax(15, MotorType.kBrushless);
    motor.restoreFactoryDefaults();
    motor.setIdleMode(IdleMode.kBrake);
    motor.setInverted(true);

    /**
    * Initialization for the relative encoder
    
    //encoder = motor.getEncoder();
    encoder.setPositionConversionFactor(0.05);
*/
    /**
     * Initialization for the absolute encoder
     */
    absoluteEncoder = motor.getAbsoluteEncoder(Type.kDutyCycle);
    absoluteEncoder.setInverted(true);
    absoluteEncoder.setZeroOffset(wristConstants.offset);

    /**
     * Initialization for the PID Controller
     */
    pidController = new ProfiledPIDController(
        wristConstants.kP, 
        wristConstants.kI, 
        wristConstants.kD, 
        wristConstants.constraints);

        /**
         * Init config
         
        resetEncoders();
        motor.burnFlash();*/
  }

  @Override
  public void periodic() {

    /**
     * SHUFFLBOARD
     */
    SmartDashboard.putNumber("Wrist Position", getAbsolutePosition());

  }

  /**
   * Function to set the velocity of the wrist
   * @param velocity
   */
  public void setVelocity(double velocity){
    
    //Add a treshold
    if(velocity < 0.05 && velocity > -0.05){

      motor.stopMotor();
    } else {
      motor.set(velocity * 0.3);
    }
    
  }

  /**
   * Get the instance of the subsystem
   * @return {@wristSubsystem} singleton instance
   */
  public static wristSubsystem getInstance(){

    if(instance == null){
        instance = new wristSubsystem();
    }

    return instance;
  }

  /**
   * Function to return the wrist to rolled up position
   */
  public void setToHome(){
    if(absoluteEncoder.getPosition() > 0.06){
    pidController.setGoal(0.06);

    double pidOutput = pidController.calculate(absoluteEncoder.getPosition());
    
        motor.set(pidOutput); 
    } else {
    motor.stopMotor();
    }
  }

  /**
   * Function to get whether the wrist is in home position or not
   * @return isInHome?
   */
  public boolean isInHome(){

    if(getPosition() < 0.05){
      return true;
    } else {
      return false;
    }
  }

  /**
   * Sends the wrist to the desired goal
   * @param desiredGoal
   */
  public void setGoal(double desiredGoal){

    pidController.setGoal(desiredGoal);
    double pidOutput = pidController.calculate(absoluteEncoder.getPosition());
    
        motor.set(pidOutput); 

  }

  /**
   * Whether the arm has returned to rolledup position
   * @return is in home?
   */
  public boolean isInGolePosition(){

    boolean isInGoal;
    if(pidController.getSetpoint().velocity == 0.0 ){
        isInGoal = true;
    } else {
        isInGoal = false;
    }

    return isInGoal;
  }

  /**
   * Gets the position of the relative encoder
   * @return position of the encoder
   */
  public double getPosition(){

    return absoluteEncoder.getPosition();

  }

  /**
   * Gets the position of the absolute encoder
   * @return absolute encoder's position
   */
  public double getAbsolutePosition(){
    return absoluteEncoder.getPosition();
  }

  /**
   * Sends the position of the absolute encoder to the relative encoder
   
  public void resetEncoders(){
    encoder.setPosition(getAbsolutePosition());
  }*/


}
