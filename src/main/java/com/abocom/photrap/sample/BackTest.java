package com.abocom.photrap.sample;

import io.reactivex.rxjava3.core.BackpressureOverflowStrategy;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.*;

@Slf4j
public class BackTest {



    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
        for(int i=0;i<100000;i++){
            final int a = i;
            CompletableFuture.runAsync(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    log.info("{}", a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },tpe);
        }


        //backpressurex();

        TimeUnit.SECONDS.sleep(1000);
    }

    private static void backpressurex(){
        ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
        Flowable.range(1, 100)
                .subscribeOn(Schedulers.single())
                .buffer(1)
                .subscribe(i->{
                    CompletableFuture.runAsync(()->{
                        try {
                            TimeUnit.MILLISECONDS.sleep(1000);
                            log.info("{}", i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    },tpe);
                });
    }

    private static void backPressure() {
        // BackPressure issue
        PublishSubject<Integer> ps = PublishSubject.create();


        ps.observeOn(Schedulers.computation())
                .subscribe(data -> {
                    Thread.sleep(100);
                    log.info("{}", data);
                }, error -> System.out.println(error.getMessage()));

        for(int i = 0; i < 50000000; i++) {
            ps.onNext(i);
        }
        ps.onComplete();

    }
    private static void useOnBackPressureBuffer2() {
        Flowable<Integer> source = Flowable.create(emitter -> {
            for (int i=0;i<1000;i++) {
                if(emitter.isCancelled())
                    return;

                emitter.onNext(i);
            }

            emitter.onComplete();
        }  , BackpressureStrategy.BUFFER); //BackpressureStrategy.BUFFER 을 인자로 넣어서 생성이 가능하다.

        source.observeOn(Schedulers.io())
                .subscribe(System.out::println);

    }

    private static void useOnBackPressureBuffer() {

        ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        Flowable.range(1, 200)
                .onBackpressureBuffer(128, () -> {}, BackpressureOverflowStrategy.ERROR)
                .observeOn(Schedulers.from(tpe))
                .subscribe(data -> {
                    Thread.sleep(100);
                    log.info("{}", data);
                }, error -> System.out.println(error.getMessage()));

    }

    //기본 버퍼 갯수 만큼만 버퍼에 저장하고 나머지는 무시하므로
    // 128까지만 출력됨.
    private static void useOnBackPressureDrop() {
        Flowable.range(1, 50_000_000)
                .onBackpressureDrop()
                .observeOn(Schedulers.computation())
                .subscribe(data -> {
                    Thread.sleep(100);
                    System.out.println(data);
                }, error -> System.out.println(error.getMessage()));

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // onBackPressureDrop() + 마지막 데이터까지 발행.
    private static void useOnBackPressureLatest() {
        Flowable.range(1, 50_000_000)
                .onBackpressureLatest()
                .observeOn(Schedulers.computation())
                .subscribe(data -> {
                    Thread.sleep(100);
                    System.out.println(data);
                }, error -> System.out.println(error.getMessage()));

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}