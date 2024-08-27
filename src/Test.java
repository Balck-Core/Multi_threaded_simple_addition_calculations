import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) throws Exception {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                4,
                8,
                10l,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        Scanner sc = new Scanner(System.in);
        System.out.println("输入的数字需要是整数类型");
        System.out.println("请输入数字从哪里开始");
        int begin = sc.nextInt();
        System.out.println("请输入数字在哪里结束");
        int end = sc.nextInt();
        ArrayList<Future<Integer>> list = new ArrayList<>();
        System.out.println("请输入需要分几段来求和来增快速度");
        int sum = sc.nextInt();
        int intervalLength = (end - begin + 1) / sum;
        for (int i = 1; i <= sum; i++) {
            int subBegin = begin + (i - 1) * intervalLength;
            int subEnd = (i == sum) ? end : begin + i * intervalLength - 1;
            Future<Integer> future = pool.submit(new MyCallable(subBegin,subEnd));
            list.add(future);
        }
        int number = 0;
        for (int i = 0; i < sum; i++) {
            number += list.get(i).get();
        }
        System.out.println(number);
        pool.shutdown();
        sc.close();
    }
}
