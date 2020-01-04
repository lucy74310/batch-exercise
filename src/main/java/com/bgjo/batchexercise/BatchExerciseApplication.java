package com.bgjo.batchexercise;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableBatchProcessing
@SpringBootApplication
public class BatchExerciseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchExerciseApplication.class, args);
    }

}
