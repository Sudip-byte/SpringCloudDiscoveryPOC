package com.sudip.ApiGateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory {

	public static class Config {
		// Put config properties here
	}

	@Override
	public GatewayFilter apply(Object config) {
		// TODO Auto-generated method stub
		return null;
	}

}
