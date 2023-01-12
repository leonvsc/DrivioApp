package nl.avans.drivioapp

import aws.sdk.kotlin.services.s3.*
import aws.sdk.kotlin.services.s3.model.PutObjectRequest
import aws.smithy.kotlin.runtime.content.asByteStream
import java.io.File

class ImagesS3AWS {

    suspend fun putS3Object(bucketName: String, objectKey: String, objectPath: String) {

        val metadataVal = mutableMapOf<String, String>()
        metadataVal["myVal"] = "test"

        val request = PutObjectRequest {
            bucket = bucketName
            key = objectKey
            metadata = metadataVal
            body = File(objectPath).asByteStream()
        }

        S3Client { region = "eu-west-1" }.use { s3 ->
            val response = s3.putObject(request)
            println("Tag information is ${response.eTag}")
        }
    }
}