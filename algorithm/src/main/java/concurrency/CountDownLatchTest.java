package concurrency;

import org.apache.commons.lang3.RandomUtils;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        // 数字表示需要的等待数
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(8);
        for (int i = 0; i < 8; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                try {
                    // 进入等待，因为start有计算器1，所以会一直等待，需要外层代码减1后才进行后面代码
                    System.out.println("员工" + finalI + "等待中。。。");
                    start.await();
                    System.out.println("员工" + finalI + "工作了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 工作完成后计算器减1，表示该工作完成
                    end.countDown();
                }
            });
            thread.start();
        }
        System.out.println("线程准备开始执行。。。。");
        Long starttime = System.currentTimeMillis();
        // 对计算器减1操作，start.await的线程就可以开始工作了
        start.countDown();
        System.out.println("线程可以执行中。。。。");
        // 等待线程中的计算器全部减1，变为0后，执行该代码
        end.await();
        System.out.println("线程都执行完了。。。。");
        System.out.println(System.currentTimeMillis() - starttime + "毫秒");


        CyclicBarrier barrier = new CyclicBarrier(10);
        for (int i = 11; i < 21; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                try {
                    // 进入等待，因为start有计算器1，所以会一直等待，需要外层代码减1后才进行后面代码
                    System.out.println("我在等待中" + finalI);
                    System.out.println(barrier.getNumberWaiting());
                    barrier.await();
                    System.out.println("开始了。。。。");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
        for (;;) {
            if (barrier.getNumberWaiting()==0) {
                barrier.reset();
                System.out.println("done");
                return;
            }
        }
    }

    private void get() {
        CompletableFuture future = new CompletableFuture();
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
