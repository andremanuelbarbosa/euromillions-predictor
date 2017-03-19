package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.dao.IdsDao;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class IdsManager {

    private final IdsDao idsDao;

    @Inject
    public IdsManager(IdsDao idsDao) {

        this.idsDao = idsDao;
    }

    public long getNextId(Class classz) {

        return getNextId(classz.getSimpleName());
    }

    private long getNextId(String name) {

        final Long id = idsDao.getId(name);

        if (id == null) {

            idsDao.insertId(name);

            return 1;

        } else {

            final long nextId = id + 1;

            idsDao.updateId(name, nextId);

            return nextId;
        }
    }
}
