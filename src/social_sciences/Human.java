package social_sciences;

public class Human {
	Boolean is_voter = false;
	static int count = 0;
	int ID;
	public Human() {
		ID = count;
		count ++;
	}

}
