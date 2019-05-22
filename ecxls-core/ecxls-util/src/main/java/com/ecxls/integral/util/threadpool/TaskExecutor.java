package com.ecxls.integral.util.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskExecutor {

	private static final int MAX_POOL_SIZE = 100;

	private static TaskExecutor sInstance = null;

	private ExecutorService mTaskService;

	private TaskExecutor() {
		mTaskService = new ThreadPoolExecutor(MAX_POOL_SIZE, MAX_POOL_SIZE, 0L,
				TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
				new DefaultThreadFactory());
	}

	public synchronized static TaskExecutor getInstance() {
		if (sInstance == null) {
			sInstance = new TaskExecutor();
		}
		return sInstance;
	}

	public void execute(Runnable runnable) {
		mTaskService.execute(runnable);
	}
	
	public <T> Future<T> submit(Callable<T> task) {
		return mTaskService.submit(task);
	}
	
	public void shutdown() {
		mTaskService.shutdown();
	}

	private static class DefaultThreadFactory implements ThreadFactory {

		private static final AtomicInteger poolNumber = new AtomicInteger(1);
		private final ThreadGroup group;
		private final AtomicInteger threadNumber = new AtomicInteger(1);
		private final String namePrefix;

		DefaultThreadFactory() {
			SecurityManager s = System.getSecurityManager();
			group = (s != null) ? s.getThreadGroup() : Thread.currentThread()
					.getThreadGroup();
			namePrefix = getClass().getSimpleName() + "-"
					+ poolNumber.getAndIncrement() + "-thread";
		}

		public Thread newThread(Runnable r) {
			Thread t = new Thread(group, r, namePrefix
					+ threadNumber.getAndIncrement(), 0);

			if (t.isDaemon()) {
				t.setDaemon(false);
			}

			if (t.getPriority() != Thread.NORM_PRIORITY) {
				t.setPriority(Thread.NORM_PRIORITY);
			}

			return t;
		}
	}
}
