# as1
cmput 301 assignment 1

video URL: https://www.youtube.com/watch?v=lvsHzxYLmcw

Description of UML: 
  The flow of the application is that the MainActivity has two Fragments that implements IView. 
  
  IView contains notifyChanges() to ensure both fragments have notifyChanges() implemented, the fragments need this
  so that the views can be updated to the user. 
  
  The Fragments also interact with HabitStore, and a list Adapter in order to handle the Habit/Task objects.
  
  Both HabitStore and the Adapters use Task objects, which has a class DaysOfTheWeek to store all of the days of
  the week as DayOfTheWeek objects. 
