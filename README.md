 # My Personal Project

## Project Proposal

***What will the application do?***

My food guide application will keep track of food locations that a user wants to try out. It lets a user enter food
locations, view information about them, and add/edit their details. Possible features include listing all places that 
they want to try; showing food locations and their details; marking the food locations they've tried.

***Who will use it?***

Anyone who is a foodie, who loves trying out new food locations, and who needs a way to keep track of upcoming new food 
locations in the city.

***Why is this project of interest to me?***

I am a foodie, and I love trying out new places to eat. Because Vancouver has such a great food scene, there are
constantly new places opening up. It's hard to keep track of all of them and their corresponding details. So, this 
would let me keep track of all these places as soon as I know about them.

## User Stories

**User stories for my food guide application that I have achieved:**
- As a user, I want to be able to add a food location to my food guide
- As a user, I want to be able to view the list of food locations in my food guide
- As a user, I want to be able to select a food location in my food guide and view details about it
- As a user, I want to be able to remove a food location from my food guide
- As a user, I want to be able to mark a place as already tried
- As a user, I want to be able to see the number of food locations in my food guide
- As a user, I want to be able to edit the details of a food location in my food guide

- As a user, I want to have the option to save the entire state of my food guide application to file
- As a user, I want to have the option to reload from that state from file and resume exactly where I left off 

## Future
**User stories for my food guide application that I will attempt in the future (not yet implemented):**
- As a user, I want to see the number of places I've already eaten at (and how many times)
- As a user, I want to be able to select a food location in my food guide and rate whether I would go
back or not
- As a user, I want to be able to select between the console based UI and the windows based GUI


**Refactoring**

If I had more to work on my project, one refactoring I would do would be to create an abstract class (ie EntryWindow) that AddWindow and UpdateWindow would extend (instead of extending JFrame and implementing ActionListener). This abstract class would then extend JFrame and implement ActionListener. This is because these two windows are almost identical aesthetically. UpdateWindow just has one additional area in the window that lets the user change the status of whether the food location has been visited or not. It also has a different button. But, they share a lot of similar fields and methods. So, having an abstract class that contains the repetitive code of the two windows would allow me to have a single point of control, to reduce semantic coupling and to reduce repetitive code.

I would also create an interface called Window that the concrete class ViewWindow and the abstract class EntryWindow (from above) would implement. This would be an interface that would declare the 3 methods that are shared by the 3 concrete windows (AddWindow, UpdateWindow, ViewWindow): instantiateFields(), setUpDisplay() and setUpFrame(). This would be useful for future proofing my project in case I would want to do something to all windows or to loop through all the windows for some reason (ie add them all to a list to iterate through them).

In my FoodGuideGUI constructor, I would also set up 2 more helper methods to group together the lines of code that are related to the same effect to improve readability. So I would group lines 55-59 to extract a helper method called setUpFrame and group lines 65-69 to extract another helper method called instantiateFields.

## Tech Stack
Java, Java Swing

## Notes

***My UI package was adapted from the following sources where indicated***
- https://www.youtube.com/watch?v=iE8tZ0hn2Ws 
- https://www.youtube.com/watch?v=KOI1WbkKUpQ 
- https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html (List Demo Project)

