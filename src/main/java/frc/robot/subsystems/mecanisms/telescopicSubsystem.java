package frc.robot.subsystems.mecanisms;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMaxLowLevel.PeriodicFrame;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.telescopicConstants;

/**Creates the subsystem for the telescopic arm */
public class telescopicSubsystem extends SubsystemBase{
    
    /**Instance of the subsystem */
    private static telescopicSubsystem instance;

    /**Motor and encoder */
    private final CANSparkMax motor;

    private final RelativeEncoder relativeEncoder;
    

    /**Subsystem's constructor */
    public telescopicSubsystem(){

        /**
         * Initialization for the motor
         */
        motor = new CANSparkMax(telescopicConstants.motorID, MotorType.kBrushless);
        motor.restoreFactoryDefaults();
        motor.setPeriodicFramePeriod(PeriodicFrame.kStatus5, 20);
        motor.setSmartCurrentLimit(35);

        /**
         * Initialization for the relative encoder
         */
        relativeEncoder = motor.getEncoder();
        relativeEncoder.setPositionConversionFactor(telescopicConstants.kTelescopicEncoder2Meters);
        relativeEncoder.setPosition(0);

        /**
         * Initialization for the PID Controller
         */
       
            
        resetEncoders();
    }


    /**
     * Gets the instance of the subsystem
     * @return {@telescopicSubsystem} singleton instance
     */
    public static telescopicSubsystem getInstance(){
        if(instance == null){
            instance = new telescopicSubsystem();
        }

        return instance;
    }

    /**
     * Gets the position of the relative encoder
     * @return relatve encoder's position
     */
    public double getPosition(){
        return relativeEncoder.getPosition();
    }

    /**
     * Sets the encoder's position to 0
     */
    public void resetEncoders(){

        relativeEncoder.setPosition(0);
    }

    @Override
    public void periodic(){
        /**
         * SHUFFLEBOARD
         */
        SmartDashboard.putNumber("Telescopic Position", getPosition());
    }



    /**
     * Sends the telescopic to a desired position through PID
     * @param goal
     */
    public void setGoal(double goal){

        if(getPosition() < goal){
            motor.set(0.5);
        }else {
            motor.set(0);
        }
    }

    /**
     * Whether the arm has returnes to its to rolled up position
     * @return
     */
    public boolean isInGolePosition(double goal){

        boolean isInGoal;

        if(getPosition() > goal){
            isInGoal = true;
        }else {
            isInGoal = false;
        }

        return isInGoal;
    } 

    public void sendToHome(){

        if(getPosition() > 0.07){
            motor.set(-0.5);
        }else {
            motor.set(0);
        }

    }

    /**
     * Stops the motor
     */
    public void stopMotor(){

        motor.set(0);
    }

    /**
     * Sets the velocity of the motor
     * @param velocity
     */
    public void setVelocity(double velocity){
        motor.set(velocity * 0.8);
    }

    

    
}