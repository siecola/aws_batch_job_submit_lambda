package br.com.siecola.aws_batch_job_submit_lambda.service;

import br.com.siecola.aws_batch_job_submit_lambda.model.JobParameter;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

public class JobReader {

    public JobParameter readJob(String jobId) {
        final AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(
                new EnvironmentVariableCredentialsProvider());
        ddbClient.withRegion(Regions.US_EAST_1);
        DynamoDB dynamoDB = new DynamoDB(ddbClient);
        Table table = dynamoDB.getTable("job-transaction");
        Item job = table.getItem("id", jobId);

        return new JobParameter(job.getString("queue"),
                job.getString("definition"),
                job.getInt("vCpu"),
                job.getInt("memory"));
    }
}