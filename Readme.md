#  Todo List BackEnd 

​


[todoList-FrontEnd](https://github.com/annajinee/todoList-frontEnd) 프로젝트에서 요청 되는 Todo List Backend API입니다.
  
빌드는 maven을  사용하고 있습니다. 아래 명령어로 todoList 폴더 내에서 컴파일 하고

    mvn complie
todoList/target 폴더에 demo0.0.1-SNAPSHOT.jar 생성 확인 후 아래 명령어로 실행합니다.

    java -jar demo0.0.1-SNAPSHOT.jar


<br>

##  데이터 구조 
H2 DB콘솔 : http://localhost:8080/h2-console/

테이블은 주 테이블인 **TODO_LIST**와 참조관계를 확인하는 테이블인  **TODO_REF** 로 구성되어 있습니다. 

<br>

##### TODO_LIST
<img src="http://drive.google.com/uc?export=view&id=1gTrVCiwUqPmvy_YCQZBtTsofz8Fbp6Vs" style width="400">

##### TODO_REF
<img src="http://drive.google.com/uc?export=view&id=1X9KIGqv_yxugoLY7cv61DZREfVpU7XO7" style width="180">

<br>

**TODO_REF** 테이블의 TODO_ID는 FK로 **TODO_LIST**테이블의 ROWID를 참조 하여 참조 데이터 리스트를 가져 옵니다. 

<img src="http://drive.google.com/uc?export=view&id=1xVOitMO9l3keyZ5-rMby--W2DJF9WAW4" style width="400">
(@OneToMany)

<br>
<br>

## API 명세

#### 1.1 할 일 등록

[Request]

- path : {host}/todo
- method : POST
- parameter

| name       | type| desc         | 필수 값 |
| ---------- |  -----| ------------ | ---- |
| toDo       | string | 할 일 | Y    |
| refId      | Json array  | 참조 아이디  | N |
- example

  ```json
  {
	"toDo":"창문틀 닦기"
	"refId":[1,3,4]
	}
  ```

[Response]

- example

  성공 :

  ```json
  정상 등록
  ```

- 실패

  ```json
  {
    "status": "INTERNAL_SERVER_ERROR",
    "message": "Invalid Parameter : toDo",
    "errorCode": 1002
    }
  ```

<br>

#### 1.2 할 일 리스트 조회

[Request]

- path : {host}/list/{position}/{size}
- method : GET
- parameter

| name       | type| desc         | 필수 값 |
| ---------- |  -----| ------------ | ---- |
| seq       | int | 조회 row id | Y    |
| seq       | int | 조회 row id | Y    |

- example

  ```
  http://localhost:8081/todo/list/0/5
  ```

[Response]

- example

  성공 :

  ``` json
  {
    "dataList": [
        {
            "rowId": 5,
            "toDo": "바람쐬기",
            "regDate": "2019-03-14 19:29:42",
            "modDate": "2019-03-14 19:29:42",
            "endYn": "N",
            "refData": [
                {
                    "toDoId": 5,
                    "refId": 1,
                    "toDoYn": "N"
                },
                {
                    "toDoId": 5,
                    "refId": 2,
                    "toDoYn": "N"
                },
                {
                    "toDoId": 5,
                    "refId": 3,
                    "toDoYn": "N"
                }
            ]
        },
        {
            "rowId": 4,
            "toDo": "청소하기",
            "regDate": "2019-03-14 19:29:31",
            "modDate": "2019-03-14 19:29:31",
            "endYn": "N",
            "refData": []
        },
        {
            "rowId": 3,
            "toDo": "청소하기",
            "regDate": "2019-03-14 19:29:19",
            "modDate": "2019-03-14 19:29:19",
            "endYn": "N",
            "refData": []
        },
        {
            "rowId": 2,
            "toDo": "청소하기",
            "regDate": "2019-03-14 19:29:16",
            "modDate": "2019-03-14 19:29:16",
            "endYn": "N",
            "refData": []
        },
        {
            "rowId": 1,
            "toDo": "안녕",
            "regDate": "2019-03-14 19:24:16",
            "modDate": "2019-03-14 19:24:16",
            "endYn": "N",
            "refData": [
                {
                    "toDoId": 1,
                    "refId": 1,
                    "toDoYn": "N"
                },
                {
                    "toDoId": 1,
                    "refId": 9,
                    "toDoYn": "N"
                }
            ]
        }
    ],
    "pageInfo": {
        "totalCount": 5,
        "totalPage": 1,
        "pageNumber":0
    }
   }
  ```

- 실패

  ```json
  {
    "status": "INTERNAL_SERVER_ERROR",
    "message": "서버오류",
    "errorCode": 1001
   }
  ```

<br>

#### 1.3 할 일 조회

[Request]

- path : {host}/api/todo/{seq}
- method : GET
- parameter

| name       | type| desc         | 필수 값 |
| ---------- |  -----| ------------ | ---- |
| seq       | int | 조회 row id | Y    |

- example

  ```
  http://localhost:8081/api/todo/1
  ```

[Response]

- example

  성공 :

  ``` json
  {
    "rowId": 1,
    "toDo": "화분에 물 주기",
    "regDate": "2019-03-14 19:24:16",
    "modDate": "2019-03-14 19:24:16",
    "endYn": "N",
    "refData": [
        {
            "toDoId": 1,
            "refId": 1,
            "toDoYn": "N"
        },
        {
            "toDoId": 1,
            "refId": 9,
            "toDoYn": "N"
        }
    ]
  }
  ```

- 실패

  ```json
  {
    "status": "INTERNAL_SERVER_ERROR",
    "message": "Not found",
    "errorCode": 1001
   }
  ```

