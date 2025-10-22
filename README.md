# Final-Team-Project-Submission
Multi-module client based project


# Notes from Adam

PERMISSIONS -> each module will handle its own permissions

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