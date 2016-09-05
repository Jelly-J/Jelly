package test;

import org.junit.Test;

public class Threads {

//	@Override
//	public void run() {
//		for (int i = 0; i < 10; i++) {
//			System.out.println(2);
//		}
//
//	}
//
//	public static void main(String[] args) {
//		Thread thread = new Thread(new Threads());
//		thread.start();
//		
//
//		for (int i = 0; i < 10; i++) {
//			System.out.println(1);
//		}
//	}
	@Test
	public synchronized void runs(){
		Thread thread = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println(i);
				}
			}
		});
		thread.setDaemon(true);
		thread.start();
	}

}
