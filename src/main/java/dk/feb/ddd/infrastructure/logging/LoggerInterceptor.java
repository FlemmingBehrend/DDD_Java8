package dk.feb.ddd.infrastructure.logging;

import org.slf4j.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.ArrayList;
import java.util.List;

@Interceptor @Logged
public class LoggerInterceptor {

    @Inject
    Logger logger;

    @AroundInvoke
    public Object log(InvocationContext invCon) throws Exception {
        if (logger.isTraceEnabled()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Calling method " + invCon.getMethod().getName());
            List<String> parameterList = new ArrayList<String>();
            if (invCon.getParameters().length > 0) {
                sb.append(" with parameters:");
                Class<?>[] parameterTypes = invCon.getMethod().getParameterTypes();
                Object[] parameters = invCon.getParameters();
                for (int i = 0; i < parameters.length; i++) {
                    parameterList.add("\t" + parameterTypes[i].getCanonicalName() + " = " + parameters[i]);
                }
            } else {
                sb.append(" with no parameters");
            }
            logger.trace(sb.toString());
            for (String string : parameterList) {
                logger.trace(string);
            }
        }
        return invCon.proceed();
    }

}