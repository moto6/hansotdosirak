# 결론
- lock 을 걸자
<img width="312" alt="image" src="https://user-images.githubusercontent.com/31065684/221095686-81a8709c-62ce-4a9a-8598-8f57217f2e8a.png">


## 테스트돌리기

- 프로젝트 루트에서 

```shell
ab -n 10000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/1' && sleep 3 &&
ab -n 10000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v1/2' && sleep 3 &&
ab -n 10000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v2/3'
```



## 베이스라인 코드 
```shell
# ab -n 15000 -c 1 -p data.json -T 'application/json' 'http://localhost:8080/post/1'
ab -n 15000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/1'
```

### 결과
```
```



## 1트 - 락만걸기

- 테스트
```shell
# ab -n 15000 -c 1 -p data.json -T 'application/json' 'http://localhost:8080/post/v1/2'
ab -n 15000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v1/2'
```

```

```



## 2트 - 리피터블 리드

- 
```shell
# ab -n 15000 -c 1 -p data.json -T 'application/json' 'http://localhost:8080/post/v2/3'
ab -n 15000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v2/3'
```

```
```


