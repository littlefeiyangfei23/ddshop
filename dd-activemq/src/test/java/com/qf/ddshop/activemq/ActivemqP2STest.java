/*
package com.qf.ddshop.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;

public class ActivemqP2STest {

    @Test
    public void testProducer() throws JMSException {
        System.err.println(Thread.currentThread().getName());
        //创建connectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://10.31.161.48:61616");
        //通过工厂创建连接对象
        Connection connection = connectionFactory.createConnection();
        //启动连接
        connection.start();
        //通过连接创建会话对象
        //第一个参数代表是否自动提交事务，如果使用true，那么第二个参数就可以忽略
        //如果第一个为false的话，那么第二参数就会生效，代表应答的模式，我们选择自动应答即可
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //通过会话对象创建目标对象（点对对模式的queue，发布者/订阅者模式的topic）
        //Queue queue = session.createQueue("myqueue");
        Topic topic = session.createTopic("mytopic");
        //通过会话对象和目标对象创建生产者
        MessageProducer producer = session.createProducer(topic);
        //创建TextMessage对象
        TextMessage textMessage = session.createTextMessage("hello topic1");
        //通过生产者来发送消息
        producer.send(textMessage);
        System.err.println(Thread.currentThread().getName());
        //关闭各种资源
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testConsumer() throws JMSException, IOException {

        System.err.println(Thread.currentThread().getName());
        //创建connectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://10.31.161.48:61616");
        //通过工厂创建连接对象
        Connection connection = connectionFactory.createConnection();
        //启动连接
        connection.start();
        //通过连接创建会话对象
        //第一个参数代表是否自动提交事务，如果使用true，那么第二个参数就可以忽略
        //如果第一个为false的话，那么第二参数就会生效，代表应答的模式，我们选择自动应答即可
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //通过会话对象创建目标对象（点对对模式的queue，发布者/订阅者模式的topic）
        //Queue queue = session.createQueue("myqueue");
        Topic topic = session.createTopic("mytopic");
        //通过会话对象和队列创建消费者对象
        MessageConsumer consumer = session.createConsumer(topic);
        //通过消费者对象来设置监听器，内部类
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                System.err.println(Thread.currentThread().getName());
                //把父类的对象强转子类的对象
                TextMessage textMessage = (TextMessage)message;
                String str = null;
                try {
                    str = textMessage.getText();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                System.out.println(str);
            }
        });
        System.in.read();
        //关闭各种资源
        consumer.close();
        session.close();
        connection.close();
    }
}
*/
