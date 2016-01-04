package edu.upenn.cis573.subsetsum;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DriverTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		SubsetSumRetryBlock retry = new SubsetSumRetryBlock();
		int[] A = {1,2,3,4,5,6,7,8,9};
		print(A);
		retry.shuffle(A);
		print(A);
	}
	
	void print(int A[]){
		System.out.println();
		for (int i : A){
			System.out.print(i);
		}
	}

}
