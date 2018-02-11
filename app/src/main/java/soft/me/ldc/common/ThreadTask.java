package soft.me.ldc.common;

import android.support.annotation.NonNull;

/**
 * Created by ldc45 on 2018/1/20.
 */

public abstract class ThreadTask implements Runnable, Comparable<ThreadTask> {
    private int priority;//优先级

    //构造函数
    public ThreadTask(int priority) {
        if (priority < 0)
            throw new IllegalArgumentException("优先级不能小于0");
        this.priority = priority;

    }

    @Override
    public int compareTo(@NonNull ThreadTask o) {
        int self = this.priority;
        int other = o.getPriority();
        return self < other ? 1 : self > other ? -1 : 0;
    }

    @Override
    public void run() {
        doRun();
    }

    //执行任务
    protected abstract void doRun();

    //获取权限
    public int getPriority() {
        return priority;
    }
}
