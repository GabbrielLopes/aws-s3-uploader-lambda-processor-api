package dev.gabbriellps.aws.s3.uploader.api.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsS3Configuration {

    @Bean
    public AmazonS3ClientBuilder amazonS3Builder() {

        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_2);
    }

}
