package machinecoding.messagebroker.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Exchange {

    Map<String, Queue> allQueues = new HashMap<>();

    public void addNewQueue(Queue q, String queueRoutingKey) {
        allQueues.put(queueRoutingKey, q);
    }

    void addAndRouteQueueMessage(Object payload, String routingKeys) {
        String[] rKeys = routingKeys.split(",");
        for (String key : rKeys) {
            if (!allQueues.containsKey(key)) {
                System.out.println("Routing queue not found : " + key);
                continue;
            }
            Queue currentQueue = allQueues.get(key);
            currentQueue.add(payload);
        }
    }

    public Object consumeMessage(String routingQueue) {

        Queue currentQueue = allQueues.get(routingQueue);
        if (currentQueue.isEmpty())
            return null;
        return currentQueue.remove();
    }
}