package com.malsum.apigateway;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestCircuitBreaker {

    private Logger logger = LoggerFactory.getLogger(RestCircuitBreaker.class);

    @GetMapping("/sample-api")
    //@Retry(name = "sample-api",fallbackMethod = "hardCodedResponse")
    @CircuitBreaker(name = "default", fallbackMethod = "hardCodedResponse")
//    @RateLimiter(name = "default")
//    @Bulkhead(name = "default")
    public String sampleApi(){
        logger.info("Sample Api call received");
        ResponseEntity<String> responseEntity =  new RestTemplate().getForEntity("http://localhost:9457",String.class);
        //return "Sample Api";
        return responseEntity.toString();
    }

    private String hardCodedResponse(Exception e){
        return "fallback-response";
    }
}
