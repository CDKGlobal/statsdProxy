package com.cdk.tools.statsdproxy.web;

import com.cdk.tools.statsdproxy.statsd.UDPMetricSender;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class MetricsController {

    @Resource
    private UDPMetricSender sender;

    @RequestMapping("/submit")
    public ResponseEntity<String> receiveStats(@RequestParam String m,
                                               @RequestParam String v,
                                               @RequestParam String t) {
        if (m == null || m.isEmpty()) {
            return new ResponseEntity<>("You must send the m parameter.", HttpStatus.BAD_REQUEST);
        }
        if (v == null || v.isEmpty()) {
            return new ResponseEntity<>("You must send the v parameter.", HttpStatus.BAD_REQUEST);
        }
        if (t == null || t.isEmpty()) {
            return new ResponseEntity<>("You must send the t parameter.", HttpStatus.BAD_REQUEST);
        }
        try {
            sender.sendMetrics(m, v, t);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
