## Results of a non-blocking vs blocking benchmark

Running load tests with **Gatling 2.1.4** on a **t2.small** instance
Running **Embedded Tomcat 7 (with Java 7)** and **Node.js v0.12.0** on a **m3.large** instance

Each scenario was run at least twice. The results are of the first run, and the second run was to prove the results are consistent.

### 90000 requests over 30 seconds

#### Servlet BIO
```
---- Global Information --------------------------------------------------------
> request count                                      90000 (OK=89101  KO=899   )
> min response time                                      0 (OK=3      KO=0     )
> max response time                                  60014 (OK=37558  KO=60014 )
> mean response time                                  2862 (OK=2814   KO=7621  )
> std deviation                                       4662 (OK=4208   KO=19962 )
> response time 50th percentile                       1600 (OK=1616   KO=0     )
> response time 75th percentile                       2971 (OK=2984   KO=1     )
> mean requests/sec                                733.329 (OK=726.004 KO=7.325 )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         21340 ( 24%)
> 800 ms < t < 1200 ms                               13907 ( 15%)
> t > 1200 ms                                        53854 ( 60%)
> failed                                               899 (  1%)
```

#### Servlet NIO
```
---- Global Information --------------------------------------------------------
> request count                                      90000 (OK=89743  KO=257   )
> min response time                                      0 (OK=3      KO=0     )
> max response time                                  63514 (OK=60895  KO=63514 )
> mean response time                                  2160 (OK=2118   KO=16926 )
> std deviation                                       3449 (OK=3214   KO=18449 )
> response time 50th percentile                       1519 (OK=1513   KO=12283 )
> response time 75th percentile                       2754 (OK=2748   KO=22311 )
> mean requests/sec                                929.723 (OK=927.068 KO=2.655 )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         28858 ( 32%)
> 800 ms < t < 1200 ms                               10702 ( 12%)
> t > 1200 ms                                        50183 ( 56%)
> failed                                               257 (  0%)
```

#### Node.js
(Node.js just crashes here)
```
---- Global Information --------------------------------------------------------
> request count                                      90000 (OK=20368  KO=69632 )
> min response time                                      1 (OK=6      KO=1     )
> max response time                                  60010 (OK=8401   KO=60010 )
> mean response time                                   501 (OK=1130   KO=318   )
> std deviation                                       1182 (OK=1246   KO=1096  )
> response time 50th percentile                         10 (OK=562    KO=8     )
> response time 75th percentile                        460 (OK=1797   KO=15    )
> mean requests/sec                                593.257 (OK=134.261 KO=458.996)
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         11743 ( 13%)
> 800 ms < t < 1200 ms                                1369 (  2%)
> t > 1200 ms                                         7256 (  8%)
> failed                                             69632 ( 77%)
```

### 60000 requests over 30 seconds:

#### Servlet BIO
```
---- Global Information --------------------------------------------------------
> request count                                      60000 (OK=60000  KO=0     )
> min response time                                      6 (OK=6      KO=-     )
> max response time                                   7735 (OK=7735   KO=-     )
> mean response time                                  1144 (OK=1144   KO=-     )
> std deviation                                        718 (OK=718    KO=-     )
> response time 50th percentile                       1097 (OK=1097   KO=-     )
> response time 75th percentile                       1464 (OK=1464   KO=-     )
> mean requests/sec                                1432.46 (OK=1432.46 KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         18319 ( 31%)
> 800 ms < t < 1200 ms                               17210 ( 29%)
> t > 1200 ms                                        24471 ( 41%)
> failed                                                 0 (  0%)
```

#### Servlet NIO
```
---- Global Information --------------------------------------------------------
> request count                                      60000 (OK=60000  KO=0     )
> min response time                                      5 (OK=5      KO=-     )
> max response time                                  14922 (OK=14922  KO=-     )
> mean response time                                  1102 (OK=1102   KO=-     )
> std deviation                                        829 (OK=829    KO=-     )
> response time 50th percentile                       1033 (OK=1033   KO=-     )
> response time 75th percentile                       1356 (OK=1356   KO=-     )
> mean requests/sec                                1429.15 (OK=1429.15 KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         19601 ( 33%)
> 800 ms < t < 1200 ms                               17905 ( 30%)
> t > 1200 ms                                        22494 ( 37%)
> failed                                                 0 (  0%)
```

#### Node.js
```
---- Global Information --------------------------------------------------------
> request count                                      60000 (OK=59690  KO=310   )
> min response time                                      5 (OK=5      KO=560   )
> max response time                                  10225 (OK=10225  KO=2922  )
> mean response time                                  1267 (OK=1266   KO=1376  )
> std deviation                                        941 (OK=943    KO=558   )
> response time 50th percentile                       1156 (OK=1152   KO=1693  )
> response time 75th percentile                       1785 (OK=1786   KO=1731  )
> mean requests/sec                                1276.433 (OK=1269.838 KO=6.595 )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         22610 ( 38%)
> 800 ms < t < 1200 ms                                8065 ( 13%)
> t > 1200 ms                                        29015 ( 48%)
> failed                                               310 (  1%)
```

### 52500 requests over 30 seconds

#### Servlet BIO
```
---- Global Information --------------------------------------------------------
> request count                                      52500 (OK=52500  KO=0     )
> min response time                                      5 (OK=5      KO=-     )
> max response time                                   4423 (OK=4423   KO=-     )
> mean response time                                   809 (OK=809    KO=-     )
> std deviation                                        438 (OK=438    KO=-     )
> response time 50th percentile                        886 (OK=887    KO=-     )
> response time 75th percentile                       1068 (OK=1068   KO=-     )
> mean requests/sec                                1412.847 (OK=1412.847 KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         23193 ( 44%)
> 800 ms < t < 1200 ms                               24817 ( 47%)
> t > 1200 ms                                         4490 (  9%)
> failed                                                 0 (  0%)
```

#### Servlet NIO
```
---- Global Information --------------------------------------------------------
> request count                                      52500 (OK=52500  KO=0     )
> min response time                                      6 (OK=6      KO=-     )
> max response time                                   3059 (OK=3059   KO=-     )
> mean response time                                   793 (OK=793    KO=-     )
> std deviation                                        368 (OK=368    KO=-     )
> response time 50th percentile                        930 (OK=930    KO=-     )
> response time 75th percentile                       1063 (OK=1063   KO=-     )
> mean requests/sec                                1426.088 (OK=1426.088 KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         20707 ( 39%)
> 800 ms < t < 1200 ms                               28725 ( 55%)
> t > 1200 ms                                         3068 (  6%)
> failed                                                 0 (  0%)
```

#### Node.js
```
---- Global Information --------------------------------------------------------
> request count                                      52500 (OK=52500  KO=0     )
> min response time                                      5 (OK=5      KO=-     )
> max response time                                   7147 (OK=7147   KO=-     )
> mean response time                                   883 (OK=883    KO=-     )
> std deviation                                        668 (OK=668    KO=-     )
> response time 50th percentile                        812 (OK=812    KO=-     )
> response time 75th percentile                       1160 (OK=1160   KO=-     )
> mean requests/sec                                1348.401 (OK=1348.401 KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         25783 ( 49%)
> 800 ms < t < 1200 ms                               15701 ( 30%)
> t > 1200 ms                                        11016 ( 21%)
> failed                                                 0 (  0%)
```