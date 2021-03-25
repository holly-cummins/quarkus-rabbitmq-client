package io.quarkiverse.rabbitmqclient.util;

import java.util.Optional;

import io.quarkiverse.rabbitmqclient.RabbitMQClientConfig;
import io.quarkiverse.rabbitmqclient.RabbitMQClientsConfig;
import io.quarkus.arc.config.ConfigProperties;
import io.quarkus.runtime.annotations.ConfigItem;

@ConfigProperties(prefix = "test")
public class TestConfig {

    @ConfigItem
    public int amqpsPort;

    @ConfigItem
    public int amqpPort;

    public void setupNonSll(RabbitMQClientsConfig config) {
        setupNonSllClient(config.defaultClient);
    }

    public void setupNonSll(String name, RabbitMQClientsConfig config) {
        setupNonSllClient(config.namedClients.get(name));
    }

    private void setupNonSllClient(RabbitMQClientConfig config) {
        config.port = amqpPort;
        config.virtualHost = "/";
        config.username = "guest";
        config.password = "guest";
        config.hostname = "localhost";
        config.tls.enabled = false;
    }

    public void setupBasicSsl(RabbitMQClientsConfig config) {
        setupBasicSslClient(config.defaultClient);
    }

    public void setupBasicSsl(String name, RabbitMQClientsConfig config) {
        setupBasicSslClient(config.namedClients.get(name));
    }

    private void setupBasicSslClient(RabbitMQClientConfig config) {
        config.port = amqpsPort;
        config.virtualHost = "/";
        config.username = "guest";
        config.password = "guest";
        config.hostname = "localhost";
        config.tls.enabled = true;
        config.tls.keyStoreFile = Optional.empty();
        config.tls.keyStorePassword = Optional.empty();
        config.tls.trustStoreFile = Optional.of("classpath:/rabbitmq/ca/cacerts.jks");
        config.tls.trustStoreType = "JKS";
        config.tls.trustStorePassword = Optional.of("letmein");
    }

    public void setupClientCertSsl(RabbitMQClientsConfig config) {
        setupClientCertSslClient(config.defaultClient);
    }

    public void setupClientCertSsl(String name, RabbitMQClientsConfig config) {
        setupClientCertSslClient(config.namedClients.get(name));
    }

    private void setupClientCertSslClient(RabbitMQClientConfig config) {
        config.port = amqpsPort;
        config.virtualHost = "/";
        config.username = "guest";
        config.password = "guest";
        config.hostname = "localhost";
        config.tls.enabled = true;
        config.tls.keyStoreFile = Optional.of("classpath:/rabbitmq/client/client.jks");
        config.tls.keyStoreType = "JKS";
        config.tls.keyStorePassword = Optional.of("letmein");
        config.tls.trustStoreFile = Optional.of("classpath:/rabbitmq/ca/cacerts.jks");
        config.tls.trustStoreType = "JKS";
        config.tls.trustStorePassword = Optional.of("letmein");
    }
}
