package victor.training.springdemo.first;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SpringBootApplication
public class SpringDemoApplication implements CommandLineRunner {

	private JdbcTemplate jdbcTemplate;
	private TransactionTemplate transactionTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}
//	@Autowired
	private A a;

	@Autowired
	public void init(A a, B b) {
		this.a = a;
		System.out.println(b);
	}

	@Autowired
	public void initTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Autowired
	public void initTemplate(PlatformTransactionManager dataSource) {
		this.transactionTemplate = new TransactionTemplate(dataSource);
		transactionTemplate.setPropagationBehavior(Propagation.REQUIRES_NEW.value());
//		transactionTemplate.execute(() -> {});
//		transactionTemplate.execute(() -> {});
//		transactionTemplate.execute(() -> {});
	}

	@Autowired
	ApplicationContext ctx;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello Spring!");
		A a = ctx.getBean(A.class);
		a.f();
	}
}

@Component
@Retention(RetentionPolicy.RUNTIME)
@interface Facade {
}

//@Service
//@Controller
//@RestController
//@Repository
//@Component
@Facade
class A {
	private final B b;
	public A(B b) {
		this.b = b;
	}
	public void f() {
		b.g();
	}
}
@Service // o singura instanta din B
@RequiredArgsConstructor
class B {
	private final C c;

	public void g() {
		System.out.println("Aici in beci vb cu " + c.getClass());
	}
}

interface C {}


// uneori
@Service
@Primary
class CSotia implements C {
}
// alteori
@Service
@Profile("local") // NICIODATA nu defini profilul PROD
class CAlta implements C {

}

@Data
class FullName {
	private final String firstName, lastName;
}


