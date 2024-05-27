package br.lab.caiofernadomf.DynamoDbOperacoes.repository.operations;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.spec.ListTablesSpec;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;

public class CheckTable {
    public static boolean checkTableExists(String tableName, AmazonDynamoDB amazonDynamoDB){

        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        try{
            dynamoDB.getTable(tableName).describe();
            return true;
        }catch (ResourceNotFoundException re){
            return false;
        }

    }
}
