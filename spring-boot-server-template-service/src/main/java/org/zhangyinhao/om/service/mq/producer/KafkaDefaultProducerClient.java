/**
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.zhangyinhao.om.service.mq.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaDefaultProducerClient {
    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;


    public boolean sendMessage(String topic, Object bean) {
        try {
            SendResult<Object, Object> sendResult = kafkaTemplate.send(topic, bean).get();
            log.debug("Kafka 发送到Topic={},数据={},partition={},offset={}", topic, sendResult.getProducerRecord().value(),
                    sendResult.getRecordMetadata().partition()
                    , sendResult.getRecordMetadata().offset());
            return true;
        } catch (Exception e) {
            log.error("kafka send message error,topic={}", topic, e);
        }
        return false;
    }

}
