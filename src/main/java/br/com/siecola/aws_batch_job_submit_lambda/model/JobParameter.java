package br.com.siecola.aws_batch_job_submit_lambda.model;

public class JobParameter {

    private String queue;
    private String definition;
    private int vCpu;
    private int memory;

    public JobParameter(String queue, String definition, int vCpu, int memory) {
        this.queue = queue;
        this.definition = definition;
        this.vCpu = vCpu;
        this.memory = memory;
    }

    public String getQueue() {
        return queue;
    }

    public String getDefinition() {
        return definition;
    }

    public int getvCpu() {
        return vCpu;
    }

    public int getMemory() {
        return memory;
    }
}