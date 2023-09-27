/*
 * Copyright 2002-2020 the original author or authors.
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

package org.springframework.web.servlet.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.convert.ConversionService;
import org.springframework.util.Assert;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Interceptor that places the configured {@link ConversionService} in request scope
 * so it's available during request processing. The request attribute name is
 * "org.springframework.core.convert.ConversionService", the value of
 * {@code ConversionService.class.getName()}.
 *
 * <p>Mainly for use within JSP tags such as the spring:eval tag.
 *
 * 转换服务暴露拦截器，会向每个请求添加一个转换服务属性，将转换服务添加到每个请求中
 *
 * @author Keith Donald
 * @since 3.0.1
 */
public class ConversionServiceExposingInterceptor implements HandlerInterceptor {

	/**
	 * 转换服务
	 */
	private final ConversionService conversionService;


	/**
	 * Creates a new {@link ConversionServiceExposingInterceptor}.
	 * @param conversionService the conversion service to export to request scope when this interceptor is invoked
	 */
	public ConversionServiceExposingInterceptor(ConversionService conversionService) {
		Assert.notNull(conversionService, "The ConversionService may not be null");
		this.conversionService = conversionService;
	}


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws ServletException, IOException {

		// 添加到请求中
		request.setAttribute(ConversionService.class.getName(), this.conversionService);
		return true;
	}

}
