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
  http://localhost:8081/todo/list/0/2
  ```

[Response]

- example

  성공 :

  ``` json
  {
    "dataList": [
        {
            "rowId": 1,
            "toDo": "청소하기",
            "regDate": "2019-03-14 17:23:44",
            "modDate": "2019-03-14 17:23:44",
            "endYn": "N",
            "refIds": "null"
        },
        {
            "rowId": 2,
            "toDo": "빨래하기",
            "regDate": "2019-03-14 16:36:44",
            "modDate": "2019-03-14 16:36:44",
            "endYn": "N",
            "refIds": "[1]"
        }
    ],
    "pageInfo": {
        "totalCount": 15,
        "totalPage": 8
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
  http://localhost:8081/api/todo/41
  ```

[Response]

- example

  성공 :

  ``` json
  {
    "toDoListData": {
        "rowId": 41,
        "toDo": "물 주기",
        "regDate": "2019-03-14 16:24:47",
        "modDate": "2019-03-14 16:24:47",
        "endYn": "N",
        "refIds": "[1,9]"
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

