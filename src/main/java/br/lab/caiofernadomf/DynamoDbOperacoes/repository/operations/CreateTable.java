package br.lab.caiofernadomf.DynamoDbOperacoes.repository.operations;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.ArrayList;
import java.util.List;

public class CreateTable {

    public static void createTable(String tableName, AmazonDynamoDB amazonDynamoDB){
        List<AttributeDefinition> attributeDefinitions= new ArrayList<AttributeDefinition>();

        attributeDefinitions.add(new AttributeDefinition().withAttributeName("name").withAttributeType("S"));
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("ip").withAttributeType("S"));

        List<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
        keySchema.add(new KeySchemaElement().withAttributeName("name").withKeyType(KeyType.HASH));
        keySchema.add(new KeySchemaElement().withAttributeName("ip").withKeyType(KeyType.RANGE));

        CreateTableRequest request = new CreateTableRequest()
                .withTableName(tableName)
                .withKeySchema(keySchema)
                .withAttributeDefinitions(attributeDefinitions)
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(5L)
                        .withWriteCapacityUnits(5L));

        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        Table table = dynamoDB.createTable(request);

        try {
            table.waitForActive();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
