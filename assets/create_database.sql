-- CSCI201 FINAL Project

CREATE DATABASE Dining_App;
USE Dining_App;

CREATE TABLE Users (
	userID INT PRIMARY KEY AUTO_INCREMENT,
	uname VARCHAR(50) NOT NULL,
	pword VARCHAR(32) NOT NULL
);

CREATE TABLE Dishes (
	dishID INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL
);

CREATE TABLE UserFavorites (
	userFavoriteID INT PRIMARY KEY AUTO_INCREMENT,
	userID INT,
	dishID INT,
	FOREIGN KEY (userID)
		REFERENCES Users(userID),
	FOREIGN KEY (dishID)
		REFERENCES Dishes(dishID)
);

CREATE TABLE DiningHalls (
	DiningHallID INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL
);

CREATE TABLE Meals (
	mealID INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL
);

CREATE TABLE Kitchens (
	kitchenID INT PRIMARY KEY AUTO_INCREMENT,
	diningHallID INT,
	name VARCHAR(50) NOT NULL,
	FOREIGN KEY (diningHallID)
		REFERENCES DiningHalls(diningHallID)
);

CREATE TABLE Allergens (
	allergenID INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL
);

CREATE TABLE DishAllergens (
	dishAllergenID INT PRIMARY KEY AUTO_INCREMENT,
	dishID INT NOT NULL,
	allergenID INT NOT NULL,
	FOREIGN KEY (dishID)
		REFERENCES Dishes(dishID),
	FOREIGN KEY (allergenID)
		REFERENCES Allergens(allergenID)
);

CREATE TABLE DiningHallDishes (
	diningHallDishesID INT PRIMARY KEY AUTO_INCREMENT,
	dishID INT NOT NULL,
	mealID INT NOT NULL,
	kitchenID INT NOT NULL,
	dt DATE NOT NULL,
	FOREIGN KEY (dishID)
		REFERENCES Dishes(dishID),
	FOREIGN KEY (mealID)
		REFERENCES Meals(mealID),
	FOREIGN KEY (kitchenID)
		REFERENCES Kitchens(kitchenID)
)