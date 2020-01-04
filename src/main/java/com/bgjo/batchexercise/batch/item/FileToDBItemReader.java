package com.bgjo.batchexercise.batch.item;

import com.bgjo.batchexercise.model.TestVo;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

public class FileToDBItemReader<T> extends FlatFileItemReader<T> {
    public FileToDBItemReader() {
        setEncoding("UTF-8");
        setResource(new FileSystemResource("E://file/test.csv"));
        setLinesToSkip(1);

        DefaultLineMapper<T> lineMapper = new DefaultLineMapper<T>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(",");
        tokenizer.setNames(new String[] { "id", "name", "age", "member_type" });
        lineMapper.setLineTokenizer(tokenizer);

        BeanWrapperFieldSetMapper<T> fieldSetMapper = new BeanWrapperFieldSetMapper<T>();
        fieldSetMapper.setTargetType((Class<? extends T>) TestVo.class);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        setLineMapper(lineMapper);

    }
}
