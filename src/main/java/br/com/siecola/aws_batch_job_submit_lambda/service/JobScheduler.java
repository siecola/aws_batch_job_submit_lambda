package br.com.siecola.aws_batch_job_submit_lambda.service;

import br.com.siecola.aws_batch_job_submit_lambda.model.JobParameter;
import com.amazonaws.services.batch.AWSBatch;
import com.amazonaws.services.batch.AWSBatchClientBuilder;
import com.amazonaws.services.batch.model.ContainerOverrides;
import com.amazonaws.services.batch.model.SubmitJobRequest;
import com.amazonaws.services.batch.model.SubmitJobResult;

public class JobScheduler {

    public String submitJob(String jobName, JobParameter jobParameter) {

        AWSBatch client = AWSBatchClientBuilder.standard().build();
        SubmitJobRequest request = new SubmitJobRequest()
                .withJobName(jobName)
                .withJobQueue(jobParameter.getQueue())
                .withJobDefinition(jobParameter.getDefinition())
                .withContainerOverrides(new ContainerOverrides()
                        .withVcpus(jobParameter.getvCpu())
                        .withMemory(jobParameter.getMemory()));
        SubmitJobResult response = client.submitJob(request);

        return response.getJobId();
    }
}