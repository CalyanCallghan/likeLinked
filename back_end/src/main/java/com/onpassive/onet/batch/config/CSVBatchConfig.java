package com.onpassive.onet.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.onpassive.onet.entity.User;

@Configuration
@EnableBatchProcessing
public class CSVBatchConfig {

	@Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<User> itemReader,
                   ItemProcessor<User, User> itemProcessor,
                   ItemWriter<User> itemWriter,TaskletStep taskletStep) {

        Step step = stepBuilderFactory.get("ATTENDANCE-file-load")
                .<User, User>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
        
        Step step2 = stepBuilderFactory.get("step2")
				.tasklet(taskletStep)
				.build();

        return jobBuilderFactory.get("ATTENDANCE-Load")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .next(step2)
                .build();
    }

    @Bean
    public FlatFileItemReader<User> itemReader() {
        FlatFileItemReader<User> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new FileSystemResource("E:/onet/Attendance.csv"));
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<User> lineMapper() {

        DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[] { "email", "firstName", "lastName", "groupId", "empId","phoneNo" });
        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(User.class);
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }

	
}
