package frc.robot.subsystems.mecanisms;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.intakeConstants;

public class intakeSubsystem extends SubsystemBase{

    private static intakeSubsystem instance;

    private final CANSparkMax motor;


    public intakeSubsystem(){

        motor = new CANSparkMax(intakeConstants.motorID, MotorType.kBrushless);
        motor.restoreFactoryDefaults();  
        motor.setInverted(true);  
    

    }

    public static intakeSubsystem getInstance(){
        if(instance == null){
            instance = new intakeSubsystem();
        }
        return instance;
    }

    public void setPiecePickingMode(boolean isPickingCone){

        if(isPickingCone){
            motor.set(intakeConstants.pickingConeVelocity);
        } else {
            motor.set(intakeConstants.pickingCubeVelocity);
        }

    }

    public void setIdleMode(boolean isPickingCone){

        if(isPickingCone){
            motor.set(intakeConstants.idleConeVelocity);
        } else {
            motor.set(intakeConstants.idleCubeVelocity);
        }

    }


    public void setVelocity(double velocity){
        motor.set(velocity);
    }

}
