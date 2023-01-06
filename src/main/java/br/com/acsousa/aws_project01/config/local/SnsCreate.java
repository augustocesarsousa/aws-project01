package br.com.acsousa.aws_project01.config.local;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.Topic;

@Configuration
@Profile("local")
public class SnsCreate {
    private static final Logger LOGGER = LoggerFactory.getLogger(SnsCreate.class);

    private final String productEventsTopic;
    private final AmazonSNS snsCLiente;

    

    public SnsCreate() {
        this.snsCLiente = AmazonSNSClient.builder()
            .withEndpointConfiguration(new AwsClientBuilder
                .EndpointConfiguration("http://localhost:4566", 
                Regions.US_EAST_1.getName()))
            .withCredentials(new DefaultAWSCredentialsProviderChain())
            .build();
        CreateTopicRequest createTopicRequest = new CreateTopicRequest("product-events");
        this.productEventsTopic = this.snsCLiente.createTopic(createTopicRequest).getTopicArn();

        LOGGER.info("SNS topic ARN: {}", this.productEventsTopic);
    }

    @Bean
    public AmazonSNS snsClient() {
        return this.snsCLiente;
    }

    @Bean(name = "productEventsTopic")
    public Topic snsProductEventsTopic() {
        return new Topic().withTopicArn(productEventsTopic);
    }
}
