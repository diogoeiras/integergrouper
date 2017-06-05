# integergrouper
Integer grouping by proximity. Groups integers using two approachs: 
- Clustering with the cluster algorithm KMeans|| from [Spark's MLlib library](https://spark.apache.org/docs/latest/mllib-clustering.html#k-means)
- Diving the values in groups reducing the sum of the variances between them
