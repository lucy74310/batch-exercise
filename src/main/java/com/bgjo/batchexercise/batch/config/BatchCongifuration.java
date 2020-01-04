package com.bgjo.batchexercise.batch.config;

import com.bgjo.batchexercise.batch.item.FileToDBItemReader;
import com.bgjo.batchexercise.batch.item.FileToDBItemWriter;
import com.bgjo.batchexercise.model.TestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.repeat.support.TaskExecutorRepeatTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.batch.api.chunk.ItemReader;
import java.io.File;
import java.io.FileWriter;

@Configuration
@Slf4j
public class BatchCongifuration {

    @Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private StepBuilderFactory stepBuilders;


    @Bean
    public Job fileToDBJob(@Qualifier("fileMakeStep") Step fileMakeStep,
                           @Qualifier("fileToDBStep") Step fileToDBStep) {
        return jobBuilders.get("fileToDB")
//                .start(fileMakeStep)
//                .next(fileToDBStep)
                .start(fileToDBStep)
                .build();
    }
    @Bean("fileToDBStep")
    public Step fileToDBStep(
        @Qualifier("fileToDBItemReader") FlatFileItemReader<TestVo> fileToDBItemReader,
        @Qualifier("fileToDBItemWriter") ItemWriter<TestVo> fileToDBItemWriter,
        @Qualifier("fileToDBItemProcessor") ItemProcessor<TestVo, TestVo> fileToDBItemProcessor
    ) {
        return stepBuilders
                .get("fileMakeStep")
                .<TestVo, TestVo>chunk(2)
                .reader( fileToDBItemReader )
                .processor( fileToDBItemProcessor )
                .writer( fileToDBItemWriter )
                .build();
    }

    @Bean("fileToDBItemReader")
    public FlatFileItemReader<TestVo> fileToDBItemReader() {
        return new FileToDBItemReader<TestVo>();
    }

    @Bean("fileToDBItemWriter")
    public ItemWriter<TestVo> fileToDBItemWriter(){
//        return list -> {
//            for(TestVo t : list) {
//                log.info(t.toString());
//            }
//        };
        return new FileToDBItemWriter<TestVo>();
    }

    @Bean("fileToDBItemProcessor")
    public ItemProcessor<TestVo, TestVo> fileToDBItemProcessor() {
        return testVo -> {
            testVo.setMember_type("D");
            return testVo;
        };
    }


    /** fileMakeStep **/


    @Bean("fileMakeStep")
    public Step fileToDBStep(@Qualifier("fileMakeTasklet") Tasklet fileMakeTasklet) {
        return stepBuilders
                .get("fileToDBStep")
                .tasklet(fileMakeTasklet).build();
    }

    @Bean("fileMakeTasklet")
    public Tasklet fileMakeTasklet() {
        return (contribution, chunkContext) -> {
            log.info(">>>>this is fileToDBTasklet");
            File file = new File("E://file/test.csv");

            FileWriter fw = new FileWriter(file);

            fw.write("1,John,13,R\n2,lucy,15,R\n3,cline,22,R");
            fw.close();
            return RepeatStatus.FINISHED;
        };
    }





}
