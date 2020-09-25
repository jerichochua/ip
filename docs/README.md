# User Guide

Duke is a command-line application, helping you to manage your todos, deadlines and events effectively.

## Features 

### Add your tasks
You can add 3 different types of tasks to Duke, which are: todos, deadlines and events.

### Manage your tasks
You can list all your tasks, mark a task as done, or delete the task completely.

### Search your tasks
You can search your tasks with a given keyword.

### Save your tasks
Duke automatically saves all your tasks in a file, which will be loaded the next time you run Duke, ensuring you have your tasks at all times.

## Usage

### `todo` - Adding a todo

Adds a todo task to the application.

Format: `todo DESCRIPTION`

Example of usage:

`todo homework`

Expected outcome:

```
Added: [T][N] homework
You now have 1 task in your list!
```

### `deadline` - Adding a deadline

Adds a deadline task to the application.

Format: `deadline DESCRIPTION /by DATE TIME`

- `DATE` must be of the following format: `dd/mm/yyyy`
- `TIME` must be specified in the 24-hours format e.g. `1800` for 6pm

Example of usage:

`deadline assignment /by 05/01/2020 1800`

Expected outcome:

```
Added: [D][N] assignment (by: 5 Jan 2020 06:00 PM)
You now have 2 tasks in your list!
```

### `event` - Adding an event

Adds an event task to the application.

Format: `event DESCRIPTION /at DATE TIME`

- `DATE` must be of the following format: `dd/mm/yyyy`
- `TIME` must be specified in the 24-hours format e.g. `1800` for 6pm

Example of usage:

`event meeting /at 05/01/2020 1800`

Expected outcome:

```
Added: [E][N] meeting (at: 5 Jan 2020 06:00 PM)
You now have 3 tasks in your list!
```

### `list` - Listing all tasks

List all the tasks to the user.

Format: `list`

Expected outcome:

```
Here are your tasks:
1. [T][N] homework
2. [D][N] assignment (by: 5 Jan 2020 06:00 PM)
3. [E][N] meeting (at: 5 Jan 2020 06:00 PM)
```

### `find` - Finding a task by keyword

Finds tasks whose names contain the keywords. 

Format: `find KEYWORD`

- If no keywords are supplied, then all the tasks will be shown to the user, similar to using `list`.

Example of usage:

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
    1. [T][N] read book
    2. [D][N] return book (by: 31 Dec 2020 11:59 PM)
```

### `done` - Marking a task as done

Marks a task with a given index as done.

Format: `done INDEX`

- The index must be a positive integer: 1, 2, 3, ...

Example of usage:

`done 2`

Expected outcome:

```
I have marked the following task as done:
    [D][Y] assignment (by: 5 Jan 2020 06:00 PM)
```

### `delete` - Deleting a task

Deletes a task with a given index from the application.

Format: `delete INDEX`

- The index must be a positive integer: 1, 2, 3, ...

Example of usage:

`delete 1`

Expected outcome:

```
Ok, I have removed this task:
    [T][Y] homework
You now have 2 tasks in your list!
```

### `clear` - Clearing all tasks

Clears all the tasks in the task list.

Format: `clear`

Expected outcome:

```
Your task list has been cleared!
```

### `exit` - Exiting application

Exits the application.

Format: `exit`

Expected outcome:

```
Bye. Hope to see you again soon!
```
