package com.starter.medicalcommon.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-03-16 16:32
 **/
public class ExecutorUtil {

    /**
     * CPU 的堵塞系数
     */
    private static final double BLOCKING_COEFFICIENT = 0.8;

    private static ThreadPoolExecutor executor;

    static {
        executor = newIoIntensiveThreadPool("common-executor");
    }

    private ExecutorUtil() {
    }

    public static ThreadPoolExecutor getExecutor() {
        return executor;
    }


    /**
     * IO 密集型线程池
     *
     * @param poolNameFormat 线程池名字的格式，例如: name-pool-%d
     * @return IO 密集型线程池
     */
    public static ThreadPoolExecutor newIoIntensiveThreadPool(String poolNameFormat) {
        Integer processors = Runtime.getRuntime().availableProcessors();
        int coreSize = (int) (processors / (1 - BLOCKING_COEFFICIENT));
        return createFixedThreadPool(poolNameFormat, coreSize);
    }

    /**
     * CPU 密集型线程池
     *
     * @param poolNameFormat 线程池名字的格式，例如: name-pool-%d
     * @return CPU 密集型线程池
     */
    public static ThreadPoolExecutor newCpuIntensiveThreadPool(String poolNameFormat) {
        int coreSize = Runtime.getRuntime().availableProcessors() + 1;
        return createFixedThreadPool(poolNameFormat, coreSize);
    }

    /**
     * 构造线程池
     *
     * @param poolNameFormat 线程池名字的格式，例如: name-pool-%d
     * @param coreSize       核心线程个数
     * @return 创建固定线程数目的线程池
     */
    private static ThreadPoolExecutor createFixedThreadPool(String poolNameFormat, int coreSize) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat(poolNameFormat)
                .build();
        return new ThreadPoolExecutor(coreSize,
                coreSize,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                threadFactory);
    }
}