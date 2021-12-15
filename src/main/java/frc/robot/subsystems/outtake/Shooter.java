package frc.robot.subsystems.outtake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;
import frc.robot.common.LazyTalonSRX;

public class Shooter extends SubsystemBase {

  private final LazyTalonSRX leftMotor = new WPI_TalonSRX(7); 
  private final LazyTalonSRX rightMotor = new WPI_TalonSRX(8);

  private final Servo gateServo = new Servo(9);



  public void outtake(double percent){

    leftMotor.set(ControlMode.PercentOutput, MathUtil.clamp(-percent, -1, 1));
    rightMotor.set(ControlMode.PercentOutput, MathUtil.clamp(-percent, -1, 1));

    System.out.println("Outtake: " + Double.toString(percent));

    gateServo.setAngle(167); //closed gate angle

  }

  public void runShooter(double pSpeed){

    gateServo.setAngle(115);
    
    leftMotor.set(pSpeed);
    rightMotor.set(pSpeed); 

  }

  public void openGate() {

    gateServo.setAngle(115); //open gate angle 

  }
  public void disable(){

    outtake(0.0);

  }

  public Shooter() {

    runShooter(0.6);

  }
}

