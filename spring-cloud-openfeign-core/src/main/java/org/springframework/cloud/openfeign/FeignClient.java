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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/**
 * 接口的注解，声明应该创建一个具有该接口的REST客户端（例如，用于自动连接到另一个组件）。如果SC LoadBalancer是可用的，它将被用来平衡后端请求，负载平衡器可以使用与feign客户端相同的名称（即值）进行配置。
 *
 * Annotation for interfaces declaring that a REST client with that interface should be
 * created (e.g. for autowiring into another component). If SC LoadBalancer is available
 * it will be used to load balance the backend requests, and the load balancer can be
 * configured using the same name (i.e. value) as the feign client.
 *
 * @author Spencer Gibb
 * @author Venil Noronha
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FeignClient {

	/**
	 * The name of the service with optional protocol prefix. Synonym for {@link #name()
	 * name}. A name must be specified for all clients, whether or not a url is provided.
	 * Can be specified as property key, eg: ${propertyKey}.
	 * @return the name of the service with optional protocol prefix
	 */
	@AliasFor("name")
	String value() default "";

	/**
	 * 如果存在的话，这将被用作Bean的名字而不是name，但不会被用作服务id。
	 * 就相当于别名
	 * * <p></p>
	 * This will be used as the bean name instead of name if present, but will not be used
	 * as a service id.
	 * @return bean name instead of name if present
	 */
	String contextId() default "";

	/**
	 * @return The service id with optional protocol prefix. Synonym for {@link #value()
	 * value}.  带有可选的协议前缀的服务ID。值的同义词。
	 */
	@AliasFor("value")
	String name() default "";

	/**
	 * @return the <code>@Qualifier</code> value for the feign client.
	 */
	String qualifier() default "";

	/**
	 * 调用的URL
	 *
	 * @return an absolute URL or resolvable hostname (the protocol is optional).
	 */
	String url() default "";

	/**
	 * @return whether 404s should be decoded instead of throwing FeignExceptions
	 */
	boolean decode404() default false;

	/**
	 * A custom configuration class for the feign client. Can contain override
	 * <code>@Bean</code> definition for the pieces that make up the client, for instance
	 * {@link feign.codec.Decoder}, {@link feign.codec.Encoder}, {@link feign.Contract}.
	 *
	 * @see FeignClientsConfiguration for the defaults
	 * @return list of configurations for feign client
	 */
	Class<?>[] configuration() default {};

	/**
	 * Fallback class for the specified Feign client interface. The fallback class must
	 * implement the interface annotated by this annotation and be a valid spring bean.
	 * @return fallback class for the specified Feign client interface
	 */
	Class<?> fallback() default void.class;

	/**
	 * Define a fallback factory for the specified Feign client interface. The fallback
	 * factory must produce instances of fallback classes that implement the interface
	 * annotated by {@link FeignClient}. The fallback factory must be a valid spring bean.
	 *
	 * @see FallbackFactory for details.
	 * @return fallback factory for the specified Feign client interface
	 */
	Class<?> fallbackFactory() default void.class;

	/**
	 * @return path prefix to be used by all method-level mappings.
	 */
	String path() default "";

	/**
	 * @return whether to mark the feign proxy as a primary bean. Defaults to true.
	 */
	boolean primary() default true;

}
