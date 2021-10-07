/*
 * Copyright 2013-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.openfeign;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * AnyNestedCondition 会分析 @Conditional，将它们用"OR"进行合并。所以多个条件只要有一个为true就行
 */
class FeignCircuitBreakerDisabledConditions extends AnyNestedCondition {

	FeignCircuitBreakerDisabledConditions() {
		super(ConfigurationPhase.PARSE_CONFIGURATION);
	}

	/**
	 * 不存在这个Bean， 这个为true
	 */
	@ConditionalOnMissingClass("org.springframework.cloud.client.circuitbreaker.CircuitBreaker")
	static class CircuitBreakerClassMissing {

	}


	@ConditionalOnProperty(value = "feign.circuitbreaker.enabled", havingValue = "false", matchIfMissing = true)
	static class CircuitBreakerDisabled {

	}

}
