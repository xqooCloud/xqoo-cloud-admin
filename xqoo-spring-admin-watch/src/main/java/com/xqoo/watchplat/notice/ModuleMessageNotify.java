package com.xqoo.watchplat.notice;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractEventNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class ModuleMessageNotify extends AbstractEventNotifier {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleMessageNotify.class);

    private static final String DEFAULT_MESSAGE = "*#{instance.registration.name}* (#{instance.id}) is *#{event.statusInfo.status}**";

    private final SpelExpressionParser parser = new SpelExpressionParser();
    private Expression message;

    public ModuleMessageNotify(InstanceRepository repository) {
        super(repository);
        this.message = parser.parseExpression(DEFAULT_MESSAGE, ParserContext.TEMPLATE_EXPRESSION);
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        return Mono.fromRunnable(() -> {
            // 改写第一个if就好了，第二个if打印的有点多余而且无用
            if (event instanceof InstanceStatusChangedEvent) {
                LOGGER.info("[服务监控] 模块名：{}，实例id: ({})，服务器ip: [{}] 状态： {}", instance.getRegistration().getName(), event.getInstance(),
                        instance.getRegistration().getServiceUrl(), ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus());
                Map rtnMap = this.createMessage(event, instance);
                LOGGER.info("[模块状态改变]{}", rtnMap.toString());
            }
            else {
                LOGGER.info("[服务监控] {} ({}) {}", instance.getRegistration().getName(), event.getInstance(),
                        event.getType());
            }
        });
    }

    private Map<String, Object> createMessage(InstanceEvent event,Instance instance) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("text", this.getMessage(event, instance));
        params.put("title", "title");
        System.out.print("[监控模块输出]" + params.toString());
        return params;
    }

    private String getMessage(InstanceEvent event,Instance instance) {
        Map<String, Object> root = new HashMap<>();
        root.put("event", event);
        root.put("instance", instance);
        StandardEvaluationContext context = new StandardEvaluationContext(root);
        context.addPropertyAccessor(new MapAccessor());
        return message.getValue(context, String.class);
    }
}
