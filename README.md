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
- ById Endpoint : `/api/advertiser/{id_advertiser}`
- All Endpoint : `/api/advertiser`
- Campaign Endpoint : `/api/advertiser/{id_advertiser}/campaign`
- Detail Endpoint : `/api/advertiser/{id_advertiser}/campaign_detail`
- Header :
    - Accept: application/json

Response :

```json [ById]
{
    "code" : "number",
    "status" : "string",
    "data" : {
         "id" : "long, unique",
         "name" : "string"
     }
}
```
```json [ALL]
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
```json [Campaign]
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
```json [Campaign_Detail]
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
