// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// are you there github
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.SuperStructure;
import frc.robot.subsystems.controlpanel.Pivot;
import frc.robot.subsystems.controlpanel.Spin;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.hopper.Agitator;
import frc.robot.subsystems.hopper.Gate;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.lift.Lift;
import frc.robot.subsystems.outtake.Shooter;
import frc.robot.subsystems.vision.Limelight;


/**
 * This is where controller inputs are assigned to robot actions
 */
public class RobotContainer {

  //HIDs
  public final XboxController driver = new XboxController(0);
  public final XboxController operator = new XboxController(1);

  //Subsystems
  public final Agitator agitator = new Agitator();
  public final Drivetrain drivetrain = new Drivetrain();
  public final Gate gate = new Gate();
  public final Lift lift = new Lift();
  public final Limelight limelight = new Limelight();
  public final Pivot pivot = new Pivot();
  public final Shooter shooter = new Shooter();
  public final Spin spin = new Spin();
  public final Intake intake = new Intake();

  //Superstructure
  public final SuperStructure superStructure = new SuperStructure(
      agitator,
      drivetrain,
      gate,
      lift,
      limelight.visionSupplier,
      pivot,
      shooter,
      spin,
      intake
  );

  public RobotContainer() {
    configureButtonBindings();

    superStructure.setDefaultCommand(superStructure.new IdleCommand(operator::getTriggerAxis));
  }

  private void configureButtonBindings() {

    JoystickButton mRightBumper = new JoystickButton(operator, 6);
    
    mRightBumper.whenHeld(new Shooter.runShooter (shooter, Constants.OuttakeSpeed));

  }

  public Command getAutonomousCommand() {
    return null;
  }
}
