package dsm.service.schedule.infra.auth;

import dsm.service.schedule.proto.AuthTeacherGrpc;
import dsm.service.schedule.proto.GetTeacherInformWithUUIDRequest;
import dsm.service.schedule.proto.GetTeacherInformWithUUIDResponse;
import io.grpc.ClientInterceptors;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class AuthHandler {
    public GetTeacherInformWithUUIDResponse getTeacherInform(
            GetTeacherInformWithUUIDRequest request) {
        String host = "127.0.0.1";
        Integer port = 10071;
        String xRequestId = "f9ed4675f1c53513c61a3b3b4e25b4c0";
        String spanContext = "1:1:0:0";

        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext().build();

        AuthTeacherGrpc.AuthTeacherBlockingStub authTeacherStub = AuthTeacherGrpc.newBlockingStub(channel);

        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("x-request-id", Metadata.ASCII_STRING_MARSHALLER), xRequestId);
        metadata.put(Metadata.Key.of("span-context", Metadata.ASCII_STRING_MARSHALLER), spanContext);

        return MetadataUtils.attachHeaders(authTeacherStub, metadata).getTeacherInformWithUUID(request);
    }
}
