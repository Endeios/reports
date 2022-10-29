package io.endeios.reports;

import com.rabbitmq.client.AMQP;

public class MyBasicProperties extends AMQP.BasicProperties {
    @Override
    public String getContentType() {
        return "text/plain";
    }

    @Override
    public String getContentEncoding() {
        return "UTF-8";
    }


    @Override
    public Integer getDeliveryMode() {
        return 2;
    }

    @Override
    public Integer getPriority() {
        return 0;
    }


}
