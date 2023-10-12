// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.mecanisms;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.math.Functions;
import frc.robot.Constants.pivotingConstants; 

public class pivotingSubsystem extends SubsystemBase {


  private static pivotingSubsystem instance;

  private final CANSparkMax motor1;
  private final CANSparkMax motor2;
  private final CANSparkMax motor3;

  private final MotorControllerGroup motors;

  private final RelativeEncoder relativeEncoder;

  private final ProfiledPIDController pidController;


  public pivotingSubsystem() {

    motor1 = new CANSparkMax(pivotingConstants.motor1ID, MotorType.kBrushless);
    motor2 = new CANSparkMax(pivotingConstants.motor2ID, MotorType.kBrushless);
    motor3 = new CANSparkMax(pivotingConstants.motor3ID, MotorType.kBrushless);

    motor1.restoreFactoryDefaults();
    motor2.restoreFactoryDefaults();
    motor3.restoreFactoryDefaults();

    motors = new MotorControllerGroup(motor1, motor2, motor3);

  
    relativeEncoder = motor2.getEncoder();
    relativeEncoder.setPositionConversionFactor(pivotingConstants.motorSprocketConversionFactor * (2 * Math.PI));
    relativeEncoder.setPosition(0);


    pidController = new ProfiledPIDController(
      pivotingConstants.kP_Pivot, 
      pivotingConstants.kI_Pivot, 
      pivotingConstants.kD_Pivot, 
      pivotingConstants.constraints);

    pidController.enableContinuousInput(-Math.PI, Math.PI);

  }

  
  public static pivotingSubsystem getInstance(){
    
    if(instance == null){
      instance = new pivotingSubsystem();
    }

    return instance;
  }
  

  public void stopMotors(){
    motors.stopMotor();
  }

  public void setDesiredPosition(double goal){
        
    double pidOutput = pidController.calculate(relativeEncoder.getPosition());
    pidController.setGoal(goal);  
   
    SmartDashboard.putNumber("PID output", pidOutput);

    motors.set(pidOutput); 
}

  public double getAppliedOutput(){
    return motor1.getAppliedOutput();
  }

  

  public double getRelativeEncoderPosition(){

    return relativeEncoder.getPosition();

  }
  
  public void setVelocity(double velocity){

    motors.set(velocity * 0.5);

    SmartDashboard.putNumber("Velocity", velocity);
  }

  public boolean isInGolePosition(){

    boolean isHome;
    if(pidController.getSetpoint().velocity == 0.0 ){
        isHome = true;
    } else {
        isHome = false;
    }

    return isHome;
  }

  @Override
  public void periodic() {

    SmartDashboard.putNumber("Posicion Pivoteo", getRelativeEncoderPosition());

    
    // This method will be called once per scheduler run
  }

  
}
