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
package org.zhangyinhao.om.service.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * <p>
 *     1.核心线程数默认CPU的数量
 *     2.最大线程数为CPU的两倍
 *     3.队列长度默认128，队列长度并不是越大越好，如果单个任务中有大对象，占用较高的堆内存建议配置的小一点，配置越大阻塞时候占用的内存越大
 *     4.线程空闲回收，默认配置2小时空闲回收，最少保留核心线程数量，是否需要回收线程 按照实际情况而定
 *     5.拒绝策略，四种阻塞队列满之后的处理方式
 *      new ThreadPoolExecutor.CallerRunsPolicy()); 使用调用线程处理，比如是main调用的线程池，用main线程执行
 *      new ThreadPoolExecutor.DiscardOldestPolicy());把阻塞队列首元素丢掉，执行当前线程
 *      new ThreadPoolExecutor.DiscardPolicy());直接丢掉当前线程
 *      new AbortPolicy());//直接抛出异常
 *      如果有特殊需求可以自定义策略，比如拒绝了缓存起来后续空闲再执行、告警等 new TestRejected();
 *
 *
 * </p>
 */
@Configuration
@Slf4j
public class ThreadPoolConfig {
    /**
     * 常用的线程池创建
     *
     * @return
     */
    @Bean("commonsThreadPoolExecutor")
    ThreadPoolExecutor commonsThreadPoolExecutor() {
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("commonsThread-pool-%d").build();
        int cpuCores = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(cpuCores, cpuCores * 2, 2L,
                TimeUnit.HOURS, new LinkedBlockingQueue<>(1280), factory,
                //拒绝策略按照实际情况  通常情况下 CallerRunsPolicy 能满足大部分需求
                new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    class TestRejected implements RejectedExecutionHandler {
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            log.warn(r.toString() + "执行了拒绝策略");
            //拒绝后其他处理
        }
    }

}
