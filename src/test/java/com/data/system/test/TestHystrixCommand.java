package com.data.system.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.data.system.service.UserInfoService;
import com.data.system.test.domain.CommandHelloFailure;
import com.data.system.test.domain.CommandHelloWorld;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:/config/spring-mybatis.xml" })
public class TestHystrixCommand {
	Logger logger = LoggerFactory.getLogger(TestHystrixCommand.class);
	@Autowired
	private UserInfoService userInfoService;

	// @Test
	public void testExecuteCommand() throws InterruptedException {
//		int i = 2;
//		for (; i < 15; i++) {
//			HystrixCommand<Integer> command = new UserInfoServiceCommand(userInfoService, i, "" + i);
//			Integer r = command.execute();
//			String method = r == -1 ? "fallback" : "run";
//			logger.error("call {} times,result:{},method:{},isCircuitBreakerOpen:{}", i, r, method, command.isCircuitBreakerOpen());
//		}
//		// 等待6s，使得熔断器进入半打开状态
//		Thread.sleep(6000);
//		for (; i < 23; i++) {
//			HystrixCommand<Integer> command = new UserInfoServiceCommand(userInfoService, i, "" + i);
//			Integer r = command.execute();
//			String method = r == -1 ? "fallback" : "run";
//			logger.error("call {} times,result:{},method:{},isCircuitBreakerOpen:{}", i, r, method, command.isCircuitBreakerOpen());
//		}
	}

	// @Test
	public void testSynchronous() throws InterruptedException, ExecutionException {
		String commandHelloWorld = new CommandHelloWorld("World").execute();
		logger.info(commandHelloWorld);
		String commandHelloBob = new CommandHelloWorld("Bob").execute();
		logger.info(commandHelloBob);
		Future<String> fs = new CommandHelloWorld("Tom").queue();
		String commandTom = fs.get();
		logger.info(commandTom);
		// String ObservableCommandHelloWorld = new
		// ObservableCommandHelloWorld("Bob").observe(;
	}

	@Test
	public void testObservable() {
		Observable<String> fWorld = new CommandHelloWorld("world").observe();
		Observable<String> fTom = new CommandHelloWorld("Tom").observe();
		System.out.println(fWorld.toBlocking().single());
		System.out.println(fTom.toBlocking().single());
		fWorld.subscribe(new Observer<String>() {

			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {

			}

			@Override
			public void onNext(String t) {
				System.out.println("onNext:" + t);
			}
		});

		fTom.subscribe(new Action1<String>() {

			@Override
			public void call(String t) {
				System.out.println("onNext:" + t);
			}
		});

//		fWorld.subscribe((v) -> {
//			System.out.println("onNext:" + v);
//		});
//		fTom.subscribe((v) -> {
//			System.out.println("onNext:" + v);
//		}, (exception) -> {
//			exception.printStackTrace();
//		});
	}

	@Test
	public void testFallBack() {
		System.out.println(new CommandHelloFailure("Tome").execute());
	}

	@Test
	public void testQueue() throws InterruptedException, ExecutionException {
		Future<String> future = new CommandHelloWorld("张三").queue();
		System.out.println(future.isDone() ? future.get() : "未执行完");
	}

	@Test
	public void testObserveAndToObservable() {
		// observe 事件注册前执行run
		Observable<String> observe = new CommandHelloWorld("HystrixCommandObserve").observe();
		System.out.println("observe开始事件注册");
		observe.subscribe(new Observer<String>() {

			@Override
			public void onCompleted() {
				System.out.println("observe onCompleted");
			}

			@Override
			public void onError(Throwable e) {

			}

			@Override
			public void onNext(String t) {
				System.out.println("observe onNext");
				System.out.println("observe result :" + t);
			}
		});

		Observable<String> observable = new CommandHelloWorld("HystrixCommandToObservable").toObservable();
		System.out.println("toObservable开始事件注册");
		observable.subscribe(new Observer<String>() {

			@Override
			public void onCompleted() {
				System.out.println("toObservable onCompleted");
			}

			@Override
			public void onError(Throwable e) {

			}

			@Override
			public void onNext(String t) {
				System.out.println("toObservable onNext");
				System.out.println("toObservable result :" + t);
			}
		});
	}
}
