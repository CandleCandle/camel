/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.camel.example.metrics.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.codahale.metrics.Meter;
import org.apache.camel.Exchange;
import org.apache.camel.RuntimeExchangeException;

@ApplicationScoped
public class UnreliableService {

    @Inject
    private Meter attempt;

    public void unreliable(Exchange exchange) {
        attempt.mark();

        if (Math.random() < 0.5) {
            throw new RuntimeExchangeException("Random failure", exchange);
        }
    }
}
