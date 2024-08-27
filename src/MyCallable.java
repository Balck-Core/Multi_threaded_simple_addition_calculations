import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    private int begin;
    private int end;
    int number = 0;

    public MyCallable(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int number = 0;
        for (int i = begin; i <= end; i++) {
            number += i;
        }
        return number;
    }
}
