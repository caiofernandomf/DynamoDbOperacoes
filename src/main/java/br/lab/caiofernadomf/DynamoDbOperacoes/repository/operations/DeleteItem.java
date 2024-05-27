package br.lab.caiofernadomf.DynamoDbOperacoes.repository.operations;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalOperator;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.xspec.Condition;
import com.amazonaws.services.dynamodbv2.xspec.DeleteItemExpressionSpec;
import com.amazonaws.services.dynamodbv2.xspec.ExpressionSpecBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteItem {
    public static Map deleteItem(
            AmazonDynamoDB amazonDynamoDB,
            String tableName,
            String keyName,
            String keyValue){

        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        Table table = dynamoDB.getTable(tableName);

        List<KeySchemaElement> keySchemaElementList=
                table.describe().getKeySchema();

        PrimaryKey primaryKey = new PrimaryKey();

        Map mapaItem = GetItem.getItem(amazonDynamoDB,tableName,keyName,keyValue);

        if(!mapaItem.isEmpty()){

            keySchemaElementList.forEach(k-> primaryKey.addComponent(k.getAttributeName(),mapaItem.get(k.getAttributeName())));

            DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey(primaryKey);

            table.deleteItem(deleteItemSpec);

            return mapaItem;
        }

        return new HashMap();

        //return table.deleteItem(keyName,keyValue).getItem();
    }
}
