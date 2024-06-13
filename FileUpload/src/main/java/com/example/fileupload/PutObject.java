// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: Apache-2.0

package com.example.fileupload;

// snippet-start:[s3.java2.s3_object_upload.main]
// snippet-start:[s3.java2.s3_object_upload.import]

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.http.SdkHttpResponse;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// snippet-end:[s3.java2.s3_object_upload.import]

/**
 * Before running this Java V2 code example, set up your development
 * environment, including your credentials.
 * <p>
 * For more information, see the following documentation topic:
 * <p>
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */

/**
 * https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/javav2/example_code/s3/src/main/java/com/example/s3
 */


public class PutObject {
    public static void main(String[] args) {

        var bucketName = "memos";
        var accountId = "<accountid>";
        var accessKey = "31ce8e360a9a3a52b3ab4da108518189";
        var accessKeySecret = "54bde73d62d0996cd0b821f088b869df719e63678b3c90ec5e6f273ff3f2f889";

        Region region = Region.US_EAST_1;
        S3Client s3 = S3Client.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider
                        .create(AwsBasicCredentials.create(accessKey, accessKeySecret)))
                .endpointOverride(URI.create("https://37ca4d75d1436e0e2a3e01a3067705a2.r2.cloudflarestorage.com/"))
                .build();

        String objectPath = "C:\\Users\\ehZyiL\\Desktop\\QQ图片20240423140357.jpg";

//        putS3Object(s3, bucketName, accessKey, objectPath);
        listBucketObjects(s3, bucketName);
//        listBucketObjectsPaginated(s3, bucketName);

        s3.close();
    }

    // This example uses RequestBody.fromFile to avoid loading the whole file into
    // memory.

    public static void putS3Object(S3Client s3, String bucketName, String fileName, String objectPath) {
        try {
            Map<String, String> metadata = new HashMap<>();
            metadata.put("x-amz-meta-myVal", "test");
            File file = new File(objectPath);
            FileInputStream input = new FileInputStream(file);


            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key("0d0bfb2d9bd9af3f65d7f96b6d218bee")
                    .build();

            PutObjectResponse response =   s3.putObject(putOb, RequestBody.fromInputStream(input, 0));

            SdkHttpResponse sdkHttpResponse = response.sdkHttpResponse();
            System.out.println(response);

//            //ListBuckets
//            ListBucketsResponse response = s3.listBuckets();
//            List<Bucket> bucketList = response.buckets();
//            for (Bucket bucket: bucketList) {
//                System.out.println("Bucket name "+bucket.name());
//            }

            // putObject
//            s3.putObject(putOb, RequestBody.fromFile(new File(objectPath)));
//            System.out.println("Successfully placed " + fileName + " into bucket " + bucketName);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }


    // 查询桶下所有对象
    public static void listBucketObjects(S3Client s3, String bucketName) {
        try {
            ListObjectsRequest listObjects = ListObjectsRequest
                    .builder()
                    .bucket(bucketName)
                    .build();

            ListObjectsResponse res = s3.listObjects(listObjects);
            List<S3Object> objects = res.contents();
            for (S3Object myValue : objects) {
                System.out.print("\n The name of the key is " + myValue.key());
                System.out.print("\n The object is " + calKb(myValue.size()) + " KBs");
                System.out.print("\n The owner is " + myValue.owner());
            }

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

    // convert bytes to kbs.
    private static long calKb(Long val) {
        return val / 1024 / 1024;
    }

    public static void listBucketObjectsPaginated(S3Client s3, String bucketName) {
        try {
            ListObjectsV2Request listReq = ListObjectsV2Request.builder()
                    .bucket(bucketName)
                    .maxKeys(1)
                    .build();

            ListObjectsV2Iterable listRes = s3.listObjectsV2Paginator(listReq);
            listRes.stream()
                    .flatMap(r -> r.contents().stream())
                    .forEach(content -> System.out.println(" Key: " + content.key() + " size = " + calKb(content.size())));

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

}
// snippet-end:[s3.java2.s3_object_upload.main]