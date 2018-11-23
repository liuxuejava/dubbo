package com.fengneng;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

/**
 * Created by liuxu on 2018/5/25.
 */
public class testspringMQ {
    @Test
    public void testqueue(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
        JmsTemplate template = ac.getBean(JmsTemplate.class);
        Destination destination = (Destination) ac.getBean("test-queue");
        template.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("spring test mq");
                return textMessage;
            }
        });
    }
}
