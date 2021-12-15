package frc.robot.subsystems;

import java.util.function.Function;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.controlpanel.Pivot;
import frc.robot.subsystems.controlpanel.Spin;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.hopper.Agitator;
import frc.robot.subsystems.hopper.Gate;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.lift.Lift;
import frc.robot.subsystems.outtake.Shooter;
import frc.robot.subsystems.vision.Limelight.VisionSupplier;

public class SuperStructure extends SubsystemBase {

  /** All Subsystems */
  public final Agitator agitator;
  public final Drivetrain drivetrain;
  public final Gate gate;
  public final Lift lift;
  public final VisionSupplier visionSupplier;
  public final Pivot pivot;
  public final Shooter shooter;
  public final Spin spin;
  public final Intake intake;

  public SuperStructure(
    Agitator agitator,
    Drivetrain drivetrain,
    Gate gate,
    Lift lift,
    VisionSupplier visionSupplier,
    Pivot pivot,
    Shooter shooter,
    Spin spin,
    Intake intake
  ) {
    this.agitator = agitator;
    this.drivetrain = drivetrain;
    this.gate = gate;
    this.lift = lift;
    this.visionSupplier = visionSupplier;
    this.pivot = pivot;
    this.shooter = shooter;
    this.spin = spin;
    this.intake = intake;
  }

  public void disable(){
    this.shooter.disable();
    this.intake.disable();
  }

  public class IdleCommand extends CommandBase {
    
    private final Function<Hand, Double> trigger;

    public IdleCommand(Function<Hand, Double> trigger){
      addRequirements(SuperStructure.this);
      this.trigger = trigger;
    }
    
    @Override
    public void initialize() {
      disable();
    }

    @Override
    public void execute() {
      if(trigger.apply(Hand.kRight) > 0.1){
        shooter.outtake(0.9);
      }
      else{
        shooter.disable();
      }

      if (trigger.apply(Hand.kLeft) > 0.1){
        intake.intake(trigger.apply(Hand.kLeft) * 0.5);
      }
      else{
        intake.disable();
      }
    }
  }


  @Override
  public void periodic() {}
}
