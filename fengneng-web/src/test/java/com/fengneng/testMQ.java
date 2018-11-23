package com.fengneng;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;


/**
 * Created by liuxu on 2018/5/24.
 */
public class testMQ {
    private static final Logger LOGGER= LoggerFactory.getLogger(testMQ.class);
    public static void main(String[] args) throws Exception {
        testConsumer();
    }
    @Test
    public void testMq() throws JMSException {
        //创建一个连接工厂对象ConnectionFactory,并指定mq服务的ip以及端口号
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.39.131:61616");
        //创建一个连接对象
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //创建一个连接会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-queue");
        MessageProducer producer = session.createProducer(queue);
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText("hello mqsfsdfs");
        producer.send(textMessage);
        session.close();
        producer.close();
        connection.close();


    }

    public static void testConsumer() throws Exception {
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.39.131:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-queue");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(message instanceof TextMessage){
                    TextMessage textMessage= (TextMessage) message;
                    try {
                        System.out.println(textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        LOGGER.debug("服务器启动");
        System.in.read();//等待键盘输入，如果键盘没有输入，就会一直等待下去
        consumer.close();
        session.close();
        connection.close();

    }
}
