package victor.training.spring.events.order;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Queues {
    // HINT: to preserve the durable subscription, the producer APP must not define the consumer queue
    // >> Clone this class and make sure to remove the useless queues for each app
    String Q1_OUT = "q1out";
    @Output(Q1_OUT)
    MessageChannel q1out();
}