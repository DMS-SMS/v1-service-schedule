FROM java:8
COPY ./build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar", "-Xmx300M","/app.jar", "-dJAEGER_AGENT_HOST=${JAEGER_AGENT_HOST} -dJAEGER_AGENT_PORT=${JAEGER_AGENT_PORT}"]