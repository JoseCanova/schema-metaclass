package org.nanotek.meta.test;

import java.util.Date;
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
		System.out.println("entered fibo x " + x + " result " + result);
		return Optional.ofNullable(result).orElseGet(() ->  fib(x));
	}
	
	private  long fib(long x)
	{ 	    
			var result = Optional.ofNullable(x).filter(y -> y >2).map( y -> fib(y-1) + fibo(y-2)).orElseGet(() -> x>0?1L:0L);
			memo.put(x, result);
			System.out.println("produced fib " + x + " result x " + "  " + result);
			return result;
	 	
	 }
					
	public static void main(String[] args) {
		 var resul = new Fibonacci().fibo(50);
		 System.out.println(resul);
	}

}
