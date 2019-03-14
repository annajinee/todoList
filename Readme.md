##  Todo List BackEnd API 명세 

​






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
        "totalPage": 1
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

