package hpe.demo;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@EnableZuulProxy
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayMain {

	public static void main(String[] args) {
		SpringApplication.run(GatewayMain.class, args);
	}

	@Bean
	public FilterRegistrationBean corsFilter() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.addAllowedOrigin("*");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("*");
	    source.registerCorsConfiguration("/**", config);
	    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
	    bean.setOrder(0);
	    return bean;
	}

}

@Component
class MyFilter extends ZuulFilter {
	private static final Logger LOG = LoggerFactory.getLogger(MyFilter.class);

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		String appkey = request.getHeader("appkey");
		LOG.info("appkey: " + appkey);
		String token = request.getHeader("token");
		LOG.info("token: " + token);
		String uri = request.getRequestURI();
		LOG.info(uri);

		if (appkey == null || appkey.equals("") || token == null || token.equals("")) {
			sendErrorResponse(ctx, 401, "missing token or appkey");
			LOG.info("missing token or appkey");
			return null;
		}

		if (!TokenManager.varifyToken(appkey, token)) {
			sendErrorResponse(ctx, 403, "token is invalid");
			LOG.info("token is invalid");
			return null;
		}

		return null;
	}

	private void sendErrorResponse(RequestContext ctx, int statusCode, String errMsg) {
		ctx.setSendZuulResponse(false);
		ctx.setResponseStatusCode(statusCode);
		try {
			ctx.getResponse().getWriter().write(errMsg);
		} catch (Exception e) {
		}
	}
}
