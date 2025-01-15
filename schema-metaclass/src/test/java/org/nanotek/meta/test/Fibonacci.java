package org.nanotek.meta.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;


/**
 * simple functional style exercise.
 */
public class Fibonacci {

	private Map<Long , Long> memo = new HashMap<>();
	
	private  long fibo (long x)
	{
		var result = memo.get(x);
		Supplier<Optional<Long>> sup = () ->  Optional.of( fib(x));
		return Optional.ofNullable(result).or(sup).get();
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
