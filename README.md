# SENG 300 Project Iteration 3

## Setup Requirements

### Trello

Step One, join our [Trello board (invite link here)](https://trello.com/invite/b/jA8ULP6i/ATTIbdca0dccdd3476d59f53a8aef95a21034C45D8EC/iteration-3).
Here we will assign tasks so we can keep track of who's working on what.

You can find a task with your name on it called "Name - Set up workspace".
Move that task to the "In Progress" section.


### Git & Github

Step Two, you'll need to use [Git (download link here)](https://git-scm.com/downloads). Git is a type of Version Control Software that we'll use to make updates to our code.

Once it's done installing, open Git Bash and type the following command:
```git clone https://github.com/AlanAyy/seng300p3/```

This will copy the code from the Github servers to your computer so you can change it with Eclipse.
If the console asks you to sign in, go ahead and grant it access.

Once it's finished cloning, if you're on Windows:
1. Open the File Explorer
2. Navigate to your `seng300p3` folder (usually it's in `C:\Users\YOUR-NAME\seng300p3`).
3. Check to make sure it cloned successfully (it should have a README.txt, LICENSE file, etc).

Now you can move your Trello ticket to the "Done" section. Now you're ready to get started!


## Git Instructions

If you're on Windows:

1. Open File Explorer.
2. Open the folder where the `seng300p3` project is stored.
3. Right-click any empty space and select "Git Bash Here" from the context menu.

Once you have Git open, the following are some instructions for branching and making pull requests:

1. `git fetch` to fetch changes from a remote repository into your local repo.
2. `git pull` to make sure your local version of the repository is up to date.
3. `git checkout -b "<your name>-<ticket name>"` to make a new branch off of main (use dashes instead of spaces).
4. `git branch` will show you which branches are available. The highlighted one is the branch that you are currently working on. To switch between branches, you can also use `git checkout <branchname>`.
5. At this point you can make changes to your code.
6. `git add .` adds all of the changed files to your commit. You can also use `git add <filename>` to only add specific files.
7. `git commit -m "your commit message"` to commit any changes. Best to commit frequently to make sure changes are saved and in case you need to revert back to a previous commit.
8. `git push` to push your code. git will give you a new command in the terminal. You can copy/paste this in your terminal and follow the instructions from there.
9. Go into the github repo on your browser and create a new pull request.
10. Merge pull request.

Note that at any time you can use `git status` to show the status of your current git environment. Let Alan, Cam or Ella know if you have any problems/questions.
