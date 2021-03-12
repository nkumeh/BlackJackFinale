# Blackjack: Group 25
Alex Ivanoff, Kristine Umeh, and Maria Piper
This project uses the requirements from [Programming Assignment 8](https://docs.google.com/document/d/1nbJzy30sAu-8iPJTuJEHzrmCu72iVjISWSd8KnweoDo/edit)

Note: I set up this project with Java 15 and Gradle.
## Group Agreements

#### Friday, March 12, 2021 - Meeting Deadlines
- **Saturday, March 13, 2021**: Alex
  - Card - class w/ tests
  - Enum - for suit and name
- **Monday, March 15, 2021**: Kristine
  - Deck - Methods print, shuffle, add, remove
  - Deck - tests
- **Wednesday, March 17, 2021**: Maria
  - Deck - sort w/ tests
  - Hand - implementation, tests, main


As a team, we agree to:
- Include things like testing, branching, etc. 
- When we will get drafts done
- Testing Style, etc.
- Meet weekly to discuss progress

## Repository Set Up
[Clone this repository](https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/cloning-a-repository) to your computer. 
I **highly** suggest doing this from the command line. 

Open your terminal, navigate to the repository and check the branch you are on by typing ```git branch``` It should say ```main```.
If it says ```master``` follow [these instructions](https://www.git-tower.com/learn/git/faq/git-rename-master-to-main/) to update
the branch name.

## Git Practices
This may seem cumbersome at first, but software developers are often required to follow strict processes like these when they work on teams, 
so it's great practice for us. 

### 1. Check that you are on the main branch
Before doing anything, make sure you are on the main branch. 
```shell
git branch 
// returns a list of all branches 
   ```
There should be an asterisk next to main. ```* main```

### 2. Make sure the code is up to date
2. Pull any merged changes from the main branch using:
```shell
git pull origin main
``` 
sometimes you can use ```git pull``` as well.

### 3. Create a new branch to work from.  
The point of creating new branches is to prevent merge conflicts and make pull requests/code 
reviews easier. 
If we all developed on the ```main``` branch, we will end up with A LOT of merge conflicts, 
which are not fun. 
I adapted these branch names from deepsource.io's ["Git branch naming conventions"](https://deepsource.io/blog/git-branch-naming-conventions/).
   - Create new branches using: 
     ```shell
     git checkout -b  <branch name>
     ```
     The ```-b``` creates a new branch from the branch you are on.
       - If you are writing a new feature/class that hasn't been written before:
         ```shell
         git checkout -b feature-add-<Ojbect Name>-your initials
         ``` 
            - ex. ```git checkout -b feature-add-ChessBoard-MP```
       - If you are refactoring a feature that has already been merged to the main branch.
         ```shell
         git checkout -b refactor-<Ojbect Name>-yourInitials
         ``` 
         - ex.```git checkout -b refactor-ChessBoard-MP```  
       - If you are doing something else not defined above, please try to use a similar naming convention.

### 4. Commit your changes frequently
Whenever you get the code to a good place or "working" state. Add the files to the stage using 
```shell
git add <filename>
``` 
ex. ```git add src/Chessboard.java```. 

Repeat for each file you want to add to the commit. It's easier if
you only include the files that are directly related to the code you want in your pull request.

GitHub's interface encourages many smaller commits. If you just push a lot of code,
in one commit, it will only show one commit on your dashboard. It *looks* better to have lots of commits --
days and times with lots of commits get greener and greener, which makes it look like you are working hard!

I like to think of committing like old school 'saving' back before we could rely on auto save. My teachers
always said, 'save early and often.' Committing is much the same, so **commit early and often!**

#### TIP: Always add files using ```git add filename```. Avoid using ```git add .``` or ```git add```. 
These will add every file that has been changed in the repository.
It clutters pull requests making them more difficult to review. 

Commit your changes using 
```shell
git commit -m "Your excellent commit message"
```

Please use the best practices for writing commit messages from [Chris Beams](https://chris.beams.io/posts/git-commit/).

In summary, these are some good guidelines for writing good commit messages:
1. Limit the subject line to 50 characters
2. Capitalize the subject line
3. Do not end the subject line with a period
4. Use the imperative mood in the subject line ("Fix" not "Fixed")

If you need to use the extended body of a commit message, use the body to explain what and why vs. how.

### 5. When you're ready to push your code
#### NEVER USE ```git push``` or ```git push origin main```. Just pretend these don't exist. :)

1. Be sure to update your repository with any changes that have been pushed.
    ```shell
    git checkout main
    ```
2. Pull any changes from the remote repository. If you have your repo set up, you can use ```git pull``` too.
    ```shell
    git pull origin main
    ```
3. Go back to the branch you want to push.
    ```shell
    git checkout <branchname>
    ```
4. Merge the changes you pulled from main.
    ```shell 
    git merge main
    ```
   *This may result in a VIM window appearing. If it does. Don't panic. If you want to edit the message,
   type 'i' to go into insert mode.
   When you've finished typing your message, press escape, then ```:wq``` to save and quit.*
5. If you have any **merge conflicts**, figure out how to resolve them in this branch. See this guide from
   [Atlassian](https://support.atlassian.com/bitbucket-cloud/docs/resolve-merge-conflicts/).
   If you take a while to resolve merge conflicts, repeat steps 2-5 before moving onto step 6. You can also ask Maria.
6. If all is well, push your changes to a remote branch. 
   ```shell
   git push origin <yourbranch name>
   ``` 
   This will push your changes to a remote branch. DO NOT push to main.

### 6. Create a pull request
Go to the repository in GitHub. At the top menu, go to 'Pull Requests'. It usually shows any recently 
pushed branches
and gives the option to create a pull request. If that isn't there, follow this 
[GitHub Docs Guide](https://docs.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request).
- Feel free to update the title
- Write notes about what you did in the text box. 

Once you've added the information about the pull request, please assign a reviewer to give you feedback on your
code. You can do this by:
- Navigating to Reviewers in the right sidebar.
- Either choose one of the people who is suggested, or open the dropdown and pick the person.
*If you're having trouble following, please refer to [this guide](https://docs.github.com/en/github/collaborating-with-issues-and-pull-requests/requesting-a-pull-request-review).*

NOTE: This project has Gradle and we will set up GitHub workflows as well so that every time we create a pull request,
the test files we've written will be run as well. We shouldn't merge anything unless there is a green check indicating 
that all the tests pass.

### 7. Review pull requests
If you are reviewing another person's code, please follow [this guide](https://docs.github.com/en/github/collaborating-with-issues-and-pull-requests/approving-a-pull-request-with-required-reviews).
If you have feedback on a particular line or chunk of code, you can add a comment to that line by hovering over the line number. 
A 'plus' sign will come up. Click on it and add your comments. When you are done reviewing, add a summary with overall feedback
to the review and "submit."

It would be good to check the button when it's ok to merge, needs changes, etc., so let's review those. 

### 8. Merge your pull request
Once all changes are addressed, the branch author can merge the pull request! Woot!
You can also delete your feature branch in your command line to reduce clutter```git branch -d <branchname>```.

## Updating this README
If you have any proposed changes to this README, please create a pull request to propose updates and practice. 