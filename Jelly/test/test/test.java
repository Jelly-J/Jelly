package test;

public class test {

	public static void main(String[] args) throws InterruptedException {

		Thread threads = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println(2);
				}
			}
		});
		threads.start();

		for (int i = 0; i < 10; i++) {
			System.out.println(1);
		}
//		new test().name();
	}

	public void name() {
		synchronized (this) {
			System.out.println(3);
			Thread.yield();
			System.out.println(4);
		}
	}
}
