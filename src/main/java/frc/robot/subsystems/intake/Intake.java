package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.common.LazyTalonSRX;

public class Intake extends SubsystemBase {

  private final LazyTalonSRX motor = new LazyTalonSRX(5);

  public void intake(double percent){

    motor.set(ControlMode.PercentOutput, MathUtil.clamp(percent, -1, 1));
    System.out.println("Intake: " + Double.toString(percent));
  }

  public void disable(){
    intake(0.0);
  }

  public void runIntake() {
    motor.set(0.6);
    
  }
}
