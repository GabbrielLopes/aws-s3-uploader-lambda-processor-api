package dev.gabbriellps.aws.s3.uploader.api.controller;

import dev.gabbriellps.aws.s3.uploader.api.service.interfaces.AwsS3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/v1")
public class FileController {

    private final AwsS3Service awsS3Service;

    public FileController(AwsS3Service awsS3Service) {
        this.awsS3Service = awsS3Service;
    }

    @PostMapping("/aws/s3/upload")
    public ResponseEntity<?> uploadFileS3(
            @RequestHeader("acess-key") String acessKey,
            @RequestHeader("secret-key") String secretKey,
            @RequestParam("bucket") String bucketName,
            @RequestPart("file") MultipartFile file) throws IOException {

        awsS3Service.uploadFile(bucketName, file, acessKey, secretKey);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
