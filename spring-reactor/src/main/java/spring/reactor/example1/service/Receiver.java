package spring.reactor.example1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.bus.Event;
import reactor.fn.Consumer;
import spring.reactor.example1.domain.QuoteResource;

import java.util.concurrent.CountDownLatch;

/**
 * fuquanemail@gmail.com 2016/11/24 17:35
 * description:
 */
@Service
public class Receiver implements Consumer<Event<Integer>> {

    @Autowired
    CountDownLatch latch;

    RestTemplate restTemplate = new RestTemplate();


    @Override
    public void accept(Event<Integer> event) {
        QuoteResource quoteResource =
                restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", QuoteResource.class);

        System.out.println("Quote " + event.getData() + ": " + quoteResource.getValue().getQuote());

        latch.countDown();
    }
}
