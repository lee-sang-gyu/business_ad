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
|/campaign_only                  |GET                    |캠페인 전체 (세부 목록제외) 조회|
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
                "startDate": "date",
                "endDate": "date",
                "subjectList": "integer",
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
                "startDate": "date",
                "endDate": "date",
                "subjectList": "integer",
                "adcontentList": [
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
        "advertiserList": "Advertiser"
        [
            {
                "id": "long, unique",
                "name": "string"
            }
        
        ],
        "adcontentList": "Adcontent"
        [
            {
                "id": "long, unique"
                "content": "string"
                ...
            }
        ]
     }
}
```
## 캠페인 조회

Request :
- Method : GET
- All Endpoint : `/api/campaign`
- Id Endpoint : `/api/campaign/{id}`
- OnlyCampaign Endpoint : `/api/campaign_only`
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
            "advertiserList": "Advertiser"
            [
                {
                    "id": "long, unique",
                    "name": "string"
                }
                ...

            ],
            "adcontentList": "Adcontent"
            [
                {
                    "id": "long, unique"
                    "content": "string"
                    ...
                }
            ]
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
             "advertiserList": "Advertiser"
                {
                    "id": "long, unique",
                    "name": "string"
                }
            ,
            "adcontentList": "Adcontent"
                {
                    "id": "long, unique"
                    "content": "string"
                    ...
                }
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
            "subjectList": "int"
            
        }
        
        ]
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
    "advertiserList": "Advertiser",
    "adcontentList": "Adcontent"
}
```
Response :

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
            "advertiserList": "Advertiser"
                {
                    "id": "long, unique",
                    "name": "string"
                }
            ,
            "adcontentList": "Adcontent"
                {
                    "id": "long, unique"
                    "content": "string"
                    ...
                }
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
## 결과 
## 결과 생성

Request :
- Method : POST
- Endpoint : `/api/result`
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json 
{
    "id" : "long, unique",
    "campaignId" : "string"
    "cliks" : "int",
    "exposure" : "int"
}
```

Response :

```json 
{
    "code" : "number",
    "status" : "string",
    "data" : {
        "id" : "long, unique",
        "campaignId" : "string"
        "cliks" : "int",
        "exposure" : "int"
     }
}
```
## 결과 조회

Request :
- Method : GET
- All Endpoint : `/api/result`
- Id Endpoint : `/api/result/{id}`
- Detail Endpoint : `/api/result_total`
- Id Detail Endpoint : `/api/result_total/{id} `
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
            "campaignId": "long",
            "cliks": "int",
            "exposure": "int"
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
            "campaignId": "long",
            "cliks": "int",
            "exposure": "int"
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
            "campaignId": "long",
            "cliks": "int",
            "exposure": "int"
            "campaign": {
                "id": "long, unique",
                "name": "string",
                "startDate": "date",
                "endDate": "date",
                "subjectList": "int",
                "advertiserList": [
                    {
                        "id": "long, unique",
                        "name": "string"
                    }
                ],
                "adcontentList": [
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
            "campaignid": "long",
            "cliks": "int",
            "exposure": "int"
            "campaign": {
                "id": "long, unique",
                "name": "string",
                "startDate": "date",
                "endDate": "date",
                "subjectList": "int",
                "advertiserId": "string",
                "advertiserList": [
                    {
                        "id": "long, unique",
                        "name": "string"
                    }
                ],
                "adcontentId": "string",
                "adcontentList": [
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
}
```
## 결과 변경

Request :
- Method : PUT
- Endpoint : `/api/result/{id}`
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json 
{
    "campaignId" : "string"
    "cliks" : "int",
    "exposure" : "int"
}
```
Response :

```json 
{
    "code" : "number",
    "status" : "string",
    "data" : {
            "campaignId" : "string"
            "cliks" : "int",
            "exposure" : "int"
     }
}
```

## 결과 삭제

Request :
- Method : DELETE
- Endpoint : `/api/result/{id}`
- Header :
    - Accept: application/json

Response :

```json 
{
    "code" : "number",
    "status" : "string"
}
```
