package com.lulu.curl.okhttpdemo.luokhttp;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanglulu on 2019/2/22.
 * for 线程池管理类
 */
public class ThreadPoolManager {

    private static final String TAG = "ThreadPoolManager";

    //1. 创建队列,用来保存异步请求任务
    private LinkedBlockingQueue<Runnable> mQueue = new LinkedBlockingQueue<>();//LinkedBlockingQueue FIFO

    //2. 添加异步任务到队列中
    public void addTask(Runnable runnable) {
        try {
            if (runnable != null) {
                mQueue.put(runnable);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //3. 创建线程池
    private ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadPoolManager() {

        mThreadPoolExecutor = new ThreadPoolExecutor(
                3, 10, 15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                //处理被抛出来的任务(被拒绝的任务)
                addTask(r);
            }
        });
        mThreadPoolExecutor.execute(communicateThread);
        mThreadPoolExecutor.execute(delayThread);
    }

    //4. 创建队列与线程池的"交互"线程
    public Runnable communicateThread = new Runnable() {
        @Override
        public void run() {
            Runnable runnable = null;
            while (true) {
                try {
                    runnable = mQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //执行线程池中的线程任务
                mThreadPoolExecutor.execute(runnable);
            }
        }
    };

    // 创建延时队列
    private DelayQueue<HttpTask> mDelayQueue = new DelayQueue<>();

    //添加到延时队列
    public void addDelayTask(HttpTask httpTask) {
        if (httpTask != null) {
            httpTask.setDelayTime(3000);
            mDelayQueue.offer(httpTask);
            Log.d(TAG, "addDelayTask: ");
        }
    }
    public Runnable delayThread = new Runnable() {
        @Override
        public void run() {
            HttpTask ht = null;
            while (true) {
                try {
                    ht = mDelayQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ht != null && ht.getRetryCount() < 3) {
                    mThreadPoolExecutor.execute(ht);
                    ht.setRetryCount(ht.getRetryCount() + 1);
                    Log.d(TAG, "run: 重试机制: " + ht.getRetryCount());
                } else {
                    Log.d(TAG, "run: 重试机制:超出次数 ");
                }
            }
        }
    };

    private static ThreadPoolManager threadPoolManager;

    public static ThreadPoolManager getInstance() {
        if (threadPoolManager == null) {
            synchronized (ThreadPoolManager.class) {
                if (threadPoolManager == null) {
                    threadPoolManager = new ThreadPoolManager();
                }
            }
        }
        return threadPoolManager;
    }
}
