
# Boss Assassinator RPG Game

## Project Overview
Built a turn-based RPG game, *Boss Assassinator*, using Java and Gradle. Implements design patterns to manage gameplay: decorator pattern for character skill upgrades, factory pattern for enemy creation, and state pattern for game state transitions (combat, exploring, game over). Includes unit tests to ensure functionality.

## Tech Stack
- **Language**: Java
- **Build Tool**: Gradle
- **Design Patterns**: 
  - Decorator pattern for wrapping characters with experience and skills (e.g., Flying Roundhouse Kick, Nunchuck Strike)
  - Factory pattern for creating enemies of varying skill levels based on game level (e.g., SmallEnemy, MediumEnemy, BossEnemy)
  - State pattern for transitioning between combat, exploring, and game over states
- **Testing**: JUnit for unit testing

## Challenges & Solutions
- Managed combat logic with dynamic skill application; used decorators to add skills without altering base classes.
- Developed unit tests to validate enemy creation, skill decoration, and state transitions, catching edge cases like invalid enemy types.

## What I Learned
- Gained hands-on experience with design patterns, enhancing code reusability and maintainability.
- Improved problem-solving with object-oriented design in a game context.
- Strengthened testing skills using JUnit to ensure code reliability.
