# API Spec

## Create Advertiser

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

## Get Advertiser

Request :
- Method : GET
- ById Endpoint : `/api/advertiser/{id_advertiser}`
- All Endpoint : `/api/advertiser`
- JoinCampaign Endpoint : `/api/advertiser/{id_advertiser}/campaign`
- Header :
    - Accept: application/json

Response :

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
                "advertiserid": "string",
                "adcontentid": "string"
            }
        ]
     }
}
```
## Update Advertiser

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

## Delete Advertiser

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