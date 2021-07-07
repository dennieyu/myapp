1. JVM구조에 대해 설명하시오.  
 `-> 가비지컬렉터, 실행엔진, 클래스로더, 메소드영역, 힙영역, 스택영역`  

2. GC처리 방법에 대해 설명하시오.  
 `-> serial, parallel, CMS, G1GC, young (eden, supervisor), old (heap), native (perm, metaspace)`  

3. HashMap vs HashTable vs ConcurrentHashMap의 차이를 설명하시오.  
 `-> hashmap (not thread-safe), hashtable, concurrenthashmap (thread-safe)`  

4. 접근제어자에 대해 설명하시오.  
 `-> private < default < protected < public`  

5. interface와 abstract의 차이를 설명하시오.  
 `-> interface (명세만), abstract (abstract 메소드가 하나라도 존재)`  

6. StringBuilder와 StringBuffer의 차이에 대해 설명하시오.  
 `-> StringBuffer (thread-safe), StringBuilder (not thread-safe), 둘다 mutable`  

7. try-with-resources에 대해 설명하시오.  
 `-> java 7 부터 지원, closable 객체 자원 해제`  

8. Synchronize에 대해 설명하시오.  
 `-> 쓰레드 동기화 제어 방법`  

9. Synchronize를 하기 위한 방법은 무엇이 있나요?  
 `-> 메소드 레벨, 블럭 영역`  

10. static은 메모리 구조 중 어디에 올라가나요?  
 `-> method area`  

11. 컬렉션 프레임워크에 대해 설명하시오.  
 `-> 생성, 수집, 거르기, 접기 (of, map, filter, reduce)`  

12. 제네릭에 대해 설명해주시고, 왜 쓰는지 어디세 써 봤는지 알려주세요.  
 `-> 객체를 미리 명시, 형변환하지 않고 사용, Persistence 접근하는 Service 구현 시 자료형 별로 메소드를 만들지 않기 위해서 사용`  

13. Vector와 List 차이에 대해 설명하시오.  
 `-> Vector (thread-safe), List (not thread-safe)`  

14. 오버로딩과 오버라이딩의 차이는?  
 `-> 오버로딩 (같은 메소드명), 오버라이딩 (메소드 재정의)`  

15. CheckedException과 UnCheckedException의 차이를 설명하시오.  
 `-> CheckedException (컴파일 시점), UnCheckedException (런타임 시점)`  

16. OOP란 무엇인가요?  
 `-> 객체 지향 프로그래밍 (데이터 중심)`  

17. final / finally / finalize 의 차이를 설명하시오.  
 `-> final (고정), finally (최종 실행), finalize (자원 해제)`  

18. new String()과 ""의 차이에 대해 설명해주세요.  
 `-> new String() (다른 주소값), "" (같은 주소값)`  

19. 스프링 IOC가 무엇인가요?  
 `-> 개발자에서 컨테이너로 객체의 생성 및 생명 주기 제어가 바뀜, Ioc Container (BeanFactory, ApplicationContext)`  

20. OOP와 AOP에 대한 차이를 설명해주세요.  
 `-> OOP (객체지향), AOP (관점지향), OOP를 더욱 OOP답게 사용, @Pointcut, @Before, @AfterReturning, @Around`  

21. POJO가 무엇인가요?  
 `-> 순수 자바 객체, Java Bean (어노테이션, 생성자, getter, setter, .. )과 구별`<br>
 `-> 특정 인터페이스나 클래스를 상속하지 않고, 순수하게 getter, setter로만 구성된(어디에도 종속되지 않은) 자바 객체를 말함`
