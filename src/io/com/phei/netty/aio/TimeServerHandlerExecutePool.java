package io.com.phei.netty.aio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServerHandlerExecutePool {
	
	private ExecutorService excutor;
	
	public TimeServerHandlerExecutePool(int maxPoolSize,int queueSize){
		excutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L, TimeUnit.SECONDS, 
					new ArrayBlockingQueue<Runnable>(queueSize));
	}
	
	public void execute(Runnable task){
		excutor.execute(task);
	}
}
