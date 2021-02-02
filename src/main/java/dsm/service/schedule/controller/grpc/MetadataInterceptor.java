package dsm.service.schedule.controller.grpc;

import io.grpc.*;
import org.lognet.springboot.grpc.GRpcGlobalInterceptor;

@GRpcGlobalInterceptor
public class MetadataInterceptor implements ServerInterceptor {
    public static final Context.Key<Object> xRequestId = Context.key("xRequestId");
    public static final Context.Key<Object> spanContext = Context.key("spanContext");

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next
    ) {
        Context context = Context.current().withValues(
                xRequestId, headers.get(
                        Metadata.Key.of("x-request-id", Metadata.ASCII_STRING_MARSHALLER)),
                spanContext, headers.get(
                        Metadata.Key.of("span-context", Metadata.ASCII_STRING_MARSHALLER)));
        return Contexts.interceptCall(context, call, headers, next);
    }
}