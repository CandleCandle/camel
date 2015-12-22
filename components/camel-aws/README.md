

Camel AWS
=========

Kinesis
-------

The Kinesis API allows both reading and writing to Kinesis streams, however, this endpoint is currently a consumer-only endpoint.

URI Format:

aws-kinesis://stream-name[?options]

URI Parameters:
amazonKinesisClient    An instance of com.amazonaws.services.kinesis.AmazonKinesisClient configured and ready to be used, in the Camel registry.
maxResultsPerRequest   Maximum results that will be returned in each poll to the AWS API, Given that the shard iterator is unique to the consumer, changing it shouldn't effect other consumers.
iteratorType           One of trim\_horizon or latest. See http://docs.aws.amazon.com/kinesis/latest/APIReference/API\_GetShardIterator.html for descriptions of these two iterator types.
scheduler.\*           Standard Camel scheduler options.

URI Parameter Defaults:
amazonKinesisClient    null
maxResultsPerRequest   100
iteratorType           TRIM\_HORIZON

AWS Documentation
http://aws.amazon.com/documentation/kinesis/

Shards:

Shards are a way of making your data processing parallel. You can produce to or consume from a particular shard. This compoent currently assumes that there is only one shard.

Nothing is created for you by this endpoint. It assumes that you are managing your infrastructure in another way.


DynamoDB Streams Endpoint
-------------------------

The DynamoDB Streams API allows reading from the stream only. To write to the stream, make a change in the underlying DynamoDB table, potentially using a DynamoDB Camel Endpoint.

URI Format:

aws-ddbstream:table-name[?options]

URI Parameters:
amazonDynamoDbStreamsClient   An instance of com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreams configured and ready to be used, in the Camel registry.
maxResultsPerRequest          Maximum results that will be returned in each poll to the AWS API, Given that the shard iterator is unique to the consumer, changing it shouldn't effect other consumers.
iteratorType                  One of trim\_horizon, latest, after\_sequence\_number or at\_sequence\_number. See http://docs.aws.amazon.com/dynamodbstreams/latest/APIReference/API\_GetShardIterator.html for descriptions of these four iterator types.
sequenceNumberProvider        Either a bean reference to an implementation of org.apache.camel.component.aws.ddbstream.SequenceNumberProvider or a literal string representing a sequence number. The role of this is to determine where in the stream to start when using one of the after\_sequence\_number or at\_sequence\_number iterator types.
scheduler.\*                  Standard Camel scheduler options.

URI Parameter Defaults:
amazonDynamoDbStreamsClient   null
maxResultsPerRequest          100
iteratorType                  LATEST
sequenceNumberProvider        null

sequenceNumberProvider:

An example of using this might be on receiving an exchange, write the Record's sequence number to disk. On startup, read the disk so that processing can continue where it left-off.

It is an error to provide a sequence number that is greater than the largest sequence number in the describe-streams result, as this will lead to the AWS call returning an HTTP 400.


