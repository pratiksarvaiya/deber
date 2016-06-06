package com.deber.api.actions;

import java.util.List;

import com.deber.api.viewmodels.View;
import com.deber.util.Linq;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class AbstractAction implements Action {

	protected Gson getGson() {

		return new GsonBuilder()
				// .enableComplexMapKeySerialization()
				// .serializeNulls()
				// .setDateFormat(DateFormat.LONG)
				// .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
				.setPrettyPrinting().create();
	}

	protected static <E, V extends View<E>> List<E> convertAllToDataModel(List<V> lst) {
		return Linq.select(lst, r -> r.convertToDataModel());
	}

	protected static <E, V extends View<E>> List<V> convertAllToViewModel(List<E> lst, Class<V> clazz) {
		return (List<V>) Linq.select(lst, 
				r -> {
					V instance = null;
					try {
						instance = (V) clazz.newInstance();
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
					instance.copyDataModel(r);
					return instance;
				}
			);
		
	}

}
