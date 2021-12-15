package frc.robot.common;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXSimCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * This class is a thin wrapper around the TalonSRX that reduces CAN bus / CPU
 * overhead by skipping duplicate set commands.
 */
public class LazyTalonSRX extends TalonSRX{

    protected double mLastSet = Double.NaN;
    protected ControlMode mLastControlMode = null;


    public LazyTalonSRX(int deviceNumber) {
        super(deviceNumber);
    }
    
    public double getLastSet(){
        return mLastSet;
    }

    @Override
    public void set(ControlMode mode, double value){
        if(value != mLastSet || mode != mLastControlMode){
            mLastSet = value;
            mLastControlMode = mode;
            super.set(mode, value);
        }
    }
}
