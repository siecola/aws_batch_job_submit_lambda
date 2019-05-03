package br.com.siecola.aws_batch_job_submit_lambda.service;

import br.com.siecola.aws_batch_job_submit_lambda.model.JobParameter;
import com.amazonaws.services.batch.AWSBatch;
import com.amazonaws.services.batch.AWSBatchClientBuilder;
import com.amazonaws.services.batch.model.*;

public class JobScheduler {

    public String submitJob(String key, JobParameter jobParameter) {

        AWSBatch client = AWSBatchClientBuilder.standard().build();
        SubmitJobRequest request = new SubmitJobRequest()
                .withJobName("job-" + key)
                .withJobQueue(jobParameter.getQueue())
                .withJobDefinition(jobParameter.getDefinition())
                .withRetryStrategy(new RetryStrategy()
                        .withAttempts(jobParameter.getAttempts()))
                .withContainerOverrides(new ContainerOverrides()
                        .withVcpus(jobParameter.getvCpu())
                        .withMemory(jobParameter.getMemory())
                        .withEnvironment(new KeyValuePair()
                                .withName("transactionKey")
                                .withValue(key)));

        SubmitJobResult response = client.submitJob(request);

        return response.getJobId();
    }
}