package dev.gabbriellps.aws.s3.uploader.api.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AwsS3Service {

    void uploadFile(String bucketName, MultipartFile file, String acessKey, String secretKey)
            throws IOException;

}
