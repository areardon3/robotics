package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {

  private final WPI_TalonSRX frontLeft;
  private final WPI_VictorSPX backLeft;
  private final WPI_TalonSRX frontRight;
  private final WPI_VictorSPX backRight;

  private final SpeedControllerGroup leftMotors;
  private final SpeedControllerGroup rightMotors;

  private final DifferentialDrive drive;

  private final DifferentialDriveKinematics kinematics;
  private final DifferentialDriveOdometry odometry;

  private final PigeonIMU gyro;

  private final static double kTrackWidth = 0.7112; //Not actual robot value

  public Drivetrain() {

    frontLeft = new WPI_TalonSRX(1);
    backLeft = new WPI_VictorSPX(2);
    frontRight = new WPI_TalonSRX(3);
    backRight = new WPI_VictorSPX(4);

    gyro = new PigeonIMU(5);

    frontLeft.setNeutralMode(NeutralMode.Brake);
    backLeft.setNeutralMode(NeutralMode.Brake);
    frontRight.setNeutralMode(NeutralMode.Brake);
    backRight.setNeutralMode(NeutralMode.Brake);

    leftMotors = new SpeedControllerGroup(frontLeft, backLeft);
    rightMotors = new SpeedControllerGroup(frontRight, backRight);

    drive = new DifferentialDrive(leftMotors, rightMotors);

    kinematics = new DifferentialDriveKinematics(kTrackWidth);
    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(gyro.getFusedHeading()));

  }

  public void curvatureDrive(){

  }

  @Override
  public void periodic() {
    
  }
}
