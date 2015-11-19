package com.utc.api13.commun.services;

import com.utc.api13.commun.entities.ADataEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

import junit.framework.TestCase;

public abstract class DataServiceTest<T extends ADataEntity> extends TestCase{

	protected abstract T getEntityWithoutId();
	
	protected abstract ADataService<T> getService();
	
	public void testSave() {
		T newEntity = getEntityWithoutId();
		try {
			getService().save(newEntity);
			assertNotNull(newEntity);
			assertNotNull(newEntity.getId());
		} catch (TechnicalException e) {
			e.printStackTrace();
		} catch (FunctionalException e) {
			e.printStackTrace();
		}
	}
}
