/**
 * Writen by Armando Mac Beath
 * 
 * {@MÆTH}
 */

package frc.robot.subsystems.swerve;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.MODS.frontLeftModule;
import frc.robot.Constants.MODS.frontRightModule;
import frc.robot.Constants.MODS.rearLeftModule;
import frc.robot.Constants.MODS.rearRightModule;

public class swerveSusbsystem extends SubsystemBase {

    
    /**
     * 2D Field for Smart Dashboard visualization
     */
    private final Field2d field = new Field2d();

    //Creation for 4 equal modules whith its constants and module number
    private final SwerveModule frontLeft = new SwerveModule(frontLeftModule.constantes, 1);

    private final SwerveModule frontRight = new SwerveModule(frontRightModule.constantes, 2);

    private final SwerveModule rearLeft = new SwerveModule(rearLeftModule.constantes, 3);

    private final SwerveModule rearRight = new SwerveModule(rearRightModule.constantes, 4);

    //Creation of a gyroscope **NAVX
    private final AHRS gyro = new AHRS(SPI.Port.kMXP);
    
    //Instance of the swerve
    private static swerveSusbsystem instance;

    //Creation of the odometry *** Used in field and pathplanning
    final SwerveDriveOdometry odometry = new SwerveDriveOdometry(
         DriveConstants.kDriveKinematics, 
         getRotation2d(), 
         new SwerveModulePosition[]{
            frontLeft.getPosition(),
            frontRight.getPosition(),
            rearLeft.getPosition(),
            rearLeft.getPosition()
         });
    
         

    public swerveSusbsystem() {

       //Reset odometry when initianig the subsyste
        odometry.resetPosition(
            getRotation2d(), 
            new SwerveModulePosition[]{
               frontLeft.getPosition(),
               frontRight.getPosition(),
               rearLeft.getPosition(),
               rearLeft.getPosition()
            }, new Pose2d());
    
        
       //A thread so the code runs correctly when it builds on the robot
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                zeroHeading();
            } catch (Exception e) {
            }
        }).start();

    }

    

    //Public method for reseting the navX
    public void zeroHeading() {
        gyro.reset();
    }

   /**
    * Get Heading
    * @return the heading of the robot
    */
    public double getHeading() {
        return Math.IEEEremainder(gyro.getAngle(), 360);
    }

    /**
     * Get rotation 2D
     * @return the heading of the robot in 2D
     */
    public Rotation2d getRotation2d() {
        return Rotation2d.fromDegrees(getHeading());
    }

    /**
     * get pose
     * @return the pose of the robot in 2d
     */
    public Pose2d getPose(){
        return odometry.getPoseMeters();
    }

   /**Public method for reseting the odometry
     * @param pose insert a new Pose2d
     */
    public void resetOdometry(Pose2d pose){
        resetEncoders();
        odometry.resetPosition(getRotation2d(), 
        new SwerveModulePosition[]{
            frontLeft.getPosition(),
            frontRight.getPosition(),
            rearLeft.getPosition(),
            rearLeft.getPosition()
         }, pose);
    }

    /**Method for converting Chasssis speeds to {@SwerveModuleStates} and setting
     * them to the modules
     * @param isFieldOriented weather the driving is oriented to the field 
     * @param driveVelocity Driving Input
     * @param strafeVelocity Strafe Input
     * @param rotationVelocity Rotation Input
     */
    public void setChassisSpeeds(ChassisSpeeds chassisSpeeds){

        SwerveModuleState[] moduleStates = DriveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);

        // 6. Output each module states to wheels
        this.setModuleStates(moduleStates);
    }
   
    /**
     * Get Kinematics
     * @return the kinematics of the robor
     */
    public SwerveDriveKinematics getKinematics(){
        return DriveConstants.kDriveKinematics;
    }
    
    /**
     * get instance
     * @return the instance in use for the swerve
     */
     public static swerveSusbsystem getInstance(){
        if(instance == null){
            instance = new swerveSusbsystem();
        }
        return instance;
    }
   
    
    

    @Override
    public void periodic() {

        SmartDashboard.putNumber("ABSOLUTE MODULE 1", frontLeft.getAbsolutePosition());
        SmartDashboard.putNumber("ABSOLUTE MODULE 2", frontRight.getAbsolutePosition());
        SmartDashboard.putNumber("ABSOLUTE MODULE 3", rearLeft.getAbsolutePosition());
        SmartDashboard.putNumber("ABSOLUTE MODULE 4", rearRight.getAbsolutePosition());
        
        SmartDashboard.putNumber("RELATIVE MODULE 1", frontLeft.getTurnPosition());
        SmartDashboard.putNumber("RELATIVE MODULE 2", frontRight.getTurnPosition());
        SmartDashboard.putNumber("RELATIVE MODULE 3", rearLeft.getTurnPosition());
        SmartDashboard.putNumber("RELATIVE MODULE 4", rearRight.getTurnPosition());

        
    
        SmartDashboard.putNumber("Giro Robot", getHeading());

        //Updating the odometry of the robot
        odometry.update(getRotation2d(), 
        new SwerveModulePosition[]{
            frontLeft.getPosition(),
            frontRight.getPosition(),
            rearLeft.getPosition(),
            rearLeft.getPosition()
         });
 
        //Set the robot pose in the Field2d
        field.setRobotPose(odometry.getPoseMeters());

        //Visualization of the field
        SmartDashboard.putData("Field", field);

         
}
    
    //Public method for stoping the modules
    public void stopModules() {
        frontLeft.stop();
        frontRight.stop();
        rearLeft.stop();
        rearRight.stop();
    }

    //Public method for reseting the encoders
    public void resetEncoders(){
        frontLeft.resetEncoders();
        frontRight.resetEncoders();
        rearLeft.resetEncoders();
        rearRight.resetEncoders();

    }

   /**Public method for setting the desired states into the modules
    * @param desiredStates an array of 4 {@SwerveModuleState} one for each module 
    * in the following order:
    * [0] -> {@frontLeftModule} 
    * [1] -> {@frontRightModule}
    * [2] -> {@rearLeftModule}
    * [3] -> {@rearRightModule}
    */
    public void setModuleStates(SwerveModuleState[] desiredStates) {
        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, DriveConstants.kMaxDriveVEL);
        frontLeft.setDesiredState(desiredStates[0]);
        frontRight.setDesiredState(desiredStates[1]);
        rearLeft.setDesiredState(desiredStates[2]);
        rearRight.setDesiredState(desiredStates[3]);
    }
}
