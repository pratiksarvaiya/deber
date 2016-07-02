package com.deber.api.actions;

import java.util.List;
import java.util.stream.Collectors;

import com.deber.api.viewmodels.View;
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
		return lst.stream().map(r -> r.convertToDataModel()).collect(Collectors.toList());
	}

	protected static <E, V extends View<E>> List<V> convertAllToViewModel(List<E> lst, Class<V> clazz) {
		return lst.stream().map(r -> {
			V instance = null;
			try {
				instance = (V) clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			instance.copyDataModel(r);
			return instance;
		}).collect(Collectors.toList());

	}

}
