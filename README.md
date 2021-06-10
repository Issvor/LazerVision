# LazerVision 1.0
Minecraft spigot plugin that causes explosions up to 30 blocks away from the player's POV.

/lazervision enable

/lazervision disable

Inspired by Dream's video where they tried to beat minecraft with explosions happening whereever they looked.

This plugin is currently disgned to 100% properly function with just one person, but can be used nearly perfectly with multiple people.

If multiple people run the command, it should work as intended. I haven't had much chance on testing, but from the very little amount of tests I did, it did cause explosions at two different player's POVs.

if multiple people ran the command, and one person runs the disable command, the last person to enable the command will have it disabled, and everyone else will continue having it no matter what.

This happens because I have a scheduler running a "createExpolosion" function every quarter second (5 ticks), and the scheduler gets saved in an int variable named "id". Whenever a second person runs the command, the first person's id gets overrided, so if the first person runs the disable command, the plugin calls the "cancelTask" function with "id".
