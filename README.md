# Final-Team-Project-Submission
Multi-module client based project


# Notes from Adam

PERMISSIONS -> each module will handle its own permissions???
- clarifying, can we only ask twice for location permission if both Pet and Doctor use it?
  - if many modules are then asking for location permission, it will be annoying for the user?? and possibly break play store policies??

PR and Review Responsibilities
- is this Gabe and Christians responsibility only?
  - are we also responsible for writing backlog tasks to fix pushed code that is not up to standard?

Git workflow too verbose?
- what is your expectation?

Praduymn says Github Actions is wrong and not expected for our CI/CD
- what are your expectations for CI/CD? should we learn one of these other tools Praduymn says is better?

## Git
tutorial and 1 time walk through on git branch -> push


## core data:
- no api
- all comes from database -> we can create our own dumby data source

## core ui
find all the different ui pieces that we can make into core ui
map is used in Pet, Eat, Doctor, Handyman, etc. -> make a core ui module for map usage

## core design
decide on color theme -> setup global theming
- use figma + coolors.co to help pick colors from a picture palette


## Navigation
### Home
- 2 vertical lazy grid
- no bottom nav

# Gabe Chris
CI/CD Linting - rules for code style - detekt/ktlint
rules for commit messages etc.
- `git commit -m "feat(module): add new feature"`

### Branch Naming
- feature/feature-name/DDMM -> e.g. feature/auth, feature/pet/2210
- bug/task-id -> e.g. bug/1234
- core/data/feature-name/DDMM -> e.g. core/data/repository/2210

### Tutorial on Git
```zsh
# Prerequisite) only need to do this on initial setup once
# A) Remote 'development' already exists
git fetch origin
# B) Create local 'development' branch tracking remote 'development'
git checkout -b development origin/development

# 1) Sync development
git checkout development
git pull origin development

# 2) Create a working branch from development
# this creates a local branch and switches to it
# - naming with /DDMM allows you to keep working on a different branch while others review/merge your PR of the previous branch.
git checkout -b feature/pet/2210 

# 3) Do work, stage, and commit
# `git add -A` stages all changes (new, modified, deleted)
# `git add .` only stages (new and modified)
git add -A
git commit -m "feat(pet): add UI and view model"

# 4) Push branch to origin (sets upstream for future pushes)
# this creates a remote branch and links it to local branch
git push -u origin feature/pet/2210 

# 5) Open PR on GitHub:
  # 1. Go to GitHub repository page
  # 2. Click on "Pull requests" tab
  # 3. Click on "New pull request" button
  # 4. Select base branch as "development" and compare branch as "feature/pet/2210"
  # 5. Click on "Create pull request" button 
  # 6. Fill in PR title and description
  # 7. Click on "Create pull request" button again to submit
  
# 6) Request review from team members: - will try to automate with CODEOWNERS file
  # 1. On the PR page, look for the "Reviewers" section on the right sidebar
  # 2. Click on the gear icon or "Request" button next to "Reviewers"
  # 3. Select team members you want to request a review from
  # 4. Click on "Request" button to send the review request

# 6.5) Address review comments (repeat as needed):
  # 1. Make changes locally based on feedback
  # 2. Stage and commit changes
git add -A
git commit -m "fix(pet): address review comments"
  # 3. Push changes to the same branch
git push


# 7) Keep branch current (rebase onto latest development)
  # why rebase vs merge?
  # - keeps commit history linear and clean
  # - Merging creates a merge commit, which can clutter history on active repos.
  # - Rebase only your own feature branches. 
  # - DON'T REBASE SHARED BRANCHES LIKE DEVELOPMENT OR MAIN
  #   - meaning -> do not checkout to development or main and rebase them
  #   - also, don't force-push shared branches (while checked out on them)
  # - HERE WE ARE REBASING OUR OWN FEATURE BRANCH - SAFE TO DO
  #   - checked out on our feature branch
  
# downloads updates from the remote for all branches; it does not change your working branch.
git fetch origin
# (while your feature branch is checked out) rewrites your local feature branch on top of the latest remote‑tracking development. 
# you are rebasing your feature branch onto it (image below)
git rebase origin/development

# Resolve conflicts if any (continuation of step 7), then:
# - these conflicts will only happen if you and someone else modified the same lines in the same files
#   - which is why we are all primarily working on our own module features
git add -A
git rebase --continue

# 8) Push rebased branch safely (only your branch; never use --force on shared branches)
# - is needed after a rebase because history changed. It safely updates only your remote branch if the remote hasn’t advanced (updated) unexpectedly.
# - After a rebase, your branch’s commit IDs change, so you’ll push with --force-with-lease.
# - It ensures that the remote branch is only overwritten if its state has not changed since the last fetch.
# - Preventing Overwrites: If a collaborator has pushed new commits to the remote branch since the last fetch, git push --force-with-lease will detect this and prevent the push, thus preventing accidental overwriting of their work. 
#   - In contrast, git push --force would blindly overwrite the remote branch, potentially leading to lost work.
git push --force-with-lease

# ---------- ALL REVIEW FEEDBACK ADDRESSED AND CONFLICTS RESOLVED --------------
# -------- END OF FEATURE BRANCH -> REVIEWERS MIGHT REQUEST A CHANGE -----------
# --------------- BUT STOP MAKING OTHER CHANGES TO BRANCH ----------------------

# 8.5) Squash commits if needed - Handled by Gabe and Chris?
    # - Squashing combines multiple commits into one, useful for cleaning up commit history before merging.
    # - Typically done by reviewer or maintainer before merging.
    # 1. Locally, run `git rebase -i origin/development`
    # 2. In the editor, change "pick" to "squash" (or "s") for commits you want to combine
    # 3. Save and close the editor
    # 4. Edit the commit message as needed, then save and close
    # 5. Push the squashed commits with `git push --force-with-lease`

# 9) After PR is merged, delete local and remote branch
# switch back to development (local)
git checkout development 
# update local development branch
git pull origin development 
# delete local feature branch
git branch -d feature/pet/2210 
# delete remote feature branch
git push origin --delete feature/pet/2210 

# 10) Repeat starting from step 2
``` 

#### Rebase vs Merge
![img_2.png](img_2.png)
![img.png](img.png)


#### CODEOWNERS (auto-assign reviewers)
- A CODEOWNERS file maps paths to owners. 
  - When a PR touches those paths, GitHub auto‑requests those users/teams as reviewers. 
- If branch protection enables “Require review from Code Owners,” at least one code owner must approve before merge. 
- Place CODEOWNERS at repo root or in .github/.
```yaml
# file: CODEOWNERS
# Whole repo
* @org/mobile-core-team

        # Android app module
app/** @org/android-owners

        # Core UI module
core/ui/** @Rich-Wilkyness @org/design-system
```

#### Release to production (Gabe and Chris)
1. open a PR from development into main.
  - this will trigger a CI to run final tests.
2. after green CI and approval, squash‑merge the PR.


### Setting Rules (protect development and main)
1. require PR reviews before merging.
2. require status checks to pass before merging.
3. enforce branch naming conventions.


### Linting
- Manual Lint -> Code -> Inspect Code -> Analyze -> select whole project -> OK
- CI -> ./gradlew lint


#### lint.xml structure
```xml
<?xml version="1.0" encoding="UTF-8"?>
<lint>
    <issue id="UnusedResources" severity="warning"/>
    <issue id="HardcodedText" severity="error"/>
</lint>    
```
<lint>: The root element of the lint.xml file.
<issue>: Represents a specific Lint check.
id: (Required) The unique identifier of the Lint check (e.g., UnusedResources, HardcodedText).
severity: (Optional) Sets the severity level for the issue. Possible values include ignore, warning, error, fatal.
enabled: (Optional) Controls whether the issue is enabled or disabled. true to enable, false to disable.
<baseline>: (Optional) Specifies a baseline file to ignore existing issues. The file attribute points to the baseline XML file.
<option>: (Optional) Used to configure specific options for an issue, if supported by that particular check.

### Detekt
- Detekt -> ./gradlew detekt
  - invokes the Gradle task named detekt (must be configured in your project)
  - Analyzes Kotlin code for style, complexity, and potential bugs and produces reports
- requires plugin ->   id("io.gitlab.arturbosch.detekt") version "1.22.0"
detekt {
  toolVersion = "1.22.0"
  config = files("$projectDir/config/detekt/config.yml")
  buildUponDefaultConfig = true
}
```yaml
style:
  MagicNumber:
    active: true
    ignoreNumbers: [-1,0,1,2]
    ignoreHashCodeFunction: true
    ignoreAnnotation: true
```


# Ali
dashboard - home screen
navigation

# Arth + Pradyumn 
Google maps -> add to core/common ui
- current location
- route mapping?? -> ask Adam for clarification
- what parameters to pass in?
- each feature uses its own api key? -> pass as parameter?

- UK -> 7pm - 9pm 
- US -> 2pm - 4pm

# Charles
Theming - core design module

# Terry + Tyson
Auth
- firebase auth

# Architecture Features (not core)
CLEAN 
DATA + DOMAIN + UI (presentation)
## Data
- fake data -> grab it from a repositoryImpl
  - fake data -> called dto
- dto -> model mapper

## Domain
- use cases -> business logic
- model

## UI (presentation)
- view models
- composable
- Intents -> can use if we want to



# Fix project structure 
- missing android directory for java in all modules. 
- Project structure -> module -> right click src -> new directory -> search for java -> select "main/java"
- create new package inside java directory -> name it "com.tc.<module_name>"