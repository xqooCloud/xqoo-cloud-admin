package com.xqoo.watchplat.config;
//
import com.xqoo.watchplat.notice.ModuleMessageNotify;
import de.codecentric.boot.admin.server.config.AdminServerNotifierAutoConfiguration;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureBefore({AdminServerNotifierAutoConfiguration.NotifierTriggerConfiguration.class, AdminServerNotifierAutoConfiguration.CompositeNotifierConfiguration.class})
public class CustomNotifyConfig {
    @Bean
    @ConditionalOnMissingBean
    public ModuleMessageNotify dingTalkNotifier(InstanceRepository repository) {
        return new ModuleMessageNotify(repository);
    }
}
