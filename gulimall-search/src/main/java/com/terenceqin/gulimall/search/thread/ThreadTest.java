package com.terenceqin.gulimall.search.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Title: ThreadTest</p>
 * Description�? */
public class ThreadTest {

	public static ExecutorService service = Executors.newFixedThreadPool(10);

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		System.out.println("main....start");
//		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//			System.out.println("当前线程" + Thread.currentThread().getId());
//			int i = 10 / 2;
//			System.out.println("运行结束" + i);
//		}, service);


		/**
		 * 方法完成后的感知
		 */
//		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//			System.out.println("当前线程" + Thread.currentThread().getId());
//			int i = 10 / 2;
//			System.out.println("运行结束" + i);
//			return i;
//		}, service).whenComplete((result , ex) -> {
//			// 这里能获取异常信�?但是没法修改数据
//			System.out.println("异步任务成功完成�?.. 结果:" + result);
//			// 感知异常 给出默认结果
//		}).exceptionally(ex -> 10);

		/**
		 * 方法执行完成后的处理
		 */
//		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//			System.out.println("当前线程" + Thread.currentThread().getId());
//			int i = 10 / 2;
//			System.out.println("运行结束" + i);
//			return i;
//		}, service).handle((result, ex) -> {
//			if(result != null){
//				return result * 8;
//			}
//			if(ex != null){
//				System.out.println("异常�?" + ex);
//				return -1;
//			}
//			return 0;
//		});
//		System.out.println("main....end 结果�? + future.get());

		/**
		 * 线程串行�?		 */
//		CompletableFuture.supplyAsync(() -> {
//			System.out.println("当前线程" + Thread.currentThread().getId());
//			int i = 10 / 2;
//			System.out.println("运行结束" + i);
//			return i;
//		}, service).thenRunAsync(() -> {
//			// thenRunAsync 不能获取执行结果
//			System.out.println("任务2启动�?..");
//		},service);

		/**
		 * 使用上一步的结果 但是没有返回结果
		 */
//		CompletableFuture.supplyAsync(() -> {
//			System.out.println("当前线程" + Thread.currentThread().getId());
//			int i = 10 / 2;
//			System.out.println("运行结束" + i);
//			return i;
//		}, service).thenAcceptAsync(res -> System.out.println("thenAcceptAsync获取上一步执行结果：" + res));

		/**
		 * 能接受上一步的结果 还有返回�?		 */
//		CompletableFuture<String> async = CompletableFuture.supplyAsync(() -> {
//			System.out.println("当前线程" + Thread.currentThread().getId());
//			int i = 10 / 2;
//			System.out.println("运行结束" + i);
//			return i;
//		}, service).thenApplyAsync(res -> {
//			System.out.println("任务2启动�?..");
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			return "thenApplyAsync" + res;
//		});
//		System.out.println("thenApplyAsync获取结果:" + async.get());

		/**
		 * 两任务合�?		 */
//		CompletableFuture<Object> async1 = CompletableFuture.supplyAsync(() -> {
//			System.out.println("任务1线程" + Thread.currentThread().getId());
//			int i = 10 / 2;
//			System.out.println("任务1结束" + i);
//			return i;
//		}, service);
//
//		CompletableFuture<Object> async2 = CompletableFuture.supplyAsync(() -> {
//			System.out.println("任务2线程" + Thread.currentThread().getId());
//			int i = 10 / 2;
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			System.out.println("任务2结束" + i);
//			return "任务合并";
//		}, service);

		// 合并上面两个任务 这个不能感知结果
//		async1.runAfterBothAsync(async2,() ->{
//			System.out.println("任务3开�?..");
//		} ,service);

		// 合并上面两个任务 可以感知前面任务的结�?//		async1.thenAcceptBothAsync(async2,(res1, res2) -> {
//			System.out.println("任务3开�?.. 任务1的结果：" + res1 + "任务2的结果：" + res2);
//		},service);

		/**
		 * 合并两个任何 还可以返回结�?		 */
//		CompletableFuture<String> async = async1.thenCombineAsync(async2, (res1, res2) -> res1 + ":" + res2 + "-> fire", service);
//		System.out.println("自定义返回结果：" + async.get());

		/**
		 * 合并两个任务 其中任何一个完成了 就执行这�?		 */
//		async1.runAfterEitherAsync(async2, () ->{
//
//			System.out.println("任务3开�?..之前的结�?");
//		},service);

		/**
		 * 感知结果 自己没有返回�?		 */
//		async1.acceptEitherAsync(async2, (res)-> System.out.println("任务3开�?..之前的结�?" + res), service);


//		CompletableFuture<String> async = async1.applyToEitherAsync(async2, (res) -> {
//
//			System.out.println("任务3开�?..之前的结�?" + res);
//			return res.toString() + "-> fire";
//		}, service);
//		System.out.println("任务3返回的结果：" + async.get());


		CompletableFuture<String> img = CompletableFuture.supplyAsync(() -> {
			System.out.println("查询商品图片信息");
			return "1.jpg";
		},service);

		CompletableFuture<String> attr = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("查询商品属�?);
			return "麒麟990 5G  钛空�?;
		},service);


		CompletableFuture<String> desc = CompletableFuture.supplyAsync(() -> {
			System.out.println("查询商品介绍");
			return "华为";
		},service);

		/**
		 * 等这三个都做�?		 */
//		CompletableFuture<Void> allOf = CompletableFuture.allOf(img, attr, desc);
//		allOf.join();
//
//		System.out.println("main....end"  + desc.get() + attr.get() + img.get());

		CompletableFuture<Object> anyOf = CompletableFuture.anyOf(img, attr, desc);
		anyOf.get();

		System.out.println("main....end" + anyOf.get());
		service.shutdown();
	}
}
