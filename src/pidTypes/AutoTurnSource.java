package pidTypes;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;


public class AutoTurnSource implements PIDSource{
private PIDSourceType pidSourceType;
	private team498.robot.subsystems.Gyro gyro;
	
	public AutoTurnSource() {
		this.gyro = team498.robot.subsystems.Gyro.getGyro();
		
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		this.pidSourceType = pidSource;
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return this.pidSourceType;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return gyro.getAngle();
	}

}
