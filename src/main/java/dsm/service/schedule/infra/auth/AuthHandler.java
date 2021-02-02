package dsm.service.schedule.infra.auth;

import dsm.service.schedule.grpc.MetadataInterceptor;
import dsm.service.schedule.infra.openTracing.JaegerHandler;
import dsm.service.schedule.proto.*;
import dsm.service.schedule.service.aop.annotation.Tracing;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class AuthHandler {
    private final JaegerHandler jaegerHandler;
    private final ConsulHandler consulHandler;

    private String serviceName = "DMS.SMS.v1.service.auth";

    private String host = null;
    private Integer port = null;

    public void updateServiceAddress() {
        host = consulHandler.getServiceHost(serviceName);
        port = consulHandler.getServicePort(serviceName);
    }

    @Tracing(serviceName = "AuthConnection (getTeacherInform)")
    public GetTeacherInformWithUUIDResponse getTeacherInform(
            GetTeacherInformWithUUIDRequest request
    ) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        AuthTeacherGrpc.AuthTeacherBlockingStub authTeacherStub = AuthTeacherGrpc.newBlockingStub(channel);

        Metadata metadata = new Metadata();
        String xRequestId = (String) MetadataInterceptor.xRequestId.get();
        String spanContext = jaegerHandler.getActiveSpanContext();

        metadata.put(Metadata.Key.of("x-request-id", Metadata.ASCII_STRING_MARSHALLER), xRequestId);
        metadata.put(Metadata.Key.of("span-context", Metadata.ASCII_STRING_MARSHALLER), spanContext);

        GetTeacherInformWithUUIDResponse response = MetadataUtils.attachHeaders(authTeacherStub, metadata).getTeacherInformWithUUID(request);
        channel.shutdown();
        channel.awaitTermination(3, TimeUnit.SECONDS);
        return response;
    }

    @Tracing(serviceName = "AuthConnection (getStudentInform)")
    public GetStudentInformWithUUIDResponse getStudentInform(
            GetStudentInformWithUUIDRequest request
    ) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        AuthStudentGrpc.AuthStudentBlockingStub authStudentStub = AuthStudentGrpc.newBlockingStub(channel);

        Metadata metadata = new Metadata();
        String xRequestId = (String) MetadataInterceptor.xRequestId.get();
        String spanContext = jaegerHandler.getActiveSpanContext();

        metadata.put(Metadata.Key.of("x-request-id", Metadata.ASCII_STRING_MARSHALLER), xRequestId);
        metadata.put(Metadata.Key.of("span-context", Metadata.ASCII_STRING_MARSHALLER), spanContext);
        GetStudentInformWithUUIDResponse response = MetadataUtils.attachHeaders(authStudentStub, metadata).getStudentInformWithUUID(request);
        channel.shutdown();
        channel.awaitTermination(3, TimeUnit.SECONDS);
        return response;
    }
}
