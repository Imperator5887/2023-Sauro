/**
 * Writen by Armando Mac Beath
 * 
 * {@MÆTH}
 */

package frc.robot.subsystems.mecanisms;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.pivotingConstants; 


/**CREATES THE SUBSYSTEM FOR THE PIVOTING OF THE ARM */
public class pivotingSubsystem extends SubsystemBase {

  /**Instance of the system */
  private static pivotingSubsystem instance;

  /**Motors and encoders */
  private final CANSparkMax motor1;
  private final CANSparkMax motor2;
  private final CANSparkMax motor3;

  private final MotorControllerGroup motors;

  private final RelativeEncoder relativeEncoder;

  /**PID Controller */
  private final ProfiledPIDController pidController;

  /**Constructor of the system */
  public pivotingSubsystem() {

    /**
     * Initialization of the motors 
     */
    motor1 = new CANSparkMax(pivotingConstants.motor1ID, MotorType.kBrushless);
    motor2 = new CANSparkMax(pivotingConstants.motor2ID, MotorType.kBrushless);
    motor3 = new CANSparkMax(pivotingConstants.motor3ID, MotorType.kBrushless);

    motor1.restoreFactoryDefaults();
    motor2.restoreFactoryDefaults();
    motor3.restoreFactoryDefaults();

    motors = new MotorControllerGroup(motor1, motor2, motor3);

    /**
     * Initialization of the encoder
     */
    relativeEncoder = motor2.getEncoder();
    relativeEncoder.setPositionConversionFactor(pivotingConstants.motorSprocketConversionFactor * (2 * Math.PI));
    relativeEncoder.setPosition(0);

    /**
     * Initialization of the PID Controller
     */
    pidController = new ProfiledPIDController(
      pivotingConstants.kP_Pivot, 
      pivotingConstants.kI_Pivot, 
      pivotingConstants.kD_Pivot, 
      pivotingConstants.constraints);

    pidController.enableContinuousInput(-Math.PI, Math.PI);

  }

  /**
   * Gets the instance of the systme
   * @return {@pivotingSubsystem} singleton instance
   */
  public static pivotingSubsystem getInstance(){
    
    if(instance == null){
      instance = new pivotingSubsystem();
    }

    return instance;
  }
  
  /**
   * Stops all motors
   */
  public void stopMotors(){
    motors.stopMotor();
  }

  /**
   * Pivots the arm to a desired position through PID
   * @param goal
   */
  public void setDesiredPosition(double goal){
        
    double pidOutput = pidController.calculate(relativeEncoder.getPosition());
    pidController.setGoal(goal);  
   
    motors.set(pidOutput); 
  }

  /**
   * Gets the applied output of the motor 1
   * @return {@motor1} applied output
   */
  public double getAppliedOutput(){
    return motor1.getAppliedOutput();
  }

  
  /**
   * Gets the position of the encoder
   * @return the position of the pivot
   */
  public double getPosition(){

    return relativeEncoder.getPosition();

  }
  
  /**
   * Sets the pivoting velocity
   * @param velocity
   */
  public void setVelocity(double velocity){

    motors.set(velocity * 0.5);

    SmartDashboard.putNumber("PIVOTING velocity", velocity);
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

  @Override
  public void periodic() {

    /**SHUFFLEBOARD */
    SmartDashboard.putNumber("Posicion Pivoteo", getPosition());

  }

  
}
