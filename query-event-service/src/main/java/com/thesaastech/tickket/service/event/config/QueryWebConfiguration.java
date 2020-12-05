/*
 * Copyright (c) 2020-2020. AxonIQ
 *
 * Licensed under the Apache License, Version 2.0 (the &quot;License&quot;)
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an &quot;AS IS&quot; BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.thesaastech.tickket.service.event.config;


import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryWebConfiguration {

    /*********************************************************/
    /* Register a dispatch interceptors on the query gateway */

    /*********************************************************/

    @Autowired
    public void reactiveCommandGatewayConfiguration(ReactorQueryGateway reactorQueryGateway) {
        reactorQueryGateway.registerDispatchInterceptor(new LoggingReactorMessageDispatchInterceptor<>());
    }
}
