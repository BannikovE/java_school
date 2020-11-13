package app.jms;

import app.model.Order;
import app.model.Product;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.Map;

//@RequestScoped
public class Producer {

   public static void produceMessage()  {
        Hashtable<String, String> props = new Hashtable<>();
        props.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.put("java.naming.provider.url", "tcp://localhost:61616");
        props.put("queue.js-queue", "ScoreboardQueue");
        props.put("connectionFactoryNames", "queueCF");

        try {
            Context context = new InitialContext(props);
            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("queueCF");
            Queue queue = (Queue) context.lookup("js-queue");
            QueueConnection connection = connectionFactory.createQueueConnection();
            connection.start();

            QueueSession session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);

            QueueSender sender = session.createSender(queue);
            TextMessage message = session.createTextMessage("Order created");
//            ObjectMessage objectMessage = session.createObjectMessage(order);

            sender.send(message);
//            sender.send(objectMessage);

            sender.close();
            session.close();
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
