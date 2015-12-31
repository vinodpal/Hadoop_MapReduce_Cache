package com.cache;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class CacheMapper extends Mapper<Object, Text, Text, IntWritable>
{
  private final static IntWritable one = new IntWritable(1);
  private Text word = new Text();

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException
    {
  Path[] uris = DistributedCache.getLocalCacheFiles(context.getConfiguration());
                    try
                    {
 BufferedReader br= new BufferedReader(new FileReader(uris[0].toString()));
      String line;
while ((line=br.readLine())!=null)
                        {
        System.out.println(line);
                      }
                  br.close(); 
                    }       
    catch (Exception e)
                    {
 System.out.println(e.toString());
                    }

      StringTokenizer itr = new StringTokenizer(value.toString());

      while (itr.hasMoreTokens()) 
      {
        word.set(itr.nextToken());
        context.write(word, one);
      }
    }
}
