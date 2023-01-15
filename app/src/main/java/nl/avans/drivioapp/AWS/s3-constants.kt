package nl.avans.drivioapp.AWS

import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.services.s3.S3Client
import nl.avans.drivioapp.BuildConfig

object `s3-constants` {
    val BUCKET_NAME: String = "images-drivio-app"
    val AWS_REGION: String = "eu-west-1"
}

val staticCredentials = StaticCredentialsProvider {
    accessKeyId = BuildConfig.AWS_ACCESS_KEY_ID
    secretAccessKey = BuildConfig.AWS_SECRET_ACCESS_KEY
}

val s3Client = S3Client {
    region = `s3-constants`.AWS_REGION
    credentialsProvider = staticCredentials}