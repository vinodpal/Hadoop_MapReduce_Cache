package com.cache;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import org.apache.hadoop.mapreduce.Job;

public class CacheDriver 
{
	public static void main(String[] args) throws Exception
	{
  
	Configuration conf = new Configuration();
	    Job j1 = new Job(conf, "cachejob");
j1.setJarByClass(CacheDriver.class);
j1.setMapperClass(CacheMapper.class);
j1.setCombinerClass(CacheReducer.class);
  j1.setReducerClass(CacheReducer.class);
 j1.setOutputKeyClass(Text.class);
   j1.setOutputValueClass(IntWritable.class);
DistributedCache.addCacheFile(new URI("hdfs://localhost:8020/input.txt"),j1.getConfiguration());
FileInputFormat.addInputPath(j1, new Path(args[0]));
FileOutputFormat.setOutputPath(j1, new Path(args[1]));
System.exit(j1.waitForCompletion(true) ? 0 : 1);
		}
}
