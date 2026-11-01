Cruise Missile mod - Minecraft 1.21.1 / NeoForge 21.1.x

BUILD (needs JDK 21 + Gradle, or copy gradlew/gradle folder from the NeoForge MDK into this folder):
  gradle build          (or ./gradlew build)
Jar appears in build/libs/cruisemissile-1.0.0.jar -> put in your mods folder.

TEST:
  gradle runClient

USE:
  /give @s cruisemissile:cruise_missile
  Right-click to fire. Flies straight, explodes on impact (power 6).
  Tweak EXPLOSION_POWER / SPEED / COOLDOWN_TICKS in CruiseMissileItem.java.
