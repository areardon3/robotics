// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// are you there github
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
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

  /**
   * Using the pressed released method with the inline joystick button creation allows for
   * more flexible button bindings. Buttons dont actually need to be given a name as long as they exist.
   * 
   * For this application with such a basic shooter design/code implementation this is WAY overkill.
   * The old button binding/command system would have worked just as well given that we dont need any complex
   * sequencing or precision control. If we ran the shooter with some sort of rpm control we could use the capabilties
   * of running a sequential command group to ramp upthe shooter while also potentially running the auto targeting code.
   * In that specific example you would want the command for this to be located inside of superstructure so that it can
   * access the drivetrain.
   * 
   * Also another thing to keep in mind is that whenPressed only runs the stuff once. Which in this case works perfectly
   * because when a motor is set it will maintain that speed until a different set is called. This is the main reason why the
   * LazyTalonSRX is a thing. It slightly lowers the data usage on the CAN Bus. I would also like to try and mess with CAN Frames
   * in the coming season aswell, however I need to do more research on the subject to not mess anything up. And in the end if the
   * CAN bus usage is managable at the end of the day it wont be needed. I wont even use the Lazy classes unless they are needed.
   * 
   * In a case where you need things to run in execute you would have to bake that loop somewhere else which is defiantly possible
   * 
   * I know this stuff all seems a bit too complicated given that I wrote a thing on how the best solution is the simplest one.
   * However I have big aspirations to do more advanced things as much as possible in order for everyone to learn how to do them.
   * 
   */
  private void configureButtonBindings() {
    new JoystickButton(operator, Button.kBumperRight.value)
      .whenHeld(shooter.new ShootCommand())
      .whenReleased(shooter.new DisableCommand()
    );
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
