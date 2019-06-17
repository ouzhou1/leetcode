import java.util.concurrent.CountDownLatch;

/**
 * Description:
 *
 * Solution:
 *
 * @author peter
 * @date 2019/6/17.
 */
public class ConcurrentExperiment {

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);

        threadCreator(latch);

        threadCreator(latch);

        try {
            System.out.println("等待2个子线程执行完毕...");
            latch.await();
            System.out.println("2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void threadCreator(CountDownLatch latch) {
        new Thread(() -> {
            try {
                System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                Thread.sleep(1);
                System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
