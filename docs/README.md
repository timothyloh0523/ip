# bluey chatbot User Guide

Bluey is a project built to help users keep track of their daily lives by storing a list of tasks, and view or update them accordingly.
Inputs to bluey are via the command-line interface.
Listed below are the key functions of Bluey!

## Features summary
1. Add tasks - 3 types of tasks: to-dos, deadlines, and events
2. Delete tasks
3. Mark tasks as done, or unmark them
4. Find tasks based on keywords
5. List out all existing tasks
6. Store task list upon shutdown and restore upon booting up 
7. End the program

## Detailed Guide to Features

### Adding deadlines

Add a task of type "deadline" into the list of tasks, including the deadline time.
The deadline time format is up to the user.

Command: `deadline {taskDescription} /by {taskDeadline}`
Example: `deadline do CS2113 homework /by Sun 2359`

Expected output:
```
Okay! I'll add this deadline to the list!
  [D][ ] do CS2113 homework (by: Sun 2359)
You now have 1 task in the list!
Successfully updated stored data!
```

### Adding events

Add a task of type "event" into the list of tasks, including the event start and end times.
The start and end time format is up to the user.

Command: `event {taskDescription} /from {eventStartTime} /to {eventEndTime}`
Example: `event Birthday Party /from Sun 1600 /to Sun 1800`

Expected output:
```
Okay! I'll add this event to the list!
  [E][ ] Birthday Party (from: Sun 1600 to: Sun 1800)
You now have 2 tasks in the list!
Successfully updated stored data!

```

### Adding todos

Add a task of type "todo" into the list of tasks.

Command: `todo {taskDescription} `
Example: `todo eat lunch`

Expected output:
```
Okay! I'll add this todo to the list!
  [T][ ] eat lunch
You now have 3 tasks in the list!
Successfully updated stored data!
```

### List existing tasks

Lists out all existing tasks, numbered by order of addition, one by one.
An error message will be shown if no tasks are present.

Command: `list`

Expected output:
If list is not empty:
```
Here is your list!
1. [D][ ] do CS2113 homework (by: Sun 2359)
2. [E][ ] Birthday Party (from: Sun 1600 to: Sun 1800)
3. [T][ ] eat lunch

```

If list is empty:
```
I have not been given any tasks! Start by creating an event, todo or deadline :)
```

### Marking tasks as done

Mark an existing task as done. Users can use the list function to find the corresponding task number desired first.
Marked tasks are reflected with an "X" in a pair of square brackets.

Command: `mark {taskNumber} `
Example: `mark 1`

Expected output:
If task is not yet marked as done:
```
Ok! Task number 1 marked as done!
  [D][X] do CS2113 homework (by: Sun 2359)
Successfully updated stored data!
```
If task is already marked as done:
```
Task already marked as done!
Successfully updated stored data!
```

### Unmarking tasks (as not done)

Unmark an existing task, into an undone task. Users can use the list function to find the corresponding task number desired first.
Unmarked tasks are reflected with an " " in a pair of square brackets.

Command: `unmark {taskNumber} `
Example: `unmark 1`

Expected output:
If task is currently marked as done:
```
Ok! Task number 1 marked as not done yet!
  [D][ ] do CS2113 homework (by: Sun 2359)
Successfully updated stored data!
```
If task is already not marked as done:
```
Task already marked as not done yet!
Successfully updated stored data!
```

### Deleting tasks 

Delete an existing task, removing it from the list.

Command: `delete {taskNumber} `
Example: `delete 1`

Expected output:
```
Okay! I've deleted this task from the list!
  [D][ ] do CS2113 homework (by: Sun 2359)
You now have 2 tasks in the list!
Successfully updated stored data!
```

### Finding tasks

List out all tasks with the specified word included.
All tasks listed are with their corresponding task number from the "list" function.
Only one word can be searched at a time, and it is case sensitive.

Command: `find {word} `
Example: `find Party`

Expected output:
```
Okay, here is your list of matching tasks:
1. [E][ ] Birthday Party (from: Sun 1600 to: Sun 1800)
```

### Shutdown

Shut down the chatbot and end the program.

Command: `bye`
Alternative command: `exit`

Expected output:
```
Goodbye! See you soon :)
```