package frc.robot.subsystems.vision;

import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import oi.limelightvision.limelight.frc.LimeLight;

public class Limelight extends SubsystemBase {

  public final LimeLight limelight = new LimeLight();

  private final double cameraHeightMeters = 0;
  private final double targetHeightMeters = 0;
  private final double cameraPitchRadians = Units.degreesToRadians(0);
  private double yaw;
  private double pitch;
  private double distance;
  private boolean hasTargets;
  public final VisionSupplier visionSupplier = new VisionSupplier();

  @Override
  public void periodic() {
    if(limelight.getIsTargetFound()){
      hasTargets = true;
      yaw = limelight.getdegRotationToTarget();
      pitch = limelight.getdegVerticalToTarget();
      distance = calculateDistanceToTargetMeters(cameraHeightMeters, targetHeightMeters, cameraPitchRadians, Units.degreesToRadians(pitch));
    }
    else {
      hasTargets = false;
    }
  }

  public static double calculateDistanceToTargetMeters(double cameraHeightMeters, double targetHeightMeters,
      double cameraPitchRadians, double targetPitchRadians){
    return (targetHeightMeters - cameraHeightMeters) / Math.tan(cameraPitchRadians + targetPitchRadians);
  }

  public class VisionSupplier {

    public double getYaw() {
      return -yaw;
    }
  
    public double getPitch() {
      return pitch;
    }

    public double getDistance() {
      return distance;
    }

    public boolean hasTarget() {
      return hasTargets;
    }

  }
}
