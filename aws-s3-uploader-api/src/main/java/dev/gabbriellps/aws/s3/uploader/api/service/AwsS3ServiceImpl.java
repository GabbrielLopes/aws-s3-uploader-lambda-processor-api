package dev.gabbriellps.aws.s3.uploader.api.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import dev.gabbriellps.aws.s3.uploader.api.service.interfaces.AwsS3Service;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AwsS3ServiceImpl implements AwsS3Service {

    private final AmazonS3ClientBuilder amazonS3Builder;

    @Override
    public void uploadFile(String bucketName, MultipartFile file, String acessKey, String secretKey)
            throws IOException {

        validaCredenciais(acessKey, secretKey);

        AmazonS3 amazonS3 = amazonS3Builder
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(acessKey, secretKey)))
                .build();

        if (!amazonS3.doesBucketExistV2(bucketName)) {
            log.error("Erro, bucket informado não existe.");
            return;
        }

        amazonS3.putObject(bucketName, "/teste/" + file.getOriginalFilename(), file.getInputStream(), new ObjectMetadata());
        log.info("Arquivo inserido com sucesso!");
    }

    private void validaCredenciais(String acessKey, String secretKey) {
        if(StringUtils.isBlank(acessKey) || StringUtils.isBlank(secretKey)){
            log.info("Erro, usuário inválido!");
            throw new IllegalArgumentException("Erro, usuário inválido!");
        }
    }
}
