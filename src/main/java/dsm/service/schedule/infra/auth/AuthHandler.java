package dsm.service.schedule.infra.auth;

import dsm.service.schedule.infra.consul.ConsulHandler;
import dsm.service.schedule.infra.openTracing.JaegerHandler;
import dsm.service.schedule.proto.*;
import dsm.service.schedule.service.aop.annotation.Tracing;
import io.grpc.ClientInterceptors;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthHandler {
    private final JaegerHandler jaegerHandler;
    private final ConsulHandler consulHandler;

    private String serviceName = "DMS.SMS.v1.service.auth";

    @Tracing(serviceName = "AuthConnection")
    public GetTeacherInformWithUUIDResponse getTeacherInform(
            GetTeacherInformWithUUIDRequest request,
            String xRequestId
    ) {
        String host = consulHandler.getServiceHost(serviceName);
        Integer port = consulHandler.getServicePort(serviceName);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        AuthTeacherGrpc.AuthTeacherBlockingStub authTeacherStub = AuthTeacherGrpc.newBlockingStub(channel);

        Metadata metadata = new Metadata();
        String spanContext = jaegerHandler.getActiveSpanContext();

        metadata.put(Metadata.Key.of("x-request-id", Metadata.ASCII_STRING_MARSHALLER), xRequestId);
        metadata.put(Metadata.Key.of("span-context", Metadata.ASCII_STRING_MARSHALLER), spanContext);

        return MetadataUtils.attachHeaders(authTeacherStub, metadata).getTeacherInformWithUUID(request);
    }

    @Tracing(serviceName = "AuthConnection")
    public GetStudentInformWithUUIDResponse getStudentInform(
            GetStudentInformWithUUIDRequest request,
            String xRequestId
    ) {
        String host = consulHandler.getServiceHost(serviceName);
        Integer port = consulHandler.getServicePort(serviceName);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        AuthStudentGrpc.AuthStudentBlockingStub authStudentStub = AuthStudentGrpc.newBlockingStub(channel);

        Metadata metadata = new Metadata();
        String spanContext = jaegerHandler.getActiveSpanContext();

        metadata.put(Metadata.Key.of("x-request-id", Metadata.ASCII_STRING_MARSHALLER), xRequestId);
        metadata.put(Metadata.Key.of("span-context", Metadata.ASCII_STRING_MARSHALLER), spanContext);

        return MetadataUtils.attachHeaders(authStudentStub, metadata).getStudentInformWithUUID(request);
    }
}
