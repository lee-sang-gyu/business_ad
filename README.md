# API 목록
- Host:localhost:8080/api
## 광고주 정보
|URL                               |Method                 |기능            |
|----------------------------------|-----------------------|----------------|
|/advertiser                       |GET                    |광고주 전체 조회 |
|/advertiser/{id}                  |GET                    |광고주 ID로 조회 |
|/advertiser/{id}/campaign         |GET                    |광고주 ID로 조회: 캠페인정보 포함|
|/advertiser/{id}/campaign_detail  |GET                    |광고주 ID로 조회: 캠페인정보 세부정보 포함|
|/advertiser                      |POST                    |광고주 생성|
|/advertiser/{id}                 |PUT                    |광고주 이름변경|
|/advertiser/{id}                 |DELETE                 |광고주 삭제|
## 광고주 생성

Request :
- Method : POST
- Endpoint : `/api/advertiser`
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json 
{
    "id" : "long, unique",
    "name" : "string"
}
```

Response :

```json 
{
    "code" : "number",
    "status" : "string",
    "data" : {
         "id" : "long, unique",
         "name" : "string",
     }
}
```

## 광고주 조회

Request :
- Method : GET
- All Endpoint : `/api/advertiser`
- Id Endpoint : `/api/advertiser/{id_advertiser}`
- Campaign Endpoint : `/api/advertiser/{id_advertiser}/campaign`
- Detail Endpoint : `/api/advertiser/{id_advertiser}/campaign_detail`
- Header :
    - Accept: application/json

Response :
[ALL]
```json 
{
    "code" : "number",
    "status" : "string",
    "data" : 
     {
         "id" : "long, unique",
         "name" : "string"
     },
     {
         "id" : "long, unique",
         "name" : "string"
     }
}
```
[Id]
```json 
{
    "code" : "number",
    "status" : "string",
    "data" : {
         "id" : "long, unique",
         "name" : "string"
     }
}
```
[Campaign]
```json 
{
    "code" : "number",
    "status" : "string",
    "data" : 
     {
         "id" : "Long, unique",
         "name" : "string",
         "campaign": [
            {
                "id": "long, unique",
                "name": "string",
                "start_date": "date",
                "end_date": "date",
                "subject_list": "integer",
            }
        ]
     }
}
```
[Campaign_Detail]
```json 
{
    "code" : "number",
    "status" : "string",
    "data" : 
     {
         "id" : "Long, unique",
         "name" : "string",
         "campaign": [
            {
                "id": "long, unique",
                "name": "string",
                "start_date": "date",
                "end_date": "date",
                "subject_list": "integer",
                "adcontent": [
                    {
                        "id": "long, unique",
                        "content": "string",
                        "image_url": "string",
                        "btn_text": "string",
                        "btn_url": "string"
                    }
            }
        ]
     }
}
```
## 광고주 변경

Request :
- Method : PUT
- Endpoint : `/api/advertiser/{id_advertiser}`
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json 
{
    "name" : "string"
}
```

Response :

```json 
{
    "code" : "number",
    "status" : "string",
    "data" : {
         "id" : "string, unique",
         "name" : "string"
     }
}
```

## 광고주 삭제

Request :
- Method : DELETE
- Endpoint : `/api/advertiser/{id_advertiser}`
- Header :
    - Accept: application/json

Response :

```json 
{
    "code" : "number",
    "status" : "string"
}
```

## 광고 내용
|URL                               |Method                 |기능            |
|----------------------------------|-----------------------|----------------|
|/adcontent                       |GET                    |광고 내용 전체 조회 |
|/adcontent/id/{id}                |GET                    |광고 내용 ID로 조회 |
|/adcontent/{content}             |GET                    |광고 내용(Content)으로 조회|
|/adcontent             |POST                    |광고 내용 생성|
|/adcontent/id/{id}             |PUT                    |광고 내용 변경|
|/adcontent/{content}             |PUT                    |광고 내용(Content) 변경|
|/adcontent/id/{id}            |DELETE                    |광고 내용 삭제|
|/adcontent/{content}            |DELETE                    |광고 내용(Content) 삭제|
## 광고 내용 생성

Request :
- Method : POST
- Endpoint : `/api/adcontent`
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json 
{
    "id" : "long, unique",
    "content" : "string"
}
```

Response :

```json 
{
    "code" : "number",
    "status" : "string",
    "data" : {
         "id": "long, unique",
        "content": "string",
        "image_url": "string",
        "btn_text": "string",
        "btn_url": "string"
     }
}
```
## 광고 내용 조회

Request :
- Method : GET
- All Endpoint : `/api/adcontent`
- Id Endpoint : `/api/adcontent/id/{id}`
- Content Endpoint : `/api/aadcontent/{content}`
- Header :
    - Accept: application/json

Response :
[ALL]
```json 
{
    "code" : "number",
    "status" : "string",
    "data" : [
        {
            "id": "long, unique",
            "content": "string",
            "image_url": "string",
            "btn_text": "string",
            "btn_url": "string"
        }
        ]
}
```
[Id]
```json 
{
    "code" : "number",
    "status" : "string",
    "data" : 
        {
            "id": "long, unique",
            "content": "string",
            "image_url": "string",
            "btn_text": "string",
            "btn_url": "string"
        }
}
```
[Content]
```json 
{
    "code" : "number",
    "status" : "string",
    "data" : [
        {
            "id": "long, unique",
            "content": "string",
            "image_url": "string",
            "btn_text": "string",
            "btn_url": "string"
        }
        ]
}
```

