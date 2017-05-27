package model.gamedata.user;

import java.time.LocalDate;
import java.util.Date;

public class UserStats {
	
	private int userID;
//	private int conditionID;
//	private int mapID;
	private LocalDate date;
	
	public UserStats() {
		
	}
	
	public void setId(int id) {
		userID = id;
	}
	
	public void setDate(LocalDate day) {
		date = day;
	}

}
