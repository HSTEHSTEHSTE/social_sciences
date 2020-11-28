package social_sciences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Election {
	ArrayList<Voter> voters = new ArrayList<Voter>();
	ArrayList<Voter> candidates = new ArrayList<Voter>();
	int voter_number = 0;
	int candidate_number = 0;
	ArrayList<Integer> vote_count = new ArrayList<Integer>(Collections.nCopies(candidate_number, 0));
	Voter base_voter = new Voter(5);
	public Election() {
		
	}
	public void AddVoter(Voter voter, Boolean is_candidate) {
		voters.add(voter);
		voter_number++;
		if(is_candidate) {
			candidate_number++;
			candidates.add(voter);
		}
	}
	public Voter Start() {
		if(voter_number == 0) {
			return base_voter;
		}
		else if(candidate_number == 0) {
			int candidateNum = ThreadLocalRandom.current().nextInt(0, voter_number);
			return voters.get(candidateNum);
		}
		else {
			vote_count = new ArrayList<Integer>(Collections.nCopies(candidate_number, 0));
			for(Voter voter:voters) {
				Voter candidate = voter.Vote(candidates);
				vote_count.set(candidates.indexOf(candidate), vote_count.get(candidates.indexOf(candidate)) + 1);
			}
			int max_count = 0;
			Voter final_candidate = base_voter;
			for(Voter candidate:candidates) {
				System.out.print("Candidate ");
				System.out.print(candidate.ID);
				System.out.print(" with policy ");
				System.out.print(candidate.policy0_inclination);
				System.out.print(" candidacy inclination ");
				System.out.print(candidate.candidacy_inclination);
				System.out.print(" received votes: ");
				System.out.println(vote_count.get(candidates.indexOf(candidate)));
				if(vote_count.get(candidates.indexOf(candidate)) > max_count) {
					final_candidate = candidate;
					max_count = vote_count.get(candidates.indexOf(candidate));
				}
			}
			return final_candidate;
		}
	}
	public void UpdateCandidacyInclination(Voter elected) {
		for(Voter voter:voters) {
			if(candidates.contains(voter)) {
				voter.candidacy_inclination += -2;
				voter.candidacy_inclination += (double) this.vote_count.get(candidates.indexOf(voter)) / (double) this.voter_number * 10.0;
			}
			voter.candidacy_inclination -= 5;
			if(voter.ID == elected.ID) {
				voter.candidacy_inclination += 10;
			}
			voter.candidacy_inclination += Math.abs(Math.abs(voter.policy0_inclination - elected.policy0_inclination) - 3);
			Boolean candidacy = voter.DecideCandidacy(0);
			if(candidacy) {
				if(!candidates.contains(voter)) {
					candidate_number++;
					candidates.add(voter);
				}
			}
			else{
				if(candidates.contains(voter)) {
					candidate_number--;
					candidates.remove(voter);
				}
			}
		}
	}
}
