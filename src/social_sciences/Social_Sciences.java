package social_sciences;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Social_Sciences {

	public Social_Sciences() {
	}

	public static void main(String[] args) throws IOException {
		int size = 500;
		Election election = new Election();
		for(int i = 0; i < size; i++) {
			int policy = ThreadLocalRandom.current().nextInt(0, 11);
			Voter voter = new Voter(policy);
			policy = ThreadLocalRandom.current().nextInt(-99, 2);
			Boolean candidacy = voter.DecideCandidacy(policy);
			election.AddVoter(voter, candidacy);
		}
		for(int j = 0; j < 5000; j++) {
			System.out.print("Round ");
			System.out.println(j);
			Voter elected = election.Start();
			election.UpdateCandidacyInclination(elected);
			System.out.print("Elected candidate: ");
			System.out.println(elected.ID);
			System.in.read();
		}
	}

}
