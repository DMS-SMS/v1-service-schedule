package dsm.service.schedule.data.grpc.auth;

import dsm.service.schedule.controller.grpc.MetadataInterceptor;
import dsm.service.schedule.infra.consul.ConsulService;
import dsm.service.schedule.infra.jaeger.JaegerService;
import dsm.service.schedule.proto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class AuthService {
    private final JaegerService jaegerService;
    private final ConsulService consulService;

    private final String serviceName = "DMS.SMS.v1.service.auth";

    private String host = null;
    private Integer port = null;

    public void updateServiceAddress() {
        host = consulService.getServiceHost(serviceName);
        port = consulService.getServicePort(serviceName);
    }

    public GetStudentInformWithUUIDResponse getStudentInform(String studentUuid, String accountUuid) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        AuthStudentGrpc.AuthStudentBlockingStub authStudentStub = AuthStudentGrpc.newBlockingStub(channel);

        Metadata metadata = new Metadata();
        String xRequestId = (String) MetadataInterceptor.xRequestId.get();
        String spanContext = jaegerService.getActiveSpanContext();

        metadata.put(Metadata.Key.of("x-request-id", Metadata.ASCII_STRING_MARSHALLER), xRequestId);
        metadata.put(Metadata.Key.of("span-context", Metadata.ASCII_STRING_MARSHALLER), spanContext);

        GetStudentInformWithUUIDRequest request = GetStudentInformWithUUIDRequest.newBuilder()
                .setUUID(accountUuid)
                .setStudentUUID(studentUuid)
                .build();

        GetStudentInformWithUUIDResponse response = MetadataUtils.attachHeaders(authStudentStub, metadata).getStudentInformWithUUID(request);

        channel.shutdown();
        channel.awaitTermination(3, TimeUnit.SECONDS);

        return response;
    }

    public GetTeacherInformWithUUIDResponse getTeacherInform(String teacherUuid, String accountUuid) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        AuthTeacherGrpc.AuthTeacherBlockingStub authTeacherStub = AuthTeacherGrpc.newBlockingStub(channel);

        Metadata metadata = new Metadata();
        String xRequestId = (String) MetadataInterceptor.xRequestId.get();
        String spanContext = jaegerService.getActiveSpanContext();

        metadata.put(Metadata.Key.of("x-request-id", Metadata.ASCII_STRING_MARSHALLER), xRequestId);
        metadata.put(Metadata.Key.of("span-context", Metadata.ASCII_STRING_MARSHALLER), spanContext);

        GetTeacherInformWithUUIDRequest request = GetTeacherInformWithUUIDRequest.newBuilder()
                .setUUID(accountUuid)
                .setTeacherUUID(teacherUuid)
                .build();

        GetTeacherInformWithUUIDResponse response = MetadataUtils.attachHeaders(authTeacherStub, metadata).getTeacherInformWithUUID(request);

        channel.shutdown();
        channel.awaitTermination(3, TimeUnit.SECONDS);

        return response;
    }
}
