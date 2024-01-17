package org.zhangyinhao.om.service.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;


@ConditionalOnProperty(prefix = "spring.kafka.cluster01.consumer", value = "enabled", matchIfMissing = false)
@Service
@Slf4j
public class Cluster01DemoMessageConsumer {


    @KafkaListener(topics = "${spring.kafka.cluster01.consumer.demo-topic}", containerFactory = "kafkaCluster01ContainerFactory")
    public void consumerSingle(List<String> message) {
        log.info("开始消费Kafka数据：监听到Kafka消息数据条数={}", message.size());
    }

}
