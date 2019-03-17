#  Todo List BackEnd 

​


[todoList-FrontEnd](https://github.com/annajinee/todoList-frontEnd) 프로젝트에서 요청 되는 Todo List Backend API입니다.
  
빌드는 maven을  사용하고 있습니다. 아래 명령어로 todoList 폴더 내에서 패키징 후
   
    컴파일 : mvn compile
    패키징 : mvn package
    
TodoList/target/ 폴더에 todolist-0.0.1-SNAPSHOT.jar 생성 확인 후 아래 명령어로 실행합니다.

    실행 : java -jar todolist-0.0.1-SNAPSHOT.jar


<br>

##  데이터 구조 
H2 DB콘솔 : http://localhost:8080/h2-console/
<br>
userName : user
Passward : test!
<img src="http://drive.google.com/uc?export=view&id=1x3_LEpKq1W5p9_2-Ythb-Tg8FT0aYqFR" style width="400">
<br>
테이블은 주 테이블인 **TODO_LIST**와 참조관계를 확인하는 테이블인  **TODO_REF** 로 구성되어 있습니다. 

<br>

##### TODO_LIST
<img src="http://drive.google.com/uc?export=view&id=1gTrVCiwUqPmvy_YCQZBtTsofz8Fbp6Vs" style width="400">

##### TODO_REF
<img src="http://drive.google.com/uc?export=view&id=1X9KIGqv_yxugoLY7cv61DZREfVpU7XO7" style width="180">

<br>

**TODO_REF** 테이블의 TODO_ID는 FK로 **TODO_LIST**테이블의 ROWID를 참조 하여 참조 데이터 리스트를 가져 옵니다. 

<br>



문제 해결 전략
- 
- 주 테이블과 참조 테이블간의 OneToMany 관계 설정으로 조인하여 참조 데이터 가져옴
- JSON Object 형태로 데이터 전달 
- 참조 데이터의 경우 JSON Object 내 JSON Array 형태로 전달
- 완료 처리 시 참조테이블 내 해당 아이디의 참조 걸린 아이디들의 완료 여부 확인 후 완료 되지 않았을 경우 throw 처리 

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
    "timestamp": "2019-03-16T22:14:50.061+0000",
    "status": 400,
    "error": "Bad Request",
    "errors": [
        {
            "codes": [
                "NotNull.toDoAddPayload.toDo",
                "NotNull.toDo",
                "NotNull.java.lang.String",
                "NotNull"
            ],
            "arguments": [
                {
                    "codes": [
                        "toDoAddPayload.toDo",
                        "toDo"
                    ],
                    "arguments": null,
                    "defaultMessage": "toDo",
                    "code": "toDo"
                }
            ],
            "defaultMessage": "Invalid Parameter - toDo",
            "objectName": "toDoAddPayload",
            "field": "toDo",
            "rejectedValue": null,
            "bindingFailure": false,
            "code": "NotNull"
        }
    ],
    "message": "Validation failed for object='toDoAddPayload'. Error count: 1",
    "path": "/todo"
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
    "timestamp": "2019-03-16T22:17:14.240+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Failed to convert value of type 'java.lang.String' to required type 'int'; nested exception is java.lang.NumberFormatException: For input string: \"1r0\"",
    "path": "/todo/list/0/1r0"
    }
  ```

<br>

#### 1.3 할 일 및 완료 상태 값 수정 

[Request]

- path : {host}/api/todo/{seq}
- method : PUT
- parameter

| name       | type| desc         | 필수 값 |
| ---------- |  -----| ------------ | ---- |
| seq       | int | 수정할 row id | Y    |

- example

  ```
  http://localhost:8081/api/todo/1
  ```

	
| name       | type| desc         | 필수 값 |
| ---------- |  -----| ------------ | ---- |
| toDo		| string | 할 일 | N    |
| endYn		| string | 완료 값 | N    |


* 할 일 수정 시
  ```json
  {
	"toDo":"화분에 물주기"
	}
  ```
 
 * 완료 상태 값 추정 시	
	  ```json
	  {
		"endYn":'Y'
		}
	  ```
[Response]

- example

  성공 :
  ```json
  true
  ```

- 실패

  ```json
  {
    "status": "NOT_MODIFIED",
    "message": "참조된 일이 완료되지 않음"
   }
  ```

