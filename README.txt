DistributedCache is a facility provided by the Map-Reduce framework to cache files (text, archives, jars etc.) needed by applications.

The framework will copy the necessary files on to the slave node before any tasks for the job are executed on that node. Its efficiency stems from the fact that the files are only copied once per job and the ability to cache archives which are un-archived on the slaves.

 

In thid copy the input.txt file into hdfs root directory directly
i.e
/
input.txt
bin/hadoop fs -copyFromLocal input.txt /myapp/input.txt

