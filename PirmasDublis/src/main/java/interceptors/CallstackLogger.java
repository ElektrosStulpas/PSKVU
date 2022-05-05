package interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@LoggerAnnotation
public class CallstackLogger implements Serializable {
    @AroundInvoke
    public Object logCall(InvocationContext ctx) throws Exception{
        System.out.println("Method called: " + ctx.getMethod());
        return ctx.proceed();
    }
}
