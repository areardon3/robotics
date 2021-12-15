package frc.robot.subsystems.outtake;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.common.LazyTalonSRX;

public class Shooter extends SubsystemBase {

  private final LazyTalonSRX leftMotor = new LazyTalonSRX(7); 
  private final LazyTalonSRX rightMotor = new LazyTalonSRX(8);

  private final Servo gateServo = new Servo(9);



  private void outtake(double percent){

    leftMotor.set(ControlMode.PercentOutput, MathUtil.clamp(-percent, -1, 1));
    rightMotor.set(ControlMode.PercentOutput, MathUtil.clamp(-percent, -1, 1));

    System.out.println(percent);

  }

  private void runShooter(){
    outtake(0.6);
  }

  public void disable(){
    outtake(0.0);
  }

  private void openGate(){
    gateServo.setAngle(115);
  }

  private void closeGate(){
    gateServo.setAngle(167);
  }

  public class ShootCommand extends SequentialCommandGroup {
    public ShootCommand(){
      addRequirements(Shooter.this);
      addCommands(
        new InstantCommand(Shooter.this:: openGate),
        new InstantCommand(Shooter.this:: runShooter)
      );
    }
  }

  public class DisableCommand extends SequentialCommandGroup {

    public DisableCommand(){
      addRequirements(Shooter.this);
      addCommands(
        new InstantCommand(Shooter.this:: disable),
        new InstantCommand(Shooter.this:: closeGate)
      );
    }

  }

}

