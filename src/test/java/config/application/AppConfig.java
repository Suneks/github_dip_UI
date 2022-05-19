package config.application;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/application/remote.properties"
})
public interface AppConfig extends Config {

    String webUrl();
    String apiUrl();
    String userLogin();
    String userPassword();

}
