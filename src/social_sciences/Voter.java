package social_sciences;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Voter extends Human {
	int policy0_inclination = 5;
	public int candidacy_inclination = 0;
	public Voter(int policy0) {
		this.policy0_inclination = policy0;
	}

	public Voter Vote(ArrayList<Voter> candidates) {
		int policy0_distance = 10;
		ArrayList<Voter> ideal_candidates = new ArrayList<Voter>();
		for(Voter candidate: candidates) {
			if(candidate.ID == this.ID) {
				return candidate;
			}
			else {
				int distance = Math.abs(this.policy0_inclination - candidate.policy0_inclination);
				if(distance == policy0_distance) {
					ideal_candidates.add(candidate);
				}
				else if(distance < policy0_distance) {
					ideal_candidates = new ArrayList<Voter>();
					ideal_candidates.add(candidate);
					policy0_distance = distance;
				}
			}
		}
		if(ideal_candidates.size() == 0) {
			return this;
		}
		else {
			int candidateNum = ThreadLocalRandom.current().nextInt(0, ideal_candidates.size());
			return ideal_candidates.get(candidateNum);
		}
	}
	
	public Boolean DecideCandidacy(int inclination) {
		this.candidacy_inclination += inclination;
		return this.candidacy_inclination > 0;
	}
}
