package com.utc.api13.commun.services;



import com.utc.api13.commun.entities.ADataEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

public abstract class DataServiceTest<T extends ADataEntity> {

	protected abstract T getEntityWithoutId();
	
//	protected ADataService<T> getService() {
//		return new ADataService<T>();
//	}
	
	
//	public void testSave() {
//		T newEntity = getEntityWithoutId();
//		try {
//			getService().save(newEntity);
//		} catch (TechnicalException e) {
//			e.printStackTrace();
//		} catch (FunctionalException e) {
//			e.printStackTrace();
//		}
//	}
}
