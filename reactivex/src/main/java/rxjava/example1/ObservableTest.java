package rxjava.example1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * fuquanemail@gmail.com 2016/12/2 10:45
 * description:
 */
public class ObservableTest {

    static final Logger log = LoggerFactory.getLogger(ObservableTest.class);

    public static void main(String[] args) {
        observable();

        observer();

        //subscribe();

        // action0();

        scheduler();
    }

    public static void observable() {
        // 创建一个Observable
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                // 发送一个 Hello World 事件
                subscriber.onNext("Hello World!");

                // 事件发送完成
                subscriber.onCompleted();
            }
        });


        // 上面这段代码，也可以通过just操作符进行简化
        // 创建对象,just里面的每一个参数，相当于调用一次Subscriber#OnNext()
        Observable<String> observable2 = Observable.just("hello world!");
    }

    /**
     * 事件消费
     * RxJava 可以通过 subscribe 操作符，对上述事件进行消费。首先，先创建一个观察者。
     * 或者
     * 创建一个Subscriber
     * <p>
     * Observer 是观察者， Subscriber 也是观察者，Subscriber 是一个实现了Observer接口的抽象类，对 Observer 进行了部分扩展，在使用上基本没有区别；
     * Subscriber 多了发送之前调用的 onStart() 和解除订阅关系的 unsubscribe() 方法。
     * 并且，在 RxJava 的 subscribe 过程中，Observer 也总是会先被转换成一个 Subscriber 再使用。
     * 所以在这之后的示例代码，都使用 Subscriber 来作为观察者。
     */
    public static void observer() {
        // 创建一个Observer
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                log.info("complete");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                log.info(s);
            }
        };

        // 创建一个Subscriber
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                log.info("complete");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                log.info(s);
            }
        };
    }

    public static void subscribe() {

        //被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                // 发送一个 Hello World 事件
                subscriber.onNext("Hello!");
                subscriber.onNext("World!");

                // 事件发送完成
                subscriber.onCompleted();
            }
        });

        // 创建一个Subscriber
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                log.info("complete");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                log.info("onNext:" + s);
            }
        };

        observable.subscribe(subscriber);
    }

    /**
     * 回调
     */
    public static void action0() {
        Action0 completedAction = new Action0() {
            @Override
            public void call() {
                log.info("complete");
            }
        };

        // onNext(T t)
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                log.info("call:" + s);
            }
        };

        // onError(Throwable t)
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        };

        //被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                // 发送一个 Hello World 事件
                subscriber.onNext("Hello!");
                subscriber.onNext("World!");

                // 事件发送完成
                subscriber.onCompleted();
            }
        });

        observable.subscribe(onNextAction);

    }

    public static void scheduler(){
        Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("a");
                subscriber.onNext("b");

                subscriber.onCompleted();

            }
            // Schedulers.io(): 指定 subscribe() 所在的线程，也就是上面call()方法调用的线程
            // Schedulers.newThread(): 指定 Subscriber 回调方法所在的线程，也就是onCompleted, onError, onNext回调的线程
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                log.info("onCompleted:");
            }

            @Override
            public void onError(Throwable throwable) {
                log.info("onError:" + throwable);
            }

            @Override
            public void onNext(String s) {
                log.info("onNext:" + s);
            }
        });


    }

}
