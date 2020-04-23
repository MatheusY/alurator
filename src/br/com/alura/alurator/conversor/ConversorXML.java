package br.com.alura.alurator.conversor;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Objects;

import br.com.alura.alurator.anotacao.NomeTagXml;

public class ConversorXML {

	public String converte(Object objeto) {
		try {
			Class<?> classeObjeto = objeto.getClass();
			StringBuilder xmlBuilder = new StringBuilder();

			if (objeto instanceof Collection) {
				Collection<?> colecao = (Collection<?>) objeto;
				xmlBuilder.append("<lista>");
				for (Object o : colecao) {
					String xml = converte(o);

					xmlBuilder.append(xml);
				}
				xmlBuilder.append("</lista>");
			} else {
				NomeTagXml anotacaoClasse = classeObjeto.getDeclaredAnnotation(NomeTagXml.class);
				String nomeClasse = Objects.isNull(anotacaoClasse) ? classeObjeto.getName() : anotacaoClasse.value();
				
				xmlBuilder.append("<" + nomeClasse + ">");

				for (Field atributo : classeObjeto.getDeclaredFields()) {
					atributo.setAccessible(true);
					NomeTagXml anotacaoAtributo = atributo.getDeclaredAnnotation(NomeTagXml.class);
					
					String nomeAtributo = Objects.isNull(anotacaoAtributo) ? atributo.getName() : anotacaoAtributo.value();
					Object valorAtributo = atributo.get(objeto);
					xmlBuilder.append("<" + nomeAtributo + ">");
					xmlBuilder.append(valorAtributo);
					xmlBuilder.append("</" + nomeAtributo + ">");
				}
					
				xmlBuilder.append("</" + nomeClasse + ">");

			}
			return xmlBuilder.toString();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro na geração do XML!");
		}

	}

}