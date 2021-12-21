package guru.springframework.sdjpaintro.config;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//bei den run options kann dieses profil mit angegeben werden...
@Profile("clean")
@Configuration
public class DbClean {

    //"Strategie" für flyway wird gesetzt
    //erst cleanen, dann migraten!
    @Bean
    public FlywayMigrationStrategy clean() {
        return flyway -> {
            flyway.clean();
            flyway.migrate();
        };
    }
}
