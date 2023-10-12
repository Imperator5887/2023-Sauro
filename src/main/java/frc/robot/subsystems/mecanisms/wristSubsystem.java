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

public class wristSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  private static wristSubsystem instance;

  private final CANSparkMax motor;

  private final AbsoluteEncoder absoluteEncoder;

  private final RelativeEncoder encoder;

  private final ProfiledPIDController pidController;



  public wristSubsystem() {

    motor = new CANSparkMax(15, MotorType.kBrushless);
    motor.restoreFactoryDefaults();
    motor.setIdleMode(IdleMode.kBrake);
    motor.setInverted(true);

    encoder = motor.getEncoder();
    encoder.setPositionConversionFactor(0.05);


    absoluteEncoder = motor.getAbsoluteEncoder(Type.kDutyCycle);
    absoluteEncoder.setInverted(true);
    absoluteEncoder.setZeroOffset(wristConstants.offset);

    
    pidController = new ProfiledPIDController(
        wristConstants.kP, 
        wristConstants.kI, 
        wristConstants.kD, 
        wristConstants.constraints);

        resetEncoders();
        motor.burnFlash();
  }

  @Override
  public void periodic() {

    SmartDashboard.putNumber("Wrist ABSOLUTE Position", getAbsolutePosition());
    SmartDashboard.putNumber("Wrist RELATIVE Position", getPosition());

    SmartDashboard.putNumber("OUTPUT WRIST", motor.getAppliedOutput());

  }

  public void setVelocity(double velocity){
    if(velocity < 0.08 && velocity > -0.08){

      motor.stopMotor();
    } else {
      motor.set(velocity * 0.5);
    }
    
  }

  public static wristSubsystem getInstance(){

    if(instance == null){
        instance = new wristSubsystem();
    }

    return instance;
  }


  public void setToHome(){
    if(absoluteEncoder.getPosition() > 0.06){
    pidController.setGoal(0.06);

    double pidOutput = pidController.calculate(absoluteEncoder.getPosition());
    
        motor.set(pidOutput); 
    } else {
    motor.stopMotor();
    }
  }

  public boolean isInHome(){

    if(getPosition() < 0.05){
      return true;
    } else {
      return false;
    }
  }

  public void setGoal(double goal){

    pidController.setGoal(goal);
    double pidOutput = pidController.calculate(encoder.getPosition());
    
        motor.set(pidOutput); 

  }

  public double getPosition(){

    return encoder.getPosition();

  }

  public double getAbsolutePosition(){
    return absoluteEncoder.getPosition();
  }

  public void resetEncoders(){
    encoder.setPosition(getAbsolutePosition());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
