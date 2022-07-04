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
## 캠페인 정보
|URL                               |Method                 |기능            |
|----------------------------------|-----------------------|----------------|
|/campaign                       |GET                    |캠페인 전체 조회 |
|/campaign/{id}                  |GET                    |캠페인 ID로 조회 |
|/campaign_total                  |GET                    |캠페인 전체 세부 목록 조회|
|/campaign_total/{id}             |GET                    |캠페인 ID 세부 목록 조회|
|/campaign                      |POST                    |캠페인 생성|
|/campaign/{id}                 |PUT                    |캠페인 변경|
|/campaign/{id}                 |DELETE                 |캠페인 삭제|
## 캠페인 결과
|URL                               |Method                 |기능            |
|----------------------------------|-----------------------|----------------|
|/result                       |GET                    |결과 전체 조회 |
|/result_total                 |GET                    |결과 전체 세부목록 조회 |
|/result/{id}                    |GET                    |결과 ID로 조회|
|/result_total/{id}           |GET                    |결과 ID로 세부목록 조회|
|/result                      |POST                    |결과 생성|
|/result/{id}                 |PUT                    |결과 변경|
|/result/{id}                 |DELETE                 |결과 삭제|

## 광고주
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
- Id Endpoint : `/api/advertiser/{id}`
- Campaign Endpoint : `/api/advertiser/{id}/campaign`
- Detail Endpoint : `/api/advertiser/{id}/campaign_detail`
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
                ]
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
## 광고 내용 변경

Request :
- Method : PUT
- Id Endpoint : `/api/adcontent/id/{id}`
- Content Endpoint : `/api/adcontent/{content}`
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json 
{
    "content" : "string"
}
```
Response :
[Id]
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
[Content]
```json 
{
    "code" : "number",
    "status" : "string",
    "data" : {
            "content": "string",
     }
}
```
## 광고 내용 삭제

Request :
- Method : DELETE
- Endpoint : `/api/adcontent/id/{id}`
- Endpoint : `/api/adcontent/{content}`
- Header :
    - Accept: application/json

Response :

```json 
{
    "code" : "number",
    "status" : "string"
}
```

## 캠페인 
## 캠페인 생성

Request :
- Method : POST
- Endpoint : `/api/campaign`
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
         "id": "long, unique",
        "name": "string",
        "startDate": "date",
        "endDate": "date",
        "subjectList": "int",
        "advertiserId": "string", //조건 "$" + "advertiserId" + "/" + "$" + "advertiserId" + "/" .... sample: $1/$2/$3/
        "adcontentId": "string"  //조건 "$" + "adcontentId" + "/" + "$" + "adcontentId" + "/" .... sample: $1/$2/$3/
     }
}
```
## 캠페인 조회

Request :
- Method : GET
- All Endpoint : `/api/campaign`
- Id Endpoint : `/api/campaign/{id}`
- Detail Endpoint : `/api/campaign_total`
- Id Detail Endpoint : `/api/campaign_total/{id} `
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
            "name": "string",
            "startDate": "date",
            "endDate": "date",
            "subjectList": "int",
            "advertiserId": "string",
            "adcontentId": "string"
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
            "name": "string",
            "startDate": "date",
            "endDate": "date",
            "subjectList": "int",
            "advertiserId": "string",
            "adcontentId": "string"
        }
}
```
[Detail]
```json 
{
    "code" : "number",
    "status" : "string",
    "data" : [
        {
            "id": "long, unique",
            "name": "string",
            "startDate": "date",
            "endDate": "date",
            "subjectList": "int",
            "advertiserId": "string",
            "advertiser": [
                {
                    "id": "long, unique",
                    "name": "string"
                }
            ],
            "adcontentId": "string",
            "adcontent": [
                {
                    "id": "long, unique",
                    "content": "string",
                    "image_url": "string",
                    "btn_text": "string",
                    "btn_url": "string"
                }
            ]
        }
        
        ]
}
```
[Id Detail]
```json 
{
    "code" : "number",
    "status" : "string",
    "data" :
        {
            "id": "long, unique",
            "name": "string",
            "startDate": "date",
            "endDate": "date",
            "subjectList": "int",
            "advertiserId": "string",
            "advertiser": [
                {
                    "id": "long, unique",
                    "name": "string"
                }
            ],
            "adcontentId": "string",
            "adcontent": [
                {
                    "id": "long, unique",
                    "content": "string",
                    "image_url": "string",
                    "btn_text": "string",
                    "btn_url": "string"
                }
            ]
        }
}
```
## 캠페인 변경

Request :
- Method : PUT
- Endpoint : `/api/campaign/{id}`
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json 
{
    "name": "string",
    "startDate": "date",
    "endDate": "date",
    "subjectList": "int",
    "advertiserId": "string",
    "adcontentId": "string"
}
```
Response :

```json 
{
    "code" : "number",
    "status" : "string",
    "data" : {
        "name": "string",
        "startDate": "date",
        "endDate": "date",
        "subjectList": "int",
        "advertiserId": "string",
        "adcontentId": "string"
     }
}
```

## 캠페인 삭제

Request :
- Method : DELETE
- Endpoint : `/api/campaign/{id}`
- Header :
    - Accept: application/json

Response :

```json 
{
    "code" : "number",
    "status" : "string"
}
```

