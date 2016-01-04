package edu.upenn.cis573.subsetsum;

public class SubsetSumRecoveryBlock extends SubsetSumSolver {

	/*
	 * Use a Recovery Block to implement this method.
	 * Use the SubsetSumSolverBF.solve and SubsetSumSolverDP.solve methods in your Recovery Block.
	 * Use the acceptance test you wrote in SubsetSumSolver
	 */	
	public boolean[] solve(int[] A, int target) throws ValidSolutionNotFoundException {
		//make a backup
		int[] copy = new int[A.length];
		System.arraycopy(A, 0, copy, 0, A.length);
		//try to solve with DP
		boolean[] result = SubsetSumImplementations.solveDP(A, target);
		if (!accept(result, A, target)) {
			//restore state and try to solve with BF
			System.arraycopy(copy, 0, A, 0, copy.length);
			boolean[] resultTwo = SubsetSumImplementations.solveBF(A, target);
			if (!accept(resultTwo, A, target)) 
				throw new ValidSolutionNotFoundException();
			return resultTwo;
		}
		return result;	
	}

}
