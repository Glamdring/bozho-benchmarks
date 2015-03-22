## Non-blocking vs blocking benchmark (Node.js, Servlets, Spray)

The benchmarked scenario is: reading a 43kb file and writing it to the output stream.

Running load tests with **Gatling 2.1.4** on a **t2.small** instance  
Running **Embedded Tomcat 7 (with Java 7)**, ``maxThreads=4000``, **Node.js v0.12.0** and **Spray 1.3.1** on a **m3.large** instance  
(For the servlet tets ``readInMemory`` was set to true, so that the file is first fully read in memory and then sent to the browser (if it's not set, it's faster))

Each scenario was run at least twice. The results are of the first run, and the second run was to prove the results are consistent.

Load test scenario is in the "simulations" directory. Node.js, Servlet and Spray code is in the "code" directory.

## Results


### 120000 requests over 30 seconds

| Scenario    :| Mean response time:| Mean req/sec:| Std deviation:| Resp. time Q2:| Resp. time Q3:| t < 800 ms :| 800 ms < t < 1200 ms:| t > 1200 ms :| failed   :|
|--------------|--------------------|--------------|---------------|---------------|---------------|-------------|----------------------|--------------|-----------|
| Servlet BIO  | 4113               | 841.019      | 6878          | 2281          | 4008          | 18961 (16%) | 9363 (8%)            | 89545 (75%)  | 2131 (2%) |
| Servlet NIO  | 4673               | 995.198      | 3928          | 4062          | 6131          | 11188 (9%)  | 5195 (4%)            | 103060 (86%) | 557 (0%)  |
| Node.js      | 4131               | 1136.342     | 3469          | 3809          | 5613          | 18349 (15%) | 6073 (5%)            | 95568 (80%)  | 10 (0%)   |
| Node.js sync | 6908               | 825.764      | 5658          | 6189          | 9904          | 11806 (10%) | 6576 (5%)            | 101558 (85%) | 60 (0%)   |
| Spray        | 6817               | 815.417      | 5908          | 5978          | 9552          | 12693 (11%) | 6462 (5%)            | 100655 (84%) | 190 (0%)  |

### 90000 requests over 30 seconds

| Scenario    :| Mean response time:| Mean req/sec:| Std deviation:| Resp. time Q2:| Resp. time Q3:| t < 800 ms :| 800 ms < t < 1200 ms:| t > 1200 ms :| failed   :|
|--------------|--------------------|--------------|---------------|---------------|---------------|-------------|----------------------|--------------|-----------|
| Servlet BIO  | 2999               | 724.364      | 4895          | 1656          | 2865          | 16453 (18%) | 12673 (14%)          | 59858 (67%)  | 1016 (1%) |
| Servlet NIO  | 2893               | 1035.542     | 2195          | 2879          | 3986          | 21150 (24%) | 1668 (2%)            | 67159 (75%)  | 23 (0%)   |
| Node.js      | 3024               | 1189.532     | 2049          | 2751          | 4039          | 12896 (14%) | 3026 (3%)            | 74078 (82%)  | 0 (0%)    |
| Node.js sync | 3103               | 884.304      | 2122          | 3150          | 4394          | 18623 (21%) | 2906 (3%)            | 68471 (76%)  | 0 (0%)    |
| Spray        | 3524               | 944.099      | 3809          | 2277          | 5232          | 20446 (23%) | 8500 (9%)            | 60992 (68%)  | 62 (0%)   |


## Raw data

### 120000 requests over 30 seconds

#### Servlet BIO
```
---- Global Information --------------------------------------------------------
> request count                                     120000 (OK=117869 KO=2131  )
> min response time                                      0 (OK=2      KO=0     )
> max response time                                  65049 (OK=63151  KO=65049 )
> mean response time                                  4113 (OK=3965   KO=12295 )
> std deviation                                       6878 (OK=6029   KO=24187 )
> response time 50th percentile                       2281 (OK=2302   KO=1     )
> response time 75th percentile                       4008 (OK=4020   KO=1027  )
> mean requests/sec                                841.019 (OK=826.084 KO=14.935)
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         18961 ( 16%)
> 800 ms < t < 1200 ms                                9363 (  8%)
> t > 1200 ms                                        89545 ( 75%)
> failed                                              2131 (  2%)
```

#### Servlet NIO
```
---- Global Information --------------------------------------------------------
> request count                                     120000 (OK=119443 KO=557   )
> min response time                                      0 (OK=4      KO=0     )
> max response time                                  60699 (OK=59692  KO=60699 )
> mean response time                                  4673 (OK=4681   KO=3053  )
> std deviation                                       3928 (OK=3833   KO=13109 )
> response time 50th percentile                       4062 (OK=4077   KO=0     )
> response time 75th percentile                       6131 (OK=6142   KO=1     )
> mean requests/sec                                995.198 (OK=990.579 KO=4.619 )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         11188 (  9%)
> 800 ms < t < 1200 ms                                5195 (  4%)
> t > 1200 ms                                       103060 ( 86%)
> failed                                               557 (  0%)
```

#### Node.js
```
---- Global Information --------------------------------------------------------
> request count                                     120000 (OK=119990 KO=10    )
> min response time                                      3 (OK=3      KO=60003 )
> max response time                                  60009 (OK=59213  KO=60009 )
> mean response time                                  4131 (OK=4126   KO=60005 )
> std deviation                                       3469 (OK=3432   KO=2     )
> response time 50th percentile                       3809 (OK=3808   KO=60005 )
> response time 75th percentile                       5613 (OK=5611   KO=60006 )
> mean requests/sec                                1136.342 (OK=1136.247 KO=0.095 )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         18349 ( 15%)
> 800 ms < t < 1200 ms                                6073 (  5%)
> t > 1200 ms                                        95568 ( 80%)
> failed                                                10 (  0%)
```

#### Node.js sync
```
---- Global Information --------------------------------------------------------
> request count                                     120000 (OK=119940 KO=60    )
> min response time                                      5 (OK=5      KO=60001 )
> max response time                                  61964 (OK=59941  KO=61964 )
> mean response time                                  6908 (OK=6882   KO=60101 )
> std deviation                                       5658 (OK=5532   KO=327   )
> response time 50th percentile                       6189 (OK=6185   KO=60006 )
> response time 75th percentile                       9904 (OK=9897   KO=60008 )
> mean requests/sec                                825.764 (OK=825.351 KO=0.413 )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         11806 ( 10%)
> 800 ms < t < 1200 ms                                6576 (  5%)
> t > 1200 ms                                       101558 ( 85%)
> failed                                                60 (  0%)
```

#### Spray
```
---- Global Information --------------------------------------------------------
> request count                                     120000 (OK=119810 KO=190   )
> min response time                                      4 (OK=4      KO=60000 )
> max response time                                  62712 (OK=61119  KO=62712 )
> mean response time                                  6817 (OK=6732   KO=60076 )
> std deviation                                       5908 (OK=5519   KO=296   )
> response time 50th percentile                       5978 (OK=5968   KO=60004 )
> response time 75th percentile                       9552 (OK=9531   KO=60009 )
> mean requests/sec                                815.417 (OK=814.126 KO=1.291 )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         12693 ( 11%)
> 800 ms < t < 1200 ms                                6462 (  5%)
> t > 1200 ms                                       100655 ( 84%)
> failed                                               190 (  0%)
```

### 90000 requests over 30 seconds

#### Servlet BIO
```
---- Global Information --------------------------------------------------------
> request count                                      90000 (OK=88984  KO=1016  )
> min response time                                      0 (OK=3      KO=0     )
> max response time                                  60011 (OK=45378  KO=60011 )
> mean response time                                  2999 (OK=2961   KO=6279  )
> std deviation                                       4895 (OK=4503   KO=18336 )
> response time 50th percentile                       1656 (OK=1673   KO=0     )
> response time 75th percentile                       2865 (OK=2880   KO=1     )
> mean requests/sec                                724.364 (OK=716.186 KO=8.177 )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         16453 ( 18%)
> 800 ms < t < 1200 ms                               12673 ( 14%)
> t > 1200 ms                                        59858 ( 67%)
> failed                                              1016 (  1%)
```

#### Servlet NIO
```
---- Global Information --------------------------------------------------------
> request count                                      90000 (OK=89977  KO=23    )
> min response time                                      0 (OK=3      KO=0     )
> max response time                                  60010 (OK=42358  KO=60010 )
> mean response time                                  2893 (OK=2889   KO=18548 )
> std deviation                                       2195 (OK=2136   KO=27424 )
> response time 50th percentile                       2879 (OK=2875   KO=1057  )
> response time 75th percentile                       3986 (OK=3986   KO=60001 )
> mean requests/sec                                1035.542 (OK=1035.277 KO=0.26                   5 )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         21150 ( 24%)
> 800 ms < t < 1200 ms                                1668 (  2%)
> t > 1200 ms                                        67159 ( 75%)
> failed                                                23 (  0%)
```

#### Node.js
```
---- Global Information --------------------------------------------------------
> request count                                      90000 (OK=90000  KO=0     )
> min response time                                      2 (OK=2      KO=-     )
> max response time                                  45630 (OK=45630  KO=-     )
> mean response time                                  3024 (OK=3024   KO=-     )
> std deviation                                       2049 (OK=2049   KO=-     )
> response time 50th percentile                       2751 (OK=2752   KO=-     )
> response time 75th percentile                       4039 (OK=4038   KO=-     )
> mean requests/sec                                1189.532 (OK=1189.532 KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         12896 ( 14%)
> 800 ms < t < 1200 ms                                3026 (  3%)
> t > 1200 ms                                        74078 ( 82%)
> failed                                                 0 (  0%)
```

#### Node.js sync
```
---- Global Information --------------------------------------------------------
> request count                                      90000 (OK=90000  KO=0     )
> min response time                                      4 (OK=4      KO=-     )
> max response time                                  48265 (OK=48265  KO=-     )
> mean response time                                  3103 (OK=3103   KO=-     )
> std deviation                                       2122 (OK=2122   KO=-     )
> response time 50th percentile                       3150 (OK=3150   KO=-     )
> response time 75th percentile                       4394 (OK=4395   KO=-     )
> mean requests/sec                                884.304 (OK=884.304 KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         18623 ( 21%)
> 800 ms < t < 1200 ms                                2906 (  3%)
> t > 1200 ms                                        68471 ( 76%)
> failed                                                 0 (  0%)
```

#### Spray
```
---- Global Information --------------------------------------------------------
> request count                                      90000 (OK=89938  KO=62    )
> min response time                                      3 (OK=3      KO=60001 )
> max response time                                  60064 (OK=55314  KO=60064 )
> mean response time                                  3524 (OK=3485   KO=60011 )
> std deviation                                       3809 (OK=3510   KO=10    )
> response time 50th percentile                       2277 (OK=2275   KO=60010 )
> response time 75th percentile                       5232 (OK=5225   KO=60010 )
> mean requests/sec                                944.099 (OK=943.448 KO=0.65  )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         20446 ( 23%)
> 800 ms < t < 1200 ms                                8500 (  9%)
> t > 1200 ms                                        60992 ( 68%)
> failed                                                62 (  0%)
```

### 60000 requests over 30 seconds

#### Servlet BIO
```
---- Global Information --------------------------------------------------------
> request count                                      60000 (OK=60000  KO=0     )
> min response time                                      2 (OK=2      KO=-     )
> max response time                                  14934 (OK=14934  KO=-     )
> mean response time                                  1503 (OK=1503   KO=-     )
> std deviation                                        818 (OK=818    KO=-     )
> response time 50th percentile                       1703 (OK=1703   KO=-     )
> response time 75th percentile                       1950 (OK=1950   KO=-     )
> mean requests/sec                                1175.825 (OK=1175.825 KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         12329 ( 21%)
> 800 ms < t < 1200 ms                                6188 ( 10%)
> t > 1200 ms                                        41483 ( 69%)
> failed                                                 0 (  0%)
```

#### Servlet NIO
```
---- Global Information --------------------------------------------------------
> request count                                      60000 (OK=60000  KO=0     )
> min response time                                      2 (OK=2      KO=-     )
> max response time                                  19753 (OK=19753  KO=-     )
> mean response time                                  1524 (OK=1524   KO=-     )
> std deviation                                        806 (OK=806    KO=-     )
> response time 50th percentile                       1713 (OK=1713   KO=-     )
> response time 75th percentile                       1983 (OK=1983   KO=-     )
> mean requests/sec                                1177.371 (OK=1177.371 KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         10751 ( 18%)
> 800 ms < t < 1200 ms                                8550 ( 14%)
> t > 1200 ms                                        40699 ( 68%)
> failed                                                 0 (  0%)
```

#### Node.js
```
---- Global Information --------------------------------------------------------
> request count                                      60000 (OK=60000  KO=0     )
> min response time                                      3 (OK=3      KO=-     )
> max response time                                  11593 (OK=11593  KO=-     )
> mean response time                                  1412 (OK=1412   KO=-     )
> std deviation                                        804 (OK=804    KO=-     )
> response time 50th percentile                       1578 (OK=1578   KO=-     )
> response time 75th percentile                       1950 (OK=1950   KO=-     )
> mean requests/sec                                1205.86 (OK=1205.86 KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         16547 ( 28%)
> 800 ms < t < 1200 ms                                2193 (  4%)
> t > 1200 ms                                        41260 ( 69%)
> failed                                                 0 (  0%)
```

#### Node.js sync
```
---- Global Information --------------------------------------------------------
> request count                                      60000 (OK=60000  KO=0     )
> min response time                                      2 (OK=2      KO=-     )
> max response time                                   6067 (OK=6067   KO=-     )
> mean response time                                  1602 (OK=1602   KO=-     )
> std deviation                                        941 (OK=941    KO=-     )
> response time 50th percentile                       1882 (OK=1882   KO=-     )
> response time 75th percentile                       2201 (OK=2201   KO=-     )
> mean requests/sec                                1120.427 (OK=1120.427 KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         14110 ( 24%)
> 800 ms < t < 1200 ms                                7652 ( 13%)
> t > 1200 ms                                        38238 ( 64%)
> failed                                                 0 (  0%)
```

#### Spray
```
---- Global Information --------------------------------------------------------
> request count                                      60000 (OK=60000  KO=0     )
> min response time                                      5 (OK=5      KO=-     )
> max response time                                  11960 (OK=11960  KO=-     )
> mean response time                                  1132 (OK=1132   KO=-     )
> std deviation                                        961 (OK=961    KO=-     )
> response time 50th percentile                       1020 (OK=1020   KO=-     )
> response time 75th percentile                       1396 (OK=1397   KO=-     )
> mean requests/sec                                1401.313 (OK=1401.313 KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         23753 ( 40%)
> 800 ms < t < 1200 ms                               11905 ( 20%)
> t > 1200 ms                                        24342 ( 41%)
> failed                                                 0 (  0%)
```

### 52500 requests over 30 seconds

#### Servlet BIO
```
---- Global Information --------------------------------------------------------
> request count                                      52500 (OK=52500  KO=0     )
> min response time                                      4 (OK=4      KO=-     )
> max response time                                   3914 (OK=3914   KO=-     )
> mean response time                                  1024 (OK=1024   KO=-     )
> std deviation                                        515 (OK=515    KO=-     )
> response time 50th percentile                       1092 (OK=1092   KO=-     )
> response time 75th percentile                       1297 (OK=1297   KO=-     )
> mean requests/sec                                1228.271 (OK=1228.271 KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         13269 ( 25%)
> 800 ms < t < 1200 ms                               18258 ( 35%)
> t > 1200 ms                                        20973 ( 40%)
> failed                                                 0 (  0%)

```

#### Servlet NIO
```
---- Global Information --------------------------------------------------------
> request count                                      52500 (OK=52500  KO=0     )
> min response time                                      3 (OK=3      KO=-     )
> max response time                                  14373 (OK=14373  KO=-     )
> mean response time                                  1065 (OK=1065   KO=-     )
> std deviation                                        566 (OK=566    KO=-     )
> response time 50th percentile                       1175 (OK=1175   KO=-     )
> response time 75th percentile                       1401 (OK=1401   KO=-     )
> mean requests/sec                                 1193.1 (OK=1193.1 KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         14361 ( 27%)
> 800 ms < t < 1200 ms                               13582 ( 26%)
> t > 1200 ms                                        24557 ( 47%)
> failed                                                 0 (  0%)
```

#### Node.js
```
---- Global Information --------------------------------------------------------
> request count                                      52500 (OK=52500  KO=0     )
> min response time                                      2 (OK=2      KO=-     )
> max response time                                   4273 (OK=4273   KO=-     )
> mean response time                                  1035 (OK=1035   KO=-     )
> std deviation                                        558 (OK=558    KO=-     )
> response time 50th percentile                       1144 (OK=1144   KO=-     )
> response time 75th percentile                       1458 (OK=1459   KO=-     )
> mean requests/sec                                1206.148 (OK=1206.148 KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         15208 ( 29%)
> 800 ms < t < 1200 ms                               14026 ( 27%)
> t > 1200 ms                                        23266 ( 44%)
> failed                                                 0 (  0%)
```

#### Node.js sync
```
---- Global Information --------------------------------------------------------
> request count                                      52500 (OK=52500  KO=0     )
> min response time                                      3 (OK=3      KO=-     )
> max response time                                   8870 (OK=8870   KO=-     )
> mean response time                                  1182 (OK=1182   KO=-     )
> std deviation                                        716 (OK=716    KO=-     )
> response time 50th percentile                       1246 (OK=1246   KO=-     )
> response time 75th percentile                       1604 (OK=1604   KO=-     )
> mean requests/sec                                1149.853 (OK=1149.853 KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         12852 ( 24%)
> 800 ms < t < 1200 ms                               11265 ( 21%)
> t > 1200 ms                                        28383 ( 54%)
> failed                                                 0 (  0%)
```

#### Spray
```
---- Global Information --------------------------------------------------------
> request count                                      52500 (OK=52500  KO=0     )
> min response time                                      5 (OK=5      KO=-     )
> max response time                                   4808 (OK=4808   KO=-     )
> mean response time                                   875 (OK=875    KO=-     )
> std deviation                                        642 (OK=642    KO=-     )
> response time 50th percentile                        868 (OK=868    KO=-     )
> response time 75th percentile                       1251 (OK=1251   KO=-     )
> mean requests/sec                                1335.946 (OK=1335.946 KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         24073 ( 46%)
> 800 ms < t < 1200 ms                               13406 ( 26%)
> t > 1200 ms                                        15021 ( 29%)
> failed                                                 0 (  0%)
```