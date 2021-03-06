# Chest Boat Dupe
A fabric mod designed to automate the duplication of items using the new chest boats in 1.19.

This mod was thrown together pretty quickly and I intend to update it with a better timing system and a better boat chest inventory detection system.

---
# Notes
### The Timing Cycle
This mod will attempt to dupe every 100 ticks (5 seconds) based off the time of the world. It is done this way to avoid requiring the mod to communicate with other instances of itself. Both users must click the dupe button within that 100 tick timing window for the dupe to work. Due to how this timing system works, best results come from running both clients on the same network.

### Container Detection
Internally, every "chest like" container in the game is the same menu (except shulker boxes). Because of this, the mod detects whether a container is a chest boat by simply reading the name of the container (the mod still supports language differences if you change the language of your game). This can have some side effects, like showing the dupe buttons for containers renamed to "Chest with Boat" (or any language equivalent if you're running the game in that language) even if the container isn't a chest boat.

### Chest Boat Detection
The way this mod determines whether to show the menu to disconnect from the game or show the menu to move the items to your inventory is based solely off of whether you are sitting in a chest boat. Not specifically if the menu is *the* chest boat you're sitting in, but rather if you are sitting in any chest boat at all.
