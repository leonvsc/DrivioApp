package nl.avans.drivioapp.AWS

import aws.sdk.kotlin.runtime.InternalSdkApi
import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.runtime.config.AwsSdkSetting
import aws.sdk.kotlin.services.s3.S3Client
import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop
import nl.avans.drivioapp.BuildConfig
import java.util.Properties

object `s3-constants` {
    val BUCKET_NAME: String = "images-drivio-app"
    val AWS_REGION: String = "eu-west-1"
}

val staticCredentials = StaticCredentialsProvider {

}

val s3Client = S3Client {
    region = `s3-constants`.AWS_REGION
    credentialsProvider = staticCredentials}