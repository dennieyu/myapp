JVM 구조
=====
<img title="JVM" src="./jvm.png" alt="example" width="500px">

PID 확인
=====
```
./jps -v

44608  -XX:+IgnoreUnrecognizedVMOptions --add-modules=ALL-SYSTEM -Xms64m -Xmx1024m
21220 Jps -Dapplication.home=C:\javadev\jdk\jdk-15 -Xms8m -Djdk.module.main=jdk.jcmd
39108 AuthserverApplication -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=49513 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=localhost -Dspring.jmx.enabled=true -Dspring.application.admin.enabled=true -XX:TieredStopAtLevel=1 -Dspring.boot.project.name=myapp-authserver -Dfile.encoding=UTF-8
45156  -Dosgi.requiredJavaVersion=11 -Dosgi.dataAreaRequiresExplicitInit=true -Xms256m -Xmx2048m --add-modules=ALL-SYSTEM -javaagent:C:\javadev\programs\sts-bundle\sts-4.9.0.RELEASE\lombok.jar
```

GC 모니터링
=====
```
./jstat -gc -h20 23928 1000

 S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT    CGC    CGCT     GCT
22528.0 21504.0  0.0    0.0   220160.0 208600.4  196096.0   30584.3   59160.0 56594.1 8704.0 8203.9     11    0.082   3      0.274   -          -    0.356
22528.0 21504.0  0.0    0.0   220160.0 208600.4  196096.0   30584.3   59160.0 56594.1 8704.0 8203.9     11    0.082   3      0.274   -          -    0.356
22528.0 21504.0  0.0    0.0   220160.0 208600.4  196096.0   30584.3   59160.0 56594.1 8704.0 8203.9     11    0.082   3      0.274   -          -    0.356
22528.0 21504.0  0.0    0.0   220160.0 208600.4  196096.0   30584.3   59160.0 56594.1 8704.0 8203.9     11    0.082   3      0.274   -          -    0.356
22528.0 21504.0  0.0    0.0   220160.0 208600.4  196096.0   30584.3   59160.0 56594.1 8704.0 8203.9     11    0.082   3      0.274   -          -    0.356
22528.0 21504.0  0.0    0.0   220160.0 209701.2  196096.0   30584.3   59160.0 56594.1 8704.0 8203.9     11    0.082   3      0.274   -          -    0.356
22528.0 21504.0  0.0    0.0   220160.0 209701.2  196096.0   30584.3   59160.0 56594.1 8704.0 8203.9     11    0.082   3      0.274   -          -    0.356
22528.0 21504.0  0.0    0.0   220160.0 209701.2  196096.0   30584.3   59160.0 56594.1 8704.0 8203.9     11    0.082   3      0.274   -          -    0.356
```

GC 처리 방식
=====
* -XX:+UseSerialGC: 하나의 스레드를 이용하여 Young 영역과 Old 영역의 정리를 처리
  
* -XX:+UseParallelGC: Young 영역의 정리에 다수의 스레드를 이용하여 처리
  
* -XX:+UseParallelOldGC: Old 영역의 처리도 다수의 스레드를 이용하여 처리
  
* -XX:+UseConcMarkSweepGC: 메이저 GC의 성능 향상을 위해 Old 영역의 정리를 Concurrent 방식으로 처리
  
* -XX:+UseG1GC:
1. G1은 이름을 보면 짐작할 수 있듯, 쓰레기로 가득찬 heap 영역을 집중적으로 수집한다.  
1. G1은 큰 메모리를 가진 멀티 프로세서 시스템에서 사용하기 위해 개발된 GC이다.  
1. GC 일시 정지 시간을 최소화하면서, 따로 설정을 하지 않아도 가능한 한 처리량(throughput)도 확보하는 것이 G1GC의 목표이다.  
1. G1은 Java 9부터 디폴트 GC이다.  
1. G1은 실시간(real time) GC가 아니다. 일시 정지 시간을 최소화하긴 하지만 완전히 없애지는 못한다.  
1. G1은 통계를 계산해가면서 GC 작업량을 조절한다.  

쓰레드 분석하기
=====
```
./jstack 23928

"http-nio-5088-exec-5" #48 daemon prio=5 os_prio=0 tid=0x0000000024392800 nid=0x4228 waiting on condition [0x000000002756f000]
   java.lang.Thread.State: WAITING (parking)
        at sun.misc.Unsafe.park(Native Method)
        - parking to wait for  <0x0000000776e962f8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
        at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:107)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(Thread.java:748)

"http-nio-5088-exec-4" #47 daemon prio=5 os_prio=0 tid=0x0000000024391000 nid=0x702c waiting on condition [0x000000002746e000]
   java.lang.Thread.State: WAITING (parking)
        at sun.misc.Unsafe.park(Native Method)
        - parking to wait for  <0x0000000776e962f8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
        at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:107)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(Thread.java:748)

"http-nio-5088-exec-3" #46 daemon prio=5 os_prio=0 tid=0x0000000024391800 nid=0x9d80 waiting on condition [0x000000002736f000]
   java.lang.Thread.State: WAITING (parking)
        at sun.misc.Unsafe.park(Native Method)
        - parking to wait for  <0x0000000776e962f8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
        at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:107)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(Thread.java:748)
```

finalization 기다리는 오브젝트 검색
=====
```
./jmap -finalizerinfo 23928

No instances waiting for finalization found
```

라이브 오브젝트 검색
=====
```
./jmap -histo 23928 | grep myapp
./jmap -histo:live 23928 | grep myapp (FullGC)

 num     #instances         #bytes  class name
----------------------------------------------
1530:             4             96  myapp.authserver.config.OAuth2Configuration$$EnhancerBySpringCGLIB$$3c5c2d3a$$FastClassBySpringCGLIB$$803acbfe
1531:             4             96  myapp.authserver.config.OAuth2Configuration$$FastClassBySpringCGLIB$$1d3db239
1692:             1             80  myapp.authserver.config.WebSecurityConfiguration$$EnhancerBySpringCGLIB$$ccda40e5
2058:             1             64  myapp.authserver.config.OAuth2Configuration$$EnhancerBySpringCGLIB$$3c5c2d3a
2379:             2             48  myapp.authserver.config.WebSecurityConfiguration$$EnhancerBySpringCGLIB$$ccda40e5$$FastClassBySpringCGLIB$$6fca637e
2380:             2             48  myapp.authserver.config.WebSecurityConfiguration$$FastClassBySpringCGLIB$$e5b5db84
2750:             1             40  myapp.authserver.config.CustomTokenEnhancer
3136:             1             32  myapp.authserver.AuthserverApplication$$EnhancerBySpringCGLIB$$8237259c
3137:             1             32  myapp.authserver.config.CustomOauth2RequestFactory
5141:             1             16  myapp.authserver.config.CustomPasswordEncoder
5142:             1             16  myapp.authserver.config.WebSecurityConfiguration$$Lambda$741/1006552148
5143:             1             16  myapp.authserver.service.CustomUserDetailsService
(base)
```

쓰레드 덤프 획득
=====
```
./jmap -dump:live,file=./dump 23928
```

VisualVM 다운로드
=====
<a href="https://visualvm.github.io/download.html" target="_blank">https://visualvm.github.io/download.html</a>