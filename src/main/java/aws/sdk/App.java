package aws.sdk;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;

import java.util.List;


public class App 
{
    CreateBucket myBucket;
    public static void main( String[] args )
    {
        
        // Put the Access Key and Secret Key here
        AWSCredentialsProvider credentials = new AWSStaticCredentialsProvider(new BasicAWSCredentials(
        "e0b6d0...",
        "+1apS4..."));

        // Your namespace
        String bucketnamespace = "frsxwtjslf35";
        // The region to connect to eg: "uk-london-1"
        String region = "";

        // Create an S3 client pointing at the region
        String endpoint = String.format("%s.compat.objectstorage.%s.oraclecloud.com",bucketnamespace,region);
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(endpoint, region);
        AmazonS3 client = AmazonS3Client.builder()
        .standard()
        .withCredentials(credentials)
        .withEndpointConfiguration(endpointConfiguration)
        .disableChunkedEncoding()
        .enablePathStyleAccess()
        .build();

        final String USAGE = "\n" +
                "CreateBucket - create an S3 bucket\n\n" +
                "Usage: CreateBucket <bucketname>\n\n" +
                "Where:\n" +
                "  bucketname - the name of the bucket to create.\n\n" +
                "The bucket name must be unique, or an error will result.\n";

        if (args.length < 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String bucket_name = args[0];

        System.out.format("\nCreating S3 bucket: %s\n", bucket_name);

        client.createBucket(bucket_name);

        Bucket named_bucket = null;
        List<Bucket> buckets = client.listBuckets();

        for (Bucket b : buckets) {
            if (b.getName().equals(bucket_name)) {
                named_bucket = b;
                System.out.println(named_bucket.getName());
                
            }
        }
        System.out.println("************************************************************************"); 
        System.out.println("****** And here it goes the list of all buckets in the root compartment:");
        System.out.println(region);
        System.out.format("\nRegion: %s\n", region);
        System.out.format("\nNamespace: %s\n", bucketnamespace);
       
        
        int i = 0;
        System.out.format("\nYou have [%s] buckets\n",  buckets.size()  );
        System.out.println("************************************************************************\n");
        for (Bucket b : buckets) {
                i++;
                //System.out.println(b.getName());
                System.out.format("\nBucket n.%s %s", i ,b.getName());
                
        }
         
        
    }
               
}


 class CreateBucket {
    public  Bucket getBucket(String bucket_name) {
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
        Bucket named_bucket = null;
        List<Bucket> buckets = s3.listBuckets();
        for (Bucket b : buckets) {
            if (b.getName().equals(bucket_name)) {
                named_bucket = b;
            }
        }
        return named_bucket;
    }

    public  Bucket createBucket(String bucket_name) {
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
        Bucket b = null;
        if (s3.doesBucketExistV2(bucket_name)) {
            System.out.format("Bucket %s already exists.\n", bucket_name);
            b = getBucket(bucket_name);
        } else {
            try {
                b = s3.createBucket(bucket_name);
            } catch (AmazonS3Exception e) {
                System.err.println(e.getErrorMessage());
            }
        }
        return b;
    }
}

