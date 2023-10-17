/**
 * Writen by Armando Mac Beath
 * 
 * {@MÆTH}
 */

package frc.lib.util;

public class desiredPositions {

    public double telescopicArmGoal;
    public double pivotingArmGoal;
    public double wristGoal;
    public double intakeVelocity;

    public desiredPositions(
        double telescopicArmGoal,
        double pivotingArmGoal,
        double wristGoal,
        double intakeVelocity){

            this.telescopicArmGoal = telescopicArmGoal;
            this.pivotingArmGoal = pivotingArmGoal;
            this.wristGoal = wristGoal;
            this.intakeVelocity = intakeVelocity;
    }


    
}
