package io.github.bluething.spring.cloud.gscloudcircuitbreaker.reading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@SpringBootApplication
public class ReadingApplication {

    private BookService bookService;

    @Autowired
    public ReadingApplication(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "to-read")
    public Mono<String> toRead() {
        return bookService.readingList();
    }

    public static void main(String[] args) {
        SpringApplication.run(ReadingApplication.class, args);
    }

}
