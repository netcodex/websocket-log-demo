package com.lizard.websocket;

import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

/**
 * 自定义实现appender，参照：@see <a href="https://logging.apache.org/log4j/2.x/manual/extending.html">log4j2扩展</a>
 *
 * @author X
 * @version 1.0
 * @since 2023-05-03 16:33
 **/
@Plugin(name = "WebsocketAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE,
    printObject = true)
public class WebsocketLogAppender extends AbstractAppender {

    private LoggerQueue loggerQueue = LoggerQueue.getInstance();

    protected WebsocketLogAppender(String name, Filter filter, Layout<? extends Serializable> layout,
        boolean ignoreExceptions, Property[] properties) {
        super(name, filter, layout, ignoreExceptions, properties);
    }

    @Override
    public void append(LogEvent event) {
        LoggerMessage loggerMessage = new LoggerMessage(event.getMessage().getFormattedMessage(),
            DateFormat.getDateTimeInstance().format(new Date(event.getTimeMillis())), event.getThreadName(),
            event.getLoggerName(), event.getLevel().name());

        loggerQueue.push(loggerMessage);
    }

    @PluginFactory
    public static WebsocketLogAppender createAppender(@PluginAttribute("name") String name,
        @PluginAttribute("ignoreExceptions") boolean ignoreExceptions, @PluginElement("Layout") Layout layout,
        @PluginElement("Filters") Filter filter) {
        if (name == null) {
            LOGGER.error("No name provided for WebSocketAppender");
            return null;
        }

        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        return new WebsocketLogAppender(name, filter, layout, ignoreExceptions, Property.EMPTY_ARRAY);
    }
}
