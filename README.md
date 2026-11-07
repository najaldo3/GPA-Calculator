# GPA Calculator

A simple Java desktop application developed for the Software Construction course.

## Features
- Add, edit, and delete courses.
- Calculate GPA using a 4-point or 5-point scale.
- Automatically update GPA when courses change.
- Display total credit hours.
- Validate course information and prevent duplicate courses.

## Implementation
The application was developed using Java Swing and follows the Model-View-Controller (MVC) architecture.

It uses two design patterns:
- **Singleton:** Maintains one instance of the GPA model.
- **Observer:** Updates the interface when course information changes.

The Course class is implemented as an immutable Abstract Data Type (ADT).

## How to Run
1. Open the project in VS Code with Java support installed.
2. Open `src/App.java`.
3. Click Run to start the application.