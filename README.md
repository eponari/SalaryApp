# SalaryApp

## APIs

# POST APIs

- /manageDays/createOffDay

  - Request: {
    "date":"2022-02-14"
  }

- /manageDays/insertWorkingDay

  - Request: {
    "user_id":1,
    "date":"2022-02-14",
    "hours":2
}

- /manageUsers/createUser

  - Request: {
    "fullName":"Edison Ponari",
    "wage":100000
}

# GET APIs

- /manageUsers/getReport
