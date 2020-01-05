package com.bgjo.batchexercise.batch.item;

import ch.qos.logback.core.db.DataSourceConnectionSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Configuration
public class FileToDBItemWriter<T> extends JdbcBatchItemWriterBuilder<T> {

    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://127.0.0.1:5432/test");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("rootpw");
        return dataSourceBuilder.build();
    }

    public FileToDBItemWriter() {

        dataSource(getDataSource())
                .sql("insert into test(id, name, age, member_type) values (:id, :name, :age, :member_type)")
                .beanMapped()
                .build();


    }
    /*@Override
    public void write(List<? extends T> items) throws Exception {
        System.out.println("FileToDBItemWriter");
        JdbcBatchItemWriterBuilder<T> jdbcBatchItemWriterBuilder= new JdbcBatchItemWriterBuilder<T>();
        jdbcBatchItemWriterBuilder
                .dataSource(dataSource)
                .sql("insert into test(id, name, age, member_type) values (:id, :name, :age, :member_type)")
                .beanMapped()
                .build();
        for(T o : items) {
            log.info(o.toString());
        }
    }*/
}
