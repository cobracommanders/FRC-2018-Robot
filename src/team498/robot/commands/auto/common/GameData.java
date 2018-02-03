package team498.robot.commands.auto.common;

public class GameData {
	private String gameData;
	
	public GameData(String gameData) {
		this.gameData = gameData;
	}
	
	public SwitchPosition getOurSwitchPosition() {
		if (gameData.charAt(0) == 'L') {
			return SwitchPosition.Left;
		} else if (gameData.charAt(0) == 'R') {
			return SwitchPosition.Right;
		} else {
			System.out.println("Error! Malformatted game data!");
			return SwitchPosition.Left;
		}
	}
	
	public ScalePosition getOurScalePosition() {
		if (gameData.charAt(1) == 'L') {
			return ScalePosition.Left;
		} else if (gameData.charAt(1) == 'R') {
			return ScalePosition.Right;
		} else {
			System.out.println("Error! Malformatted game data!");
			return ScalePosition.Left;
		}
	}
	
	public SwitchPosition getEnemySwitchPosition() {
		if (gameData.charAt(3) == 'L') {
			return SwitchPosition.Right;
		} else if (gameData.charAt(3) == 'R') {
			return SwitchPosition.Left;
		} else {
			System.out.println("Error! Malformatted game data!");
			return SwitchPosition.Right;
		}
	}
	
	public ScalePosition getEnemyScalePosition() {
		if (gameData.charAt(1) == 'L') {
			return ScalePosition.Right;
		} else if (gameData.charAt(1) == 'R') {
			return ScalePosition.Left;
		} else {
			System.out.println("Error! Malformatted game data!");
			return ScalePosition.Right;
		}
	}
}
