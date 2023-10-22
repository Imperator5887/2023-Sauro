package frc.robot;

import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.lib.util.SwerveModuleConstants;
import frc.lib.util.limelightOffsets;

public class Constants {

    public static final class ModuleConstants {

        //Diameter of the Wheel in meters
        public static final double kWheelDiameterMeters = Units.inchesToMeters(4);
       
        //Gear ratio of the drive motor
        public static final double kDriveMotorGearRatio = 1 / 7.13;
        
        //Gear ratio of the turning motor
        public static final double kTurningMotorGearRatio = 1 /  13.71;
        
        // Drive position conversion factor from rotation to meters
        public static final double kDriveEncoderRot2Meter = kDriveMotorGearRatio * Math.PI * kWheelDiameterMeters;
       
        // Turning position conversion factor from rotation to radias
        public static final double kTurningEncoderRot2Rad = kTurningMotorGearRatio * 2 * Math.PI;
       
        // Drive velocity conversion factor from RPM to M/S
        public static final double kDriveEncoderRPM2MeterPerSec = kDriveEncoderRot2Meter / 60;
       
        // Turning velocity conversion factor from RPM to Rads/Sec
        public static final double kTurningEncoderRPM2RadPerSec = kTurningEncoderRot2Rad / 60;
       
        //P constant for the turn motor
        public static final double kPTurning = 0.247;
    }

    public static final class DriveConstants {

         /**
         * 
         *             Trackwidth
         *     --------------------------
         *     |                        |
         *     |                        |
         *     |                        | |
         *     |                        | |WHEELBASE
         *     |        midpoint        | | 
         *     |                        |
         *     |                        |
         *     |                        |
         *     |                        |
         *     --------------------------
         */


        // Distance between right and left wheels
        public static final double kTrackWidth = Units.inchesToMeters(19.25);
        
        // Distance between front and back wheels
        public static final double kWheelBase = Units.inchesToMeters(19.25);
       
        /**
        * Create the kinematics of the swerve
        */
        public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
                new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(kWheelBase / 2, kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, kTrackWidth / 2));

        public static final double kMaxDriveVEL = 6.5;
        public static final double kMaxRotVEL = 3 * 2 * Math.PI;

        public static final double kDriveLimiter = kMaxDriveVEL / 5;
        public static final double kRotationLimiter = //
                kMaxRotVEL / 5;
        public static final double kDriveAccelerationLimiter = 5;
        public static final double kRotationAccelerationLimiter = 5;
    }

    public static class MODS {
  

        public static final class frontLeftModule {
                  
            public static int driveMotorID = 1;
            public static int turningMotorID = 2;
            public static boolean driveMotorInverted = true;
            public static boolean turningMotorInverted = false;
            public static double absoluteEncoderOffsetRad = 5.19;
            public static boolean absoluteEncoderReversed = true;
                       
            public static final SwerveModuleConstants constantes = 
            new SwerveModuleConstants(driveMotorID, turningMotorID, driveMotorInverted, 
            turningMotorInverted, absoluteEncoderOffsetRad, absoluteEncoderReversed);
                  
         }
                  
        public static final class frontRightModule {
                  
            public static int driveMotorID = 3;
            public static int turningMotorID = 4;
            public static boolean driveMotorInverted = true;
            public static boolean turningMotorInverted = false;
            public static double absoluteEncoderOffsetRad = 5.67;
            public static boolean absoluteEncoderReversed = true;
                      
                  
            public static final SwerveModuleConstants constantes = 
            new SwerveModuleConstants(driveMotorID, turningMotorID, driveMotorInverted, 
            turningMotorInverted, absoluteEncoderOffsetRad, absoluteEncoderReversed);
            
                  
        }
                  
        public static final class rearLeftModule {
                  
            public static int driveMotorID = 5;
            public static int turningMotorID = 6;
            public static boolean driveMotorInverted = true;
            public static boolean turningMotorInverted = false;
            public static int absoluteEncoderID = 3;
            public static double absoluteEncoderOffsetRad = 2.73;
            public static boolean absoluteEncoderReversed = true;
                  
            public static final SwerveModuleConstants constantes = 
            new SwerveModuleConstants(driveMotorID, turningMotorID, driveMotorInverted, 
            turningMotorInverted, absoluteEncoderOffsetRad, absoluteEncoderReversed);
            
                  
        }
                  
        public static final class rearRightModule {
                  
            public static int driveMotorID = 7;
            public static int turningMotorID = 8;
            public static boolean driveMotorInverted = true;
            public static boolean turningMotorInverted = false;
            public static int absoluteEncoderID =  4;
            public static double absoluteEncoderOffsetRad = 2.2;
            public static boolean absoluteEncoderReversed = true; 
                  
            public static final SwerveModuleConstants constantes = 
            new SwerveModuleConstants(driveMotorID, turningMotorID, driveMotorInverted, 
            turningMotorInverted, absoluteEncoderOffsetRad, absoluteEncoderReversed);
            
                  
        }
    }
    public static final class AutoConstants {
        public static final double kMaxSpeedMetersPerSecond = DriveConstants.kMaxDriveVEL / 4;
        public static final double kMaxAngularSpeedRadiansPerSecond = //
                DriveConstants.kMaxRotVEL / 10;
        public static final double kMaxAccelerationMetersPerSecondSquared = 3;
        public static final double kMaxAngularAccelerationRadiansPerSecondSquared = Math.PI / 4;
        
        
       
    }

    public static final class limelightConstants {


    /**
     * PID constants for the autoalign
     */
     public static final double kPdrive = 0.08;
     public static final double kIdrive = 0;
     public static final double kDdrive = 0;

     public static final double kPstrafe = 0.04;
     public static final double kIstrafe = 0;
     public static final double kDstrafe = 0;

     public static final double kProtation = 0.01;
     public static final double kIrotation = 0;
     public static final double kDrotation = 0;

        public static final class aprilTag{

            public static double driveOffset = 5.4;
            public static double strafeOffset = -1;
            public static double rotationOffset = 17;

            public static final limelightOffsets offsets =  
        new limelightOffsets(driveOffset, strafeOffset, rotationOffset);

        }

        public static final class reflectiveTape{

            public static double driveOffset = 0.08;
            public static double strafeOffset = -5.16;
            public static double rotationOffset = 17;

            public static final limelightOffsets offsets =  
        new limelightOffsets(driveOffset, strafeOffset, rotationOffset);

        }

        
    }

   

    public static final class OIConstants {
        public static final int kDriverControllerPort = 0;
        public static final int kPlacerControllerPort = 1;

        public static final int kDriverYAxis = 1;
        public static final int kDriverXAxis = 0;
        public static final int kDriverRotAxis = 4;
        public static final int kDriverFieldOrientedButtonIdx = 1;

        public static final double kDeadband = 0.05;
    }

    public static final class Shuffleboard {

        public static final ShuffleboardTab kShuffleboardTab = edu.wpi.first.wpilibj.shuffleboard.Shuffleboard.getTab("Imperator");
    
        }


    public static class telescopicConstants{
           
        public static int motorID = 12;
       
        
       
        public static int boreEncoderID = 8;

        public static double kP_Telescopic = 0;
        public static double kI_Telescopic = 0;
        public static double kD_Telescopic = 0;

        public static final double gearRatio = 1/15;
        public static final double tamboDiameter = Units.inchesToMeters(1.25);
        public static final double tamboPerimeter = tamboDiameter * Math.PI;

        public static double kTelescopicEncoder2Meters = (gearRatio * Math.PI * tamboDiameter) /80
        ;

        public static double maxVelocity = 5;
        public static double maxAcceleration = 5;

        public static TrapezoidProfile.Constraints constraints = new TrapezoidProfile.Constraints(maxVelocity, maxAcceleration);

    }
    

    public static final class pivotingConstants{

        public static final int buttonDesiredPosition1 = 2;
        public static final int buttonHomePosition = 1;
    
    
        public static final int motor1ID = 9;
        public static final int motor2ID = 10;
        public static final int motor3ID = 11;
    
    
        public static final double motorSprocketConversionFactor = 1 / 288.75;
        
        public static final double kP_Pivot = 0.75;
        public static final double kI_Pivot = 0;
        public static final double kD_Pivot = 0;
    
        
    
        public static final double maxVelocity = 24;
        public static final double maxAcceleration = 21;
        public static final TrapezoidProfile.Constraints constraints = 
                        new TrapezoidProfile.Constraints(maxVelocity, maxAcceleration);
                    
    
        public static final double pickingPose = 1.45; //1.52
        public static final double homePosition = 0.; // 0.02
        public static final double placePosition = 0.55;
        


    }


    public static class intakeConstants{

        public static int motorID = 14;

        public static double pickingConeVelocity = 0.5;
        public static double pickingCubeVelocity = -0.4;

        public static double idleConeVelocity = 0.05;
        public static double idleCubeVelocity = -0.05;


    }

    public static class wristConstants{
        public static int motorID = 15;

        public static double kP = 0.8;
        public static double kI = 0;
        public static double kD = 0.12;

        public static double offset = 0.22;

        public static double maxVelocity = 25;
        public static double maxAcceleration = 25;

        public static double maxPosition = 0.03;

        public static final double positionConversionFactor = 0;
        
        public static TrapezoidProfile.Constraints constraints =
                      new TrapezoidProfile.Constraints(maxVelocity, maxAcceleration);

        public static final double pickingPose = 0.40;
        public static final double cubeHighPose = 0.33;
        public static final double cubeMidPose = 0.43;
        public static final double homePose = 0.08;

    }

    
}
