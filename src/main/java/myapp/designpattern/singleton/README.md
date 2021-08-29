설명
------------------

> 하나의 객체만을 생성해 이후에 호출된 곳에서는 생성된 객체를 반환하여 프로그램 전반에서 하나의 인스턴스만을 사용하게 하는 패턴

- 주로 공통된 객체를 여러개 생성해서 사용하는 DBCP (DataBase Connection Pool) 와 같은 상황에서 많이 사용

### 문제점 

- Multi-Thread 환경에서의 문제점

```
    public class Printer {
    	private static Printer printer = null;
    	private int count = 0;
    
    	private Printer(){}
    
    	public static Printer getInstance() {
    		if(printer == null) {
    			printer = new Printer();
    		}
    		return printer;
    	}
    
    	public void print(String input) {
    		count++;
    		System.out.println(input + "count : "+ count);
    	}
    }
```

### 해결

- 정적 변수에 인스턴스를 만들어 바로 초기화
- 인스턴스를 만드는 메서드에 동기화

```
    public class Printer {
    	private static Printer printer = new Printer();
    	private static int count = 0;
    
    	private Printer(){}
    
    	public static Printer getInstance() {
    		return printer;
    	}
    
    	public synchronized static void print(String input) {
    		count++;
    		System.out.println(input + "count : "+ count);
    	}
    }
```