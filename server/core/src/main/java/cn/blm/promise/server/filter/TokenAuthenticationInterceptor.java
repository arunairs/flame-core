package cn.blm.promise.server.filter;

import cn.blm.promise.server.annotation.Token;
import cn.blm.promise.server.repository.entity.User;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jiaan.zhang@oracle.com
 * @date 08/10/2016 7:52 PM
 */
public class TokenAuthenticationInterceptor extends AbstractAuthenticationInterceptor
{
	@Override
	protected boolean isAuthenticated(HttpServletRequest request, HandlerMethod handlerMethod)
	{
		String token = getToken(request);
		return true;
	}

	@Override
	protected boolean isExcluded(HttpServletRequest request, HandlerMethod handlerMethod)
	{
		Class clazz = handlerMethod.getBeanType();
		if (clazz.isAnnotationPresent(Token.class))
			return false;
		Method method = handlerMethod.getMethod();
		if (method.isAnnotationPresent(Token.class))
			return false;
		return true;
	}

	@Override
	protected void requestAccepted(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("user", null);
	}

	@Override
	protected void requestRefused(HttpServletRequest request, HttpServletResponse response)
	{

	}

	protected String getToken(HttpServletRequest request)
	{
		String token = request.getHeader("X-Auth-Token");
		return token;
	}

	protected boolean isTokenRequired(HandlerMethod handlerMethod)
	{
		Annotation annotation = handlerMethod.getMethod().getAnnotation(Token.class);
		try
		{
			return (boolean) Token.class.getDeclaredMethod("required").invoke(annotation);
		}
		catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ignored)
		{
		}
		return true;
	}
}