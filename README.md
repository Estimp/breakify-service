# Breakify - API Service
This repository contains the source code for the RESTful API back-end of the Breakify system.
It exposes the endpoints used by the Android app and the MQTT consumer, handling data storage and business logic.

If you want to modify or extend the functionality of the API, the full source code is included in this repository so you can download, customize, and deploy it as needed.

> This project is open source. Feel free to contribute, open issues, or fork the repository to create your own version.

This project is part of a larger system called Breakify.
The Android app and MQTT consumer components can be found in their respective repositories:
- [breakify-android-releases](https://github.com/Estimp/breakify-android-releases/tree/main) — Android APK releases
- [breakify-mqtt](https://github.com/Estimp/breakify-mqtt) — MQTT message consumer

## Project Architecture
Breakify consists of multiple independent services:

| Component       | Repository                          | Description                  |
|-----------------|-------------------------------------|------------------------------|
| Android App | [breakify-android-releases](https://github.com/Estimp/breakify-android-releases/tree/main) | Compiled APK releases |
| API Service | **This repository** | Back-end RESTful API |
| MQTT Message Consumer | [breakify-mqtt](https://github.com/Estimp/breakify-mqtt) | Subscribes and processes MQTT messages |
