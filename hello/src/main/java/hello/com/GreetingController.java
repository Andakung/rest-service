package hello.com;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class GreetingController {
 
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    // define a variable GreetingComponent
    private GreetingComponent g;
    
    // constructor dependency injection
    // inject/initialize GreetingComponent in the constructor
    @Autowired
    public GreetingController(GreetingComponent g) {
        this.g = g;
    }
    
    // ถ้าไม่ได้ส่งอะไรมาจะเอาอันนี้เป็นค่า Default
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    
 // test the greeting component
    @GetMapping("/testgreeting")
    public ResponseEntity<String> getMessage() {
        return ResponseEntity.ok(g.getMessage());
    }
}