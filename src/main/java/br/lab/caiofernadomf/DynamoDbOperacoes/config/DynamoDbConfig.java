package br.lab.caiofernadomf.DynamoDbOperacoes.config;

import com.amazonaws.auth.*;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.s3.model.Region;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDbConfig {

//    @Bean
//    AmazonDynamoDB amazonDynamoDB() {
//
//        return AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_2)
//                .build();
//    }

    @Bean
    AmazonDynamoDB buildAmazonDynamoDb(){
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder
                                .EndpointConfiguration("http://localhost:4566","us-east-2")
                ).build();
    }
}
