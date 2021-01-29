package dsm.service.schedule.controller;

import dsm.service.schedule.core.Mapper;
import dsm.service.schedule.core.usecase.UseCase;
import dsm.service.schedule.core.usecase.UseCaseExecutor;

public class UseCaseExecutorImpl implements UseCaseExecutor {
    @Override
    public <I extends UseCase.InputValues, O extends UseCase.OutputValues, Res> Res
    execute(UseCase<I, O> useCase, I input, O output, Mapper<O, Res> outputMapper) {
        return outputMapper.map(useCase.execute(input));
    }
}
