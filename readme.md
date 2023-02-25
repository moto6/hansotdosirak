# 결론
- lock 을 걸자
<img width="312" alt="image" src="https://user-images.githubusercontent.com/31065684/221095686-81a8709c-62ce-4a9a-8598-8f57217f2e8a.png">
- 설명 : 2개의 프로세스에서 10000번 요청 날릴때
  - 베이스라인, 리피터블 리드는 정합성 꺠짐

## 테스트돌리기

- 프로젝트 루트에서 

```shell
ab -n 10000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/1' && sleep 3 &&
ab -n 10000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v1/2' && sleep 3 &&
ab -n 10000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v2/3' && sleep 3 &&
ab -n 10000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v3/4'
```


```shell
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/1' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v1/2' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v2/3' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v3/4' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v4/5' 
```

### with 웜업

```shell

ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/1' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v1/2' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v2/3' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v3/4' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v4/5' && sleep 10 &&
sleep 20 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/6' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v1/7' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v2/8' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v3/9' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v4/10' 
```
- 2회차 성능  / H2  ( 10초간만 쉴떄)
```text
Requests per second:    4619.84 [#/sec] (mean)
Requests per second:    5040.21 [#/sec] (mean)
Requests per second:    3973.78 [#/sec] (mean)
Requests per second:    5994.94 [#/sec] (mean)
Requests per second:    8446.17 [#/sec] (mean)
Requests per second:    6802.37 [#/sec] (mean)
Requests per second:    185.07 [#/sec] (mean) 락
Requests per second:    4211.93 [#/sec] (mean)
Requests per second:    7691.14 [#/sec] (mean)
Requests per second:    8318.33 [#/sec] (mean)

```


- 2회차 성능 / H2  ( 1회차-2회차 간 20초 추가 휴식시간)
```text
Requests per second:    3896.80 [#/sec] (mean)
Requests per second:    6055.64 [#/sec] (mean)
Requests per second:    4123.19 [#/sec] (mean)
Requests per second:    7881.26 [#/sec] (mean)
Requests per second:    7604.00 [#/sec] (mean)
# 여기서 20초 쉼
Requests per second:    5196.48 [#/sec] (mean)
Requests per second:    5529.13 [#/sec] (mean)
Requests per second:    4327.13 [#/sec] (mean)
Requests per second:    8117.16 [#/sec] (mean)
Requests per second:    7920.72 [#/sec] (mean)

```


- 성능에 관하여
```
1번 : Requests per second:    4411.54 [#/sec] (mean)

2번 : Requests per second:    4757.34 [#/sec] (mean)

3번 : Requests per second:    3883.90 [#/sec] (mean)

4번 : Requests per second:    242.77 [#/sec] (mean)

```


```
1회차
Requests per second:    427.24 [#/sec] (mean)
Requests per second:    394.24 [#/sec] (mean)
Requests per second:    344.07 [#/sec] (mean)
Requests per second:    438.73 [#/sec] (mean)
Requests per second:    617.80 [#/sec] (mean)

2회차
Requests per second:    361.40 [#/sec] (mean)
Requests per second:    380.13 [#/sec] (mean)
Requests per second:    331.41 [#/sec] (mean)
Requests per second:    450.20 [#/sec] (mean)
Requests per second:    601.34 [#/sec] (mean)

```





### 많아지는거, mysql

```shell

ab -n 15000 -c 5 -p data.json -T 'application/json' 'http://localhost:8080/post/1' && sleep 10 &&
ab -n 15000 -c 5 -p data.json -T 'application/json' 'http://localhost:8080/post/v1/2' && sleep 10 &&
ab -n 15000 -c 5 -p data.json -T 'application/json' 'http://localhost:8080/post/v2/3' && sleep 10 &&
ab -n 15000 -c 5 -p data.json -T 'application/json' 'http://localhost:8080/post/v3/4' && sleep 10 &&
ab -n 15000 -c 5 -p data.json -T 'application/json' 'http://localhost:8080/post/v4/5' && sleep 10 &&
sleep 20 &&
ab -n 15000 -c 5 -p data.json -T 'application/json' 'http://localhost:8080/post/6' && sleep 10 &&
ab -n 15000 -c 5 -p data.json -T 'application/json' 'http://localhost:8080/post/v1/7' && sleep 10 &&
ab -n 15000 -c 5 -p data.json -T 'application/json' 'http://localhost:8080/post/v2/8' && sleep 10 &&
ab -n 15000 -c 5 -p data.json -T 'application/json' 'http://localhost:8080/post/v3/9' && sleep 10 &&
ab -n 15000 -c 5 -p data.json -T 'application/json' 'http://localhost:8080/post/v4/10' 
```

## 결과

```text
Requests per second:    918.08 [#/sec] (mean)
Requests per second:    593.59 [#/sec] (mean)
Requests per second:    762.80 [#/sec] (mean)
Requests per second:    492.10 [#/sec] (mean)
Requests per second:    680.36 [#/sec] (mean)
Requests per second:    1061.91 [#/sec] (mean)
Requests per second:    507.51 [#/sec] (mean)
Requests per second:    759.94 [#/sec] (mean)
Requests per second:    611.80 [#/sec] (mean)
Requests per second:    784.39 [#/sec] (mean)

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


