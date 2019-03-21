package com.bonc.dataplatform.demo;

import java.time.Clock;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 不是线程安全的
 */
class Counter1 extends Thread {
	private static long counter = 0;
	private String name;

	public Counter1(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		synchronized (this) {
			counter++;
			System.out.println(name + ":" + counter);
		}
	}
}

/**
 * 线程安全的 ,AtomicLong是基于CAS实现的
 */
class Counter2 extends Thread {
	private static AtomicLong counter = new AtomicLong(0);
	private String name;

	public Counter2(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(name + ":" + counter.incrementAndGet());
	}
}

/**
 * 线程安全的， LongAdder也是基于CAS实现的
 * 
 *
 */
class Counter3 extends Thread {
	private static LongAdder counter = new LongAdder();
	private String name;

	public Counter3(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		synchronized (counter) {
			counter.add(1);
			System.out.println(name + ":" + counter.sum());
		}
	}
}

/***
 * DoubleAdder、LongAccumulator、DoubleAccumulator与LongAdder
 * 
 * 虽然LongAdder可以替换AtomicLong，都是通过CAS实现，但是他们的的初衷还是不一样的，参见以下说明：
 * 
 * One or more variables that together maintain an initially zero
 * {@code long} sum.  When updates (method {@link #add}) are contended
 * across threads, the set of variables may grow dynamically to reduce
 * contention. Method {@link #sum} (or, equivalently, {@link
 * #longValue}) returns the current total combined across the
 * variables maintaining the sum.
 *
 * <p>This class is usually preferable to {@link AtomicLong} when
 * multiple threads update a common sum that is used for purposes such
 * as collecting statistics, not for fine-grained synchronization
 * control.  Under low update contention, the two classes have similar
 * characteristics. But under high contention, expected throughput of
 * this class is significantly higher, at the expense of higher space
 * consumption.
 *
 * <p>LongAdders can be used with a {@link
 * java.util.concurrent.ConcurrentHashMap} to maintain a scalable
 * frequency map (a form of histogram or multiset). For example, to
 * add a count to a {@code ConcurrentHashMap<String,LongAdder> freqs},
 * initializing if not already present, you can use {@code
 * freqs.computeIfAbsent(k -> new LongAdder()).increment();}
 *
 * <p>This class extends {@link Number}, but does <em>not</em> define
 * methods such as {@code equals}, {@code hashCode} and {@code
 * compareTo} because instances are expected to be mutated, and so are
 * not useful as collection keys.
 *
 * @since 1.8
 * @author Doug Lea
 */
 */
public class Java8Concurrent {
	public static void main(String[] args) {
		long a = Clock.systemUTC().millis();
		for (int i = 0; i < 100; i++) {
			Thread thread = new Counter1(i + "");
			thread.start();
		}
		long b = Clock.systemUTC().millis();
		System.out.println("===================================");
		System.out.println("耗时：" + (b - a));
		System.out.println("===================================");

	}
}
