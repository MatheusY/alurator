package br.com.alura.alurator.relfexao;

public class Reflexao {

	public ManipuladorClasse reflexaoClasse(String fqn) {
			Class<?> classe = getClasse(fqn);
			return new ManipuladorClasse(classe);
	}

	public Class<?> getClasse(String fqn) {
		try {
			Class<?> classe = Class.forName(fqn);
			return classe;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
