package com.thesaastech.tickket.service.event.config;

import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandWebConfiguration {

    /***********************************************************/
    /* Register a dispatch interceptors on the command gateway */
    /***********************************************************/

    @Autowired
    public void reactiveCommandGatewayConfiguration(ReactorCommandGateway reactorCommandGateway) {
        reactorCommandGateway.registerDispatchInterceptor(new LoggingReactorMessageDispatchInterceptor<>());
    }

    @Bean
    SpringAggregateSnapshotterFactoryBean springAggregateSnapshotterFactoryBean() {
        return new SpringAggregateSnapshotterFactoryBean();
    }
}
