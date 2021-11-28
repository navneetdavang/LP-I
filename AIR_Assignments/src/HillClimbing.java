

import java.util.*;


class state {
	int arr[];
	state paraent;
	int h = 0;
	state(state s1) {
		this.arr = new int[9];
		for (int i = 0; i < s1.arr.length; i++) {
			this.arr[i] = s1.arr[i];
		}
	}
	state() {
		arr = new int[9];
	}
}


public class HillClimbing {
	state gstate, cstate, sstate;
	Scanner sc = new Scanner(System.in);
	ArrayList<state> ngb = new ArrayList<state>();
	
	HillClimbing() {
		gstate = new state();
		cstate = new state();
		sstate = new state();
	}
	
	void display(state s) {
		int k = 0;
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 3; i++) {
				System.out.print(" " + s.arr[k]);
				k++;
			}
			System.out.println();
		}
	}
	
	void input() {
		System.out.println("Enter the start state");
		for (int i = 0; i < 9; i++) {
			sstate.arr[i] = sc.nextInt();
		}
		System.out.println("Enter the goal state");
		for (int i = 0; i < 9; i++) {
			gstate.arr[i] = sc.nextInt();
		}
	}
	
	int h(state s) {
		int hvalue = 0;
		for (int i = 0; i < 9; i++) {
			if (s.arr[i] != gstate.arr[i]) {
				hvalue++;
			}
		}
		return hvalue;
	}

	int blpos(state s) {
		for (int j = 0; j < 9; j++) {
			if (s.arr[j] == 0) {
				return j;
			}
		}
		return 0;
	}
	
	void Movegen(state s) {
		int p = blpos(s);
		// int temp=0;
		ngb.clear();
		if (p % 3 != 0) {
			state n1 = new state(s);
			// temp=n1.arr[p];
			n1.arr[p] = n1.arr[p - 1];
			n1.arr[p - 1] = 0;
			n1.h = h(n1);
			ngb.add(n1);
		}
		if (p < 6) {
			state n1 = new state(s);
			// temp=n1.arr[p];
			n1.arr[p] = n1.arr[p + 3];
			n1.arr[p + 3] = 0;
			n1.h = h(n1);
			ngb.add(n1);
		}
		if (p > 2 && p < 9) {
			state n1 = new state(s);
			// temp=n1.arr[p];
			n1.arr[p] = n1.arr[p - 3];
			n1.arr[p - 3] = 0;
			n1.h = h(n1);
			ngb.add(n1);
		}
		if (p % 3 != 2) {
			state n1 = new state(s);
			// temp=n1.arr[p];
			n1.arr[p] = n1.arr[p + 1];
			n1.arr[p + 1] = 0;
			n1.h = h(n1);
			ngb.add(n1);
		}
	}
	
	int lowestscore() {
		int i = 0, min = 999;
		for (int j = 0; j < ngb.size(); j++) {
			if (min > ngb.get(j).h) {
				min = ngb.get(j).h;
				i = j;
			}
		}
		return i;
	}

	state hillclimbing() {
		int low = 0, done = 0;
		state n, nn;
		sstate.h = h(sstate);
		sstate.paraent = null;
		n = sstate;
		Movegen(n);
		low = lowestscore();
		nn = ngb.get(low);
		display(n);
		System.out.println();
		while (nn.h < n.h) {
			display(nn);
			System.out.println();
			nn.paraent = n;
			n = nn;
			Movegen(n);
			low = lowestscore();
			nn = ngb.get(low);
		}
		return nn;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HillClimbing ob = new HillClimbing();
		ob.input();
		ob.hillclimbing();
	}
}



/*
  Output:
  Enter the start state
	1 2 3
	0 4 5
	6 7 8
	Enter the goal state
	1 2 3 
	4 5 6
	7 8 0
	 1 2 3
	 0 4 5
	 6 7 8
	
	 1 2 3
	 4 0 5
	 6 7 8
	
	 1 2 3
	 4 5 0
	 6 7 8
	
	 1 2 3
	 4 5 8
	 6 7 0
 
 
  */