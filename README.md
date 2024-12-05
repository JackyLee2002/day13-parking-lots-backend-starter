Backend

### ParkingManager
You are an experienced Java software engineer working on a project about digitizing Parking lots management.
Parking manager is responsible for managing three parking lots: 
● The Plaza Park (9 parking capacity) 
● City Mall Garage (12 parking capacity) 
● Office Tower Parking (9 parking capacity)
The Parking Manager have employed three Parking Boys to help manage these parking lots, each utilizing a specific parking strategy:
Standard parking strategy, Smart parking strategy, Super Smart parking strategy implement, their attribute names within the Parking Manager should be StandardParkingBoy,SuperParkingBoy and SuperSmartParkingBoy.
Please create A parking manager class, given the above 3 parking lots and 3 parking boys, manager have the following 3 method: 
1. getAllParkingLots(): return all parking lots park(plate number, strategy)
2. request the correct parking boy to do the parking job and return a Ticket(plate number)
3. fetch the car from the corresponding parking lot and return the car.


### Unit Test for ParkingManager
You are an experienced Java software engineer working on a project about digitizing parking lot management. You have already created a ParkingManager class that manages three parking lots and employs three parking boys, each utilizing a specific parking strategy. The ParkingManager class has the following methods:

getAllParkingLots(): Returns all parking lots.
park(String plateNumber, String strategy): Requests the correct parking boy to park the car based on the strategy and returns a Ticket object containing the plate number.
fetch(String ticket): Fetches the car from the corresponding parking lot using the ticket and returns the car.
The ParkingManager class manages the following parking lots:

The Plaza Park (9 parking capacity)
City Mall Garage (12 parking capacity)
Office Tower Parking (9 parking capacity)
The ParkingManager employs three parking boys with the following strategies:

StandardParkingBoy: Standard parking strategy
SuperParkingBoy: Smart parking strategy
SuperSmartParkingBoy: Super smart parking strategy
Your task is to write unit tests for the ParkingManager class using JUnit5 in a Spring Boot environment. The unit tests should cover the following scenarios:

Initialization Test: Verify that the ParkingManager initializes correctly with the three parking lots and three parking boys.
Get All Parking Lots Test: Verify that the getAllParkingLots() method returns all the parking lots.
Park Car Test: Verify that the park(String plateNumber, String strategy) method requests the correct parking boy to park the car and returns a valid Ticket.
Fetch Car Test: Verify that the fetch(String ticket) method fetches the car from the corresponding parking lot using the ticket and returns the correct car.
Make sure all the test case function name ae written using the should_when_given format


### Unit Test Additional (check invalid plate num)
Write another testcase that covers the scenario where the platenumber does not following the format ^[A-Z]{2}-\\d{4}$


### Generate Test for ParkingLot GET /parking-lots and POST /park/{plateNumber}/{parkingBoy}
Given the ParkingLotController, write JUnit5 Test that uses mockMVC to test the get /parking-lots and post /park/{plateNumber}/{parkingBoy} to ensure 100% branch and line coverage.
The test should use the given_when_then format for the function naming, and add the given when then comment within the test to make it clearer.
###


Frontend

###
Here is a react app, the requirement is to Display Parking Lot Status The system should clearly display the current status of all three parking lots, showing the license plate of the car parked
in each parking position to provide a real-time view of parking lot usage.

When the app loads, using an useeffect, it should call this api at localhost:8080/parking-lots and set that result as a state called parking Lots.

The app should use useContext and useReducer to globally store the state and modify the states using dispatch instead of directly modifying the application's states

Below is an example of the API that would be called to get the status of the parking lots
[
    {
        "id": 1,
        "name": "The Plaza Park",
        "tickets": [
            {
                "plateNumber": "TE-1234",
                "position": 1,
                "parkingLot": 1
            }
        ],
        "capacity": 9,
        "availablePositionRate": 0.8888888888888888,
        "availableCapacity": 8,
        "full": false
    },
    {
        "id": 2,
        "name": "City Mall Garage",
        "tickets": [],
        "capacity": 12,
        "availablePositionRate": 1.0,
        "availableCapacity": 12,
        "full": false
    },
    {
        "id": 3,
        "name": "Office Tower Parking",
        "tickets": [],
        "capacity": 9,
        "availablePositionRate": 1.0,
        "availableCapacity": 9,
        "full": false
    }
]
###