package net.ailitech.rest.dal.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;



@ConfigurationProperties(prefix="orm.master")
@Data
public class DefaultMasterDruidConfig extends BaseDruidConfig {
}
