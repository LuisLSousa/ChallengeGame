# Challenge Game
A Challenge Game to play with friends, developed with Kotlin in Android Studio.

This project was created during the 2020 quarantine, and stemmed from my interest in learning Kotlin and developing an app for the first time.

# Where can I find the app?
Unfortunately, the app is not available (beyond the source code presented here), as it was developed solely as a personal project - a Challenge Game to play with friends when the quarantine was done!

# Add Challenges

To add challenges, add them to the file src\main\assets\challenges.txt \
For example, if you wanted to make a drinking challenge, write the challenge in the form:
```
%s, drink %d times.
```
%s will be substituted automatically for a random player, and %d will be replaced by a random number
between 2 and 5.
If you want to include two players, simply add another %s.
E.g.:
```
%s and %s, drink as many times as there are people between you (clockwise).
```
For simplicity, it is only possible to include two players in a challenge, although this could be changed at a later stage. \
It is also possible to include challenges like:
```
%s, do a handstand.
```
Or:
```
%s, do %d pushups.
```
Or even:
```
Everyone in the room has to wash their hands thoroughly to prevent the spread of Covid.
```

Use your imagination and have fun!
 
