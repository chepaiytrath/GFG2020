package machinecoding.messagebroker.advanced;

// Message = payload + topic
// PubSubService = Message Broker. Publisher/Subscriber aren't concerned with how it saves/retrieves data
// Publisher : Sends Message (topic embedded inside) to PubSubService
// Subscriber :


public class A0DriverClass {
    public static void main(String[] args) {
        // Instantiate publishers, subscribers and PubSubService
        Publisher javaPublisher = new PublisherImpl();              // Publisher doesn't have any topic field
        Publisher pythonPublisher = new PublisherImpl();

        Subscriber javaSubscriber = new SubscriberImpl();           // Subscriber doesn't have any topic field
        Subscriber allLanguagesSubscriber = new SubscriberImpl();
        Subscriber pythonSubscriber = new SubscriberImpl();

        PubSubService pubSubService = new PubSubService();

        // Declare Messages and Publish Messages to PubSubService
        Message javaMsg1 = new Message("Java", "Core Java Concepts");         // Create Java Messages with topic field = "Java" embedded inside
        Message javaMsg2 = new Message("Java", "Spring MVC : Dependency Injection and AOP");
        Message javaMsg3 = new Message("Java", "JPA & Hibernate");

        javaPublisher.publish(javaMsg1, pubSubService);             // Publish Java Messages via Java Publisher
        javaPublisher.publish(javaMsg2, pubSubService);
        javaPublisher.publish(javaMsg3, pubSubService);

        Message pythonMsg1 = new Message("Python", "Easy and Powerful programming language");
        Message pythonMsg2 = new Message("Python", "Advanced Python message");  // Create Python Messages with topic field = "Python" embedded inside

        pythonPublisher.publish(pythonMsg1, pubSubService);         // Publish Python Messages via Python Publisher
        pythonPublisher.publish(pythonMsg2, pubSubService);

        // Declare Subscribers
        javaSubscriber.addSubscriber("Java", pubSubService);            // Java subscriber only subscribes to Java topics
        pythonSubscriber.addSubscriber("Python", pubSubService);        // Python subscriber only subscribes to Python topics
        allLanguagesSubscriber.addSubscriber("Java", pubSubService);    // All subscriber subscribes to both Java and Python
        allLanguagesSubscriber.addSubscriber("Python", pubSubService);

        // Trying unSubscribing a subscriber
        // pythonSubscriber.unSubscribe("Python", pubSubService);

        // Broadcast message to all subscribers. After broadcast, messageQueue will be empty in PubSubService
        pubSubService.broadcast();

        // Print messages of each subscriber to see which messages they got
        System.out.println("Messages of Java Subscriber are: ");
        javaSubscriber.printMessages();

        System.out.println("\nMessages of Python Subscriber are: ");
        pythonSubscriber.printMessages();

        System.out.println("\nMessages of All Languages Subscriber are: ");
        allLanguagesSubscriber.printMessages();

        // After broadcast the messagesQueue will be empty, so publishing new messages to server
        System.out.println("\nPublishing 2 more Java Messages...");
        Message javaMsg4 = new Message("Java", "JSP and Servlets");
        Message javaMsg5 = new Message("Java", "Struts framework");

        javaPublisher.publish(javaMsg4, pubSubService);
        javaPublisher.publish(javaMsg5, pubSubService);

        javaSubscriber.getMessagesForSubscriberOfTopic("Java", pubSubService);
        System.out.println("\nMessages of Java Subscriber now are: ");
        javaSubscriber.printMessages();
    }
}