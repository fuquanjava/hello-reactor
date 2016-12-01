package reactor.example1;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.stream.Stream;

/**
 * fuquanemail@gmail.com 2016/12/1 11:26
 * description:
 */
public class FluxTest {
    public static void main(String[] args) {
        // testFlux();

        testBoundedRequest();

    }


    public static void testFlux() {
        // 完全申明式，数据流动的才执行
        Flux<String> flux = Flux.just("red", "white", "blue");
        Flux<String> upper = flux.log().map(value -> value.toUpperCase());
        System.err.println(upper.blockFirst());

        Stream<String> stream = Stream.of("red", "white", "blue");
        Stream<String> streamUpper = stream.map(value -> {
            System.err.println("stream:" + value);
            return value.toUpperCase();
        });
        System.err.println(streamUpper.findFirst().get());
        System.err.println("-----------------------------------------------");

        Flux.just("red", "white", "blue")
                .log()
                .map(value -> value.toUpperCase())
                .subscribe(System.out::println);
    }

    public static void testBoundedRequest() {
        Flux.just("red", "white", "blue")
                .log()
                .map(value -> value.toUpperCase()).subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
                System.out.println("onSubscribe:" + s);
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext:" + s);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError:" + t);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    public static void test2(){
        Flux.just("red","white","blue")
                .log()
                .map(value -> value.toUpperCase())
                .subscribe(null,2);

    }
}
