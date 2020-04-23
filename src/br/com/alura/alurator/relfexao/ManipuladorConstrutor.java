package br.com.alura.alurator.relfexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ManipuladorConstrutor {

	private Constructor<?> constructor;

	public ManipuladorConstrutor(Constructor<?> constructor) {
		this.constructor = constructor;
	}

	public Object invoca() {
		try {
			return constructor.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
			return new RuntimeException(e);
		} catch (InvocationTargetException e) {
			return new RuntimeException("Erro no construtor", e.getTargetException());
		}
	}

}
