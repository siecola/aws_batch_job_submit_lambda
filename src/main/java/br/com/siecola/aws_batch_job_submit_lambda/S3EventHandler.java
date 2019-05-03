package br.com.siecola.aws_batch_job_submit_lambda;

import br.com.siecola.aws_batch_job_submit_lambda.model.JobParameter;
import br.com.siecola.aws_batch_job_submit_lambda.service.JobReader;
import br.com.siecola.aws_batch_job_submit_lambda.service.JobScheduler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification;

public class S3EventHandler implements RequestHandler<S3Event, String> {

    @Override
    public String handleRequest(S3Event s3Event, Context context) {
        LambdaLogger logger = context.getLogger();

        S3EventNotification.S3EventNotificationRecord record =
                s3Event.getRecords().get(0);
        String key = record.getS3().getObject().getKey();
        logger.log("Object key: " + key);

        JobReader jobReader = new JobReader();
        JobParameter jobParameter = jobReader.readJob(key);

        JobScheduler jobScheduler = new JobScheduler();
        String jobId = jobScheduler.submitJob(key, jobParameter);

        logger.log("JobId: "+ jobId);
        return null;
    }
}