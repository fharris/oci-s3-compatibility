# Simple client to explore AWS S3 OCI Object Storage compatibility

## We are going to explore OCI Object Storage S3 compatibility API. The first thing we need to do is to create a secret key. Check the documentation to learn more about the process:
https://docs.oracle.com/en-us/iaas/Content/Object/Tasks/s3compatibleapi.htm

https://docs.oracle.com/en-us/iaas/Content/Rover/IAM/User_Credentials/Secret_Keys/create_customer-secret-key.htm?source=%3Aow%3Ao%3Abl%3Apo%3A%3A%3A#CreateCustomerSecretKey



## To run:

mvn exec:java -Dexec.mainClass="aws.sdk.App" -Dexec.args="name_of_the_bucket_to_create" -Dexec.cleanupDaemonThreads=false
