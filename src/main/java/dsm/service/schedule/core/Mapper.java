package dsm.service.schedule.core;

public abstract class Mapper<I, O> {
    public abstract O map(I input);
}
