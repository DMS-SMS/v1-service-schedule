package dsm.service.schedule.core.usecase;

import dsm.service.schedule.core.Mapper;

public interface UseCaseExecutor {
    <I extends UseCase.InputValues, O extends UseCase.OutputValues, Res> Res execute(
            UseCase<I, O> useCase, I input, O output, Mapper<O, Res> outputMapper);
}
