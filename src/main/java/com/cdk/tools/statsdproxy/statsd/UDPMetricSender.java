package com.cdk.tools.statsdproxy.statsd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;

@Component
public class UDPMetricSender {

    private static final Logger logger = LoggerFactory.getLogger(UDPMetricSender.class);

    @Value("${targetAddress}")
    private String targetAddress;

    @Value("${targetPort}")
    private String targetPort;

    @Async
    public void sendMetrics(String metricName, String metricValue, String type) throws Exception {
        logger.info("Sending {} to {}:{}", getData(metricName, metricValue, type), targetAddress, targetPort);
        InetAddress IPAddress = InetAddress.getByName(targetAddress);
        int port = Integer.valueOf(targetPort);
        String msg = getData(metricName, metricValue, type);
        byte[] sendData = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(sendData, sendData.length, IPAddress, port);
        DatagramSocket socket = new DatagramSocket();
        socket.send(packet);
    }

    private String getData(String metricName, String metricValue, String type) {
        StringBuilder data = new StringBuilder();
        data.append(metricName)
            .append(":")
            .append(metricValue)
            .append("|")
            .append(type);
        return data.toString();
    }
}
