package edu.upenn.cis573.subsetsum;

import java.util.Random;

public class SubsetSumRetryBlock extends SubsetSumSolver {

	/*
	 * Use a Retry Block to implement this method.
	 * Use the SubsetSumSolverDP.solve method in your Retry Block.
	 * Use the acceptance test in SubsetSumSolver
	 */	
	public boolean[] solve(int[] A, int target) throws ValidSolutionNotFoundException {
		//make a backup
		int[] copy = new int[A.length];
		System.arraycopy(A, 0, copy, 0, A.length);
		boolean[] result = SubsetSumImplementations.solveDP(A, target);
		if (!accept(result, A, target)) {
			//restore state, shuffle and retry
			System.arraycopy(copy, 0, A, 0, copy.length);
			shuffle(A);
			boolean[] resultTwo = SubsetSumImplementations.solveDP(A, target);
			if (!accept(resultTwo, A, target)) {
				//restore state, shuffle and retry
				System.arraycopy(copy, 0, A, 0, copy.length);
				shuffle(A);
				boolean[] resultThree = SubsetSumImplementations.solveDP(A, target);
				if (!accept(resultThree, A, target)) 
					throw new ValidSolutionNotFoundException();
				return resultThree;
			}
			return resultTwo;
		}
		return result;	
	}
	
	/*
	 * This is the method that you need to implement as part of your Retry Block.
	 */
    protected void shuffle(int A[]) {
        Random random = new Random();
        for (int i = A.length - 1; i > 0; i--) {
          int index = random.nextInt(i + 1);
          int a = A[index];
          A[index] = A[i];
          A[i] = a;
        }
    }

}
