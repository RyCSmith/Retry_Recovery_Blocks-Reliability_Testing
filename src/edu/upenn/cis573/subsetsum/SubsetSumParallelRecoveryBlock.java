package edu.upenn.cis573.subsetsum;

public class SubsetSumParallelRecoveryBlock extends SubsetSumSolver {

	/*
	 * Use a Parallel Recovery Block to implement this method.
	 * Use the SubsetSumSolverBF.solve and SubsetSumSolverDP.solve methods in your Parallel Recovery Block.
	 * Use the acceptance test you wrote in SubsetSumSolver
	 */	
	public boolean[] solve(int[] A, int target) throws ValidSolutionNotFoundException {
		
		//make new copies of the array to avoid modifying something that may be used elsewhere
		int[] copyOne = new int[A.length];
		System.arraycopy(A, 0, copyOne, 0, A.length);
		int[] copyTwo = new int[A.length];
		System.arraycopy(A, 0, copyTwo, 0, A.length);
		
		//create and begin threads
		DPThread dpThread = new DPThread(copyOne, target);
		BFThread bfThread = new BFThread(copyTwo, target);
		dpThread.run();
		bfThread.run();
		
		//join dpThread (since it will finish more quickly than bfThread) when it completes check the value, return if accepted
		//if the value is not accepted, wait for bfThread to finish, return true if accepted and throw and exception otherwise
		try {
			dpThread.join();
			if (accept(dpThread.getResult(), A, target))
					return dpThread.getResult();
			bfThread.join();
			if (accept(bfThread.getResult(), A, target))
				return bfThread.getResult();
		} catch (InterruptedException e) {
			//this catch block is required by Java (since i didn't want to change method signature with throws declaration)
		}
		throw new ValidSolutionNotFoundException();
	}

	/**
	 * Thread to run the DP algorithm.
	 */
	public class DPThread extends Thread {
		int[] A;
		int target;
		boolean[] resultOutput;
		
		public DPThread(int[] A, int target) {
			this.A = A;
			this.target = target;
		}
		public void run() {
			boolean[] result = SubsetSumImplementations.solveDP(A, target);
			resultOutput = result; 
		}
		/**
		 * Returns the result of the algorithm so it can be checked for validity.
		 * @return
		 */
		public boolean[] getResult() {
			return resultOutput;
		}
	}
	
	/**
	 * Thread to run the BF algorithm.
	 */
	public class BFThread extends Thread {
		int[] A;
		int target;
		boolean[] resultOutput;
		public BFThread(int[] A, int target) {
			this.A = A;
			this.target = target;
		}
		public void run() {
			boolean[] result = SubsetSumImplementations.solveBF(A, target);
			resultOutput = result; 
		}
		/**
		 * Returns the result of the algorithm so it can be checked for validity.
		 * @return
		 */
		public boolean[] getResult() {
			return resultOutput;
		}
	}

}


