package com.cdk.tools.statsdproxy.statsd;

import com.cdk.tools.statsdproxy.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { App.class } )
public class UDPMetricSenderTest {

    @Resource
    private UDPMetricSender sender;

    @Test
    public void testSendMetrics() throws Exception {
        sender.sendMetrics("metricName", "metricValue", "s");
    }

}