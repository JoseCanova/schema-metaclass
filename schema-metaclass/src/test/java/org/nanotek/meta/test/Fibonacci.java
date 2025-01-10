package org.nanotek.meta.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * simple functional style exercise.
 */
public class Fibonacci {

	private Map<Long , Long> memo = new HashMap<>();
	
	
	private  long fibo (long x)
	{
		var result = memo.get(x);
		return result == null?fib(x):result;
	}
	
	private  long fib(long x)
	{ 	
			var result = Optional.ofNullable(x).filter(y -> y >2).map( y -> fib(y-1) + fibo(y-2)).orElse(1L);
			memo.put(x, result);
			return result;
	 	
	 }
					
	public static void main(String[] args) {
		 var resul = new Fibonacci().fibo(50);
		 System.out.println(resul);
	}

}
