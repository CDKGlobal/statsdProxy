statsdProxy
=========== 
An HTTP proxy for statsd - will accept stats and forward them to a configured instance. 

### Usage
There are two variables to configure:
**targetAddress**: Recipient statsd address
**targetPort**: Recipient statsd port

You can run via the Docker [container](https://registry.hub.docker.com/u/makanab/statsdproxy/):

docker run -e targetAddress=<statsd address> -e targetPort=<statsd port> -p 8080:8080 makanab/statsdproxy