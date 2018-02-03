package pidTypes;

import edu.wpi.first.wpilibj.PIDOutput;

public class AutoTurnOutput implements PIDOutput{

	private double lastOutput = -0.1;
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		this.lastOutput = output;
	}
	
	public double getOutput() {
		return this.lastOutput;
	}

}
