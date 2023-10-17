/**
 * Writen by Armando Mac Beath
 * 
 * {@MÆTH}
 */

package frc.robot.subsystems.mecanisms;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.intakeConstants;

/**CREATES THE SUBSYSTEM OF THE INTAKE */
public class intakeSubsystem extends SubsystemBase{

    /**Instance of the system */
    private static intakeSubsystem instance;

    /**Motor */
    private final CANSparkMax motor;

    private final RelativeEncoder encoder;

    /**Constructor of the system */
    public intakeSubsystem(){

        /**Initialization of the motor */
        motor = new CANSparkMax(intakeConstants.motorID, MotorType.kBrushless);
        motor.restoreFactoryDefaults();  
        motor.setInverted(true); 
        
        encoder = motor.getEncoder();
        encoder.setPositionConversionFactor(0.2);
    

    }

    /**
     * Gets the instance of the subsystem
     * @return {@intakeSubsystem} singleton instance
     */
    public static intakeSubsystem getInstance(){
        if(instance == null){
            instance = new intakeSubsystem();
        }
        return instance;
    }

    /**
     * Sets the velocity for each picking mode (cone [true] // cube [false])
     * @param isPickingCone 
     */
    public void setPiecePickingMode(boolean isPickingCone){

        if(isPickingCone){
            motor.set(intakeConstants.pickingConeVelocity);
        } else {
            motor.set(intakeConstants.pickingCubeVelocity);
        }

    }


    /**
     * Sets the Idle mode to use afterpicking a certain piece
     * @param isPickingCone
     */
    public void setIdleMode(boolean isPickingCone){

        if(isPickingCone){
            motor.set(intakeConstants.idleConeVelocity);
        } else {
            motor.set(intakeConstants.idleCubeVelocity);
        }

    }

    /**
     * Sets the velocity for the motor
     * @param velocity
     */
    public void setVelocity(double velocity){
        motor.set(velocity);
    }

    /**
     * Gets the position of the roller
     * @return encoder's position
     */
    public double getPosition(){
       return encoder.getPosition();
    }

    /**
     * Whether has it pick the piece or no
     * @return has pick?
     
    public boolean hasPick(boolean isPickingCone, boolean isInAuto){
        

        switch

    }

    /**
     * stops the motor 
     */
    public void stop(){
        motor.stopMotor();
    }
}
