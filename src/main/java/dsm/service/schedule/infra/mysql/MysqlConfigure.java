package dsm.service.schedule.infra.mysql;


import dsm.service.schedule.infra.consul.ConsulHandler;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class MysqlConfigure {
    private final ConsulHandler consulHandler;

    @Bean
    public DataSource dataSource() {
        JSONObject consulV = consulHandler.getValue("db/schedule/local");

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url((String) consulV.get("url"));
        dataSourceBuilder.username((String) consulV.get("username"));
        dataSourceBuilder.password(System.getenv("DB_PASSWORD"));
        return dataSourceBuilder.build();
    }

}
