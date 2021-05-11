# v1-service-schedule
`v1-service-schedule`은 스케줄관리 및 시간표 관련 서비스 입니다.<br/>
## What Used
- SpringBoot (without Tomcat)
- Spring Boot - gRPC
- Consul
- Jaeger Client
- MySQL (Spring Boot JPA)
## Tree
```
📦 v1-service-schedule
├─ .github
│  └─ workflows
│     └─ docker-build.yml
├─ .gitignore
├─ Dockerfile
├─ LICENSE
├─ README.md
├─ build.gradle
├─ docker-compose.yml
├─ gradle
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradlew
├─ gradlew.bat
├─ service-schedule-deployment.yaml
├─ settings.gradle
└─ src
   └─ main
      ├─ java
      │  └─ dsm
      │     └─ service
      │        └─ schedule
      │           ├─ ScheduleApplication.java
      │           ├─ controller
      │           │  ├─ UseCaseExecutorImpl.java
      │           │  ├─ grpc
      │           │  │  ├─ MetadataInterceptor.java
      │           │  │  ├─ ScheduleService.java
      │           │  │  └─ ScheduleServiceGrpc.java
      │           │  └─ mapper
      │           │     ├─ CreateScheduleInputMapper.java
      │           │     ├─ CreateScheduleOutputMapper.java
      │           │     ├─ DeleteScheduleInputMapper.java
      │           │     ├─ DeleteScheduleOutputMapper.java
      │           │     ├─ GetScheduleInputMapper.java
      │           │     ├─ GetScheduleOutputMapper.java
      │           │     ├─ GetTimetableInputMapper.java
      │           │     ├─ GetTimetableOutputMapper.java
      │           │     ├─ GetTimetablesInputMapper.java
      │           │     ├─ GetTimetablesOutputMapper.java
      │           │     ├─ UpdateScheduleInputMapper.java
      │           │     └─ UpdateScheduleOutputMapper.java
      │           ├─ core
      │           │  ├─ Mapper.java
      │           │  ├─ domain
      │           │  │  ├─ entity
      │           │  │  │  ├─ Account.java
      │           │  │  │  ├─ Schedule.java
      │           │  │  │  ├─ Timetable.java
      │           │  │  │  └─ enums
      │           │  │  │     └─ AccountType.java
      │           │  │  ├─ exception
      │           │  │  │  ├─ BusinessException.java
      │           │  │  │  ├─ NotFoundException.java
      │           │  │  │  ├─ ServerErrorException.java
      │           │  │  │  └─ UnauthorizedException.java
      │           │  │  └─ repository
      │           │  │     ├─ AccountRepository.java
      │           │  │     ├─ ScheduleRepository.java
      │           │  │     └─ TimetableRepository.java
      │           │  └─ usecase
      │           │     ├─ UseCase.java
      │           │     ├─ UseCaseExecutor.java
      │           │     ├─ account
      │           │     │  └─ GetAccountUseCase.java
      │           │     ├─ schedule
      │           │     │  ├─ CreateScheduleUseCase.java
      │           │     │  ├─ DeleteScheduleUseCase.java
      │           │     │  ├─ GetScheduleUseCase.java
      │           │     │  └─ UpdateScheduleUseCase.java
      │           │     └─ timetable
      │           │        ├─ GetTimetableUseCase.java
      │           │        └─ GetTimetablesUseCase.java
      │           ├─ data
      │           │  ├─ adapter
      │           │  │  └─ repository
      │           │  │     ├─ AccountRepositoryImpl.java
      │           │  │     ├─ ScheduleRepositoryImpl.java
      │           │  │     ├─ TimetableRepositoryImpl.java
      │           │  │     └─ mapper
      │           │  │        ├─ AccountRepositoryMapper.java
      │           │  │        ├─ ScheduleRepositoryMapper.java
      │           │  │        └─ TimetableRepositoryMapper.java
      │           │  ├─ api
      │           │  │  └─ school
      │           │  │     └─ SchoolApiService.java
      │           │  ├─ db
      │           │  │  └─ jpa
      │           │  │     ├─ model
      │           │  │     │  ├─ ScheduleModel.java
      │           │  │     │  └─ TimetableModel.java
      │           │  │     └─ repository
      │           │  │        ├─ JpaScheduleRepository.java
      │           │  │        └─ JpaTimetableRepository.java
      │           │  └─ grpc
      │           │     └─ auth
      │           │        └─ AuthService.java
      │           └─ infra
      │              ├─ aop
      │              │  ├─ AspectJService.java
      │              │  └─ annotation
      │              │     └─ Tracing.java
      │              ├─ config
      │              │  ├─ db
      │              │  │  └─ mysql
      │              │  │     ├─ MysqlConfigure.java
      │              │  │     └─ MysqlInterceptor.java
      │              │  └─ spring
      │              │     ├─ ApplicationContextProvider.java
      │              │     ├─ BeanUtils.java
      │              │     ├─ ShutdownEventListener.java
      │              │     └─ StartConfig.java
      │              ├─ consul
      │              │  └─ ConsulService.java
      │              ├─ event
      │              │  └─ AddressUpdateEventService.java
      │              ├─ grpc
      │              │  └─ GrpcService.java
      │              └─ jaeger
      │                 └─ JaegerService.java
      ├─ proto
      │  ├─ auth_student.proto
      │  ├─ auth_teacher.proto
      │  ├─ schedule-event.proto
      │  └─ schedule.proto
      └─ resources
         └─ application.yml
```
©generated by [Project Tree Generator](https://woochanleee.github.io/project-tree-generator)
