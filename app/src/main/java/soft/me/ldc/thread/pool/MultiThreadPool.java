package soft.me.ldc.thread.pool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import soft.me.ldc.thread.ThreadTask;

/**
 * Created by LDC on 2017/8/15.
 */

public class MultiThreadPool {
    private static final int poolcount = 5;//核心线程数
    private static final int Maxpoolcount = 15;//最大线程数
    private ExecutorService pool = null;//线程池
    private static volatile MultiThreadPool instance = null;
    //线程操作对象
    private ThreadPoolExecutor threadPoolExecutor = null;//线程池对象


    //单实例 线程安全
    public static MultiThreadPool newInsance() {
        synchronized (MultiThreadPool.class) {
            if (instance == null)
                instance = new MultiThreadPool();
        }
        return instance;
    }

    /**
     * 构造函数
     */
    private MultiThreadPool() {
        initPool();
    }

    /**
     * 推送一个线程任务到线程池
     */
    public void pushThread(ThreadTask thread) {
        initPool();
        if (pool != null && !pool.isShutdown())
            pool.execute(thread);
    }

    /**
     * 推送一个线程任务到线程池
     */
    public void pushThread(Thread thread) {
        initPool();
        if (pool != null && !pool.isShutdown())
            pool.execute(thread);
    }

    /**
     * 推送全部全部线程任务到线程池
     *
     * @param threads
     */
    public void pushAllThread(List<ThreadTask> threads) {
        initPool();
        if (pool != null && !pool.isShutdown())
            for (ThreadTask thread : threads) {
                pool.execute(thread);
            }
    }

    // TODO: 2017/12/11  获取线程池
    public ExecutorService getPool() {
        return pool;
    }

    // TODO: 2017/12/11 实例化线程池 创建线程池
    public void initPool() {
        if (pool == null) {
            pool = new ThreadPoolExecutor(poolcount, Maxpoolcount, 0L, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());
        }
        if (pool != null) {
            threadPoolExecutor = (ThreadPoolExecutor) pool;//对象转换
        }
    }

    // TODO: 2017/12/11 获取当前线程活动数
    public int getActiveThradCount() {
        int threadCount = -1;
        try {
            if (pool != null)
                threadCount = ((ThreadPoolExecutor) pool).getActiveCount();//当前活动任务

        } catch (Exception e) {
            threadCount = -1;
            e.printStackTrace();
        }
        return threadCount;
    }

    // TODO: 2017/12/11 获取线程池对象
    public ThreadPoolExecutor getPoolExecutor() {
        return ((ThreadPoolExecutor) pool);
    }

    // TODO: 2017/12/11 完成任务数
    public long getCompletedTaskNumber() {
        long completedtaskNumber = -1;//默认时-1
        try {
            if (threadPoolExecutor != null) {
                completedtaskNumber = threadPoolExecutor.getCompletedTaskCount();
            }
        } catch (Exception e) {
            completedtaskNumber = -1;
            e.printStackTrace();
        }
        return completedtaskNumber;
    }

    // TODO: 2017/12/11 获取任务数
    public long getTaskNumber() {
        long tasknumber = -1;
        try {
            if (threadPoolExecutor != null) {
                tasknumber = threadPoolExecutor.getTaskCount();
            }
        } catch (Exception e) {
            tasknumber = -1;
            e.printStackTrace();
        }
        return tasknumber;
    }

    // TODO: 2017/12/11 未完成数
    public long getNotCompletedTaskNumber() {
        long completedTask = -1;//完成任务
        long allTask = -1;//全部任务
        long notcompletedTask = -1;//未完成任务
        try {
            if (threadPoolExecutor != null) {
                completedTask = threadPoolExecutor.getCompletedTaskCount();
                allTask = threadPoolExecutor.getTaskCount();
                notcompletedTask = (allTask - completedTask);
            }
        } catch (Exception e) {
            notcompletedTask = -1;
            e.printStackTrace();
        }
        return notcompletedTask;
    }
}
