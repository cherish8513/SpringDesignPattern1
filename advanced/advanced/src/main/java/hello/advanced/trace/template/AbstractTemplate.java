package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logTrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractTemplate<T> {

    private final LogTrace logTrace;

    public T execute(String message){
        TraceStatus status = null;
        try {
            status = logTrace.begin(message);

            //로직 호출
            T result = call();

            logTrace.end(status);

            return result;
        } catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();
}
