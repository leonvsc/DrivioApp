package nl.avans.drivioapp.AWS

import aws.sdk.kotlin.services.s3.model.DeleteObjectRequest
import aws.sdk.kotlin.services.s3.model.PutObjectRequest
import aws.smithy.kotlin.runtime.content.asByteStream
import java.io.File

class ImagesS3AWS {

    // Function to upload images to S3
    suspend fun putS3Object(bucketName: String, objectKey: String, objectPath: String) {

        println("PutS3Object accessed")
        val metadataVal = mutableMapOf<String, String>()
        metadataVal["myVal"] = "test"

        val request = PutObjectRequest {
            bucket = bucketName
            key = objectKey
            metadata = metadataVal
            body = File(objectPath).asByteStream()
        }

        s3Client.putObject(request)
        println("Tag information is $request")

    }

    suspend fun deleteS3Object(bucketName: String, objectName: String) {

        val request = DeleteObjectRequest {
            bucket = bucketName
            key = objectName
        }
        s3Client.deleteObject(request)
        println("Tag information is $request")
    }
}
