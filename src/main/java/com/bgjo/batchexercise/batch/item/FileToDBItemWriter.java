package com.bgjo.batchexercise.batch.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
public class FileToDBItemWriter<T> implements ItemWriter<T> {

    @Override
    public void write(List<? extends T> items) throws Exception {
        System.out.println("FileToDBItemWriter");
        for(T o : items) {
            log.info(o.toString());
        }
    }
}
