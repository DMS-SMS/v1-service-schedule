package dsm.service.schedule.infra.implementation.service;

import dsm.service.schedule.domain.service.UuidService;

public class UuidServiceImpl implements UuidService {
    @Override
    public boolean checkUuid(String uuid) {
        return false;
    }

    @Override
    public String generateRandomKey() {
        return null;
    }

    @Override
    public String generateUuid() {
        return null;
    }
}
