/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Utility;

/**
 * An example command.  You can replace me with your own command.
 */
public class TeleopDrive extends Command {
  public TeleopDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  private double horizontalSpeed;
  private double verticalSpeed;
  private double rotation;
  
  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    processInputs();
    Robot.driveTrain.manualDrive(horizontalSpeed, verticalSpeed, rotation);
  }
  private void processInputs()
  {
    horizontalSpeed = Utility.transform(Robot.OI.joystick.getX(Hand.kLeft));
    verticalSpeed = -Utility.transform(Robot.OI.joystick.getY(Hand.kLeft));
    rotation = Utility.transform(Robot.OI.joystick.getX(Hand.kRight));
    if (isUnderRotationThreshold(rotation)) 
    {
      rotation = 0;
    }
    ApplyBrakeIfBPressed();
  }
  private boolean isUnderRotationThreshold(double rotation)
  {
    return Math.abs(rotation) < 0.075;

  }
  public void ApplyBrakeIfBPressed (){
    if (Robot.OI.joystick.getBButton())
    {
      horizontalSpeed = 0;
      verticalSpeed = 0;
   }
  }
  
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
