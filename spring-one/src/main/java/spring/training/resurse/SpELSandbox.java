package spring.training.resurse;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Data
public class SpELSandbox {
	private String stringProperty;
	private Integer intProperty;
	private Boolean booleanProperty;
	private List<String> stringList;
	private List<SpELSandbox> childList;

	public SpELSandbox() {
	}

	public SpELSandbox(Integer intProperty, String stringProperty) {
		this.stringProperty = stringProperty;
		this.intProperty = intProperty;
	}

	public String randomToken() {
		return UUID.randomUUID().toString();
	}
}


@Configuration
class SpelConfiguration {
	@Bean
	public SpELSandbox sandbox(
			@Value("#{T(java.lang.Math).random() lt 0.5f?'A Beautiful Day':null}") String day) {
		SpELSandbox box = new SpELSandbox();
		box.setStringProperty(day);
		box.setBooleanProperty(true);
		box.setIntProperty(10);
		box.setChildList(Arrays.asList(
				new SpELSandbox(10, "One"),
				new SpELSandbox(20, "Two"),
				new SpELSandbox(30, "Three")
				));
		System.out.println("Sandbox: " + box);
		return box;
	}
}


@Component
class UsingSpells {
	@Value("#{sandbox.stringProperty?.toUpperCase()?:'Ploua'}") // Optional.map.orElse('ploua')
	private String s1;
	@Value("#{sandbox.intProperty + 1}")
	private String s2;
	@Value("#{sandbox.childList.?[intProperty gt 15]}") //.stream().filter()
	private List<SpELSandbox> children;
	@Value("#{sandbox.childList.?[intProperty gt 15].![intProperty]}") //.stream().map()
	private List<Integer> childrenInts;

	@PostConstruct
	public void show() {
		System.out.println("-------------SPEL-----------");
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(children);
		System.out.println(childrenInts);
		System.out.println("-------------END SPEL-----------");
	}

}