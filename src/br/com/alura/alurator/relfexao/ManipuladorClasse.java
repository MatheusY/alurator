package br.com.alura.alurator.relfexao;

import java.lang.reflect.Constructor;

public class ManipuladorClasse {

	private Class<?> classe;

	public ManipuladorClasse(Class<?> classe) {
		this.classe = classe;

	}

	public ManipuladorConstrutor getConstrutorBase() {
		try {
			Constructor<?> constructor = classe.getDeclaredConstructor();
			return new ManipuladorConstrutor(constructor);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public ManipuladorObjeto getCriaInstancia() {
		Object instancia = getConstrutorBase().invoca();
		return new ManipuladorObjeto(instancia);
	}

}
