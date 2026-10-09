# Doctrine Value Selector

96 predefined mx settings with selectable values.

- Dropdown selector instead of manual text input.
- Numeric values use presets plus the original default.
- Known string values such as `high`, `amplitude`, `diameter`, and `touchScreen` are selectable.
- Apply All uses Shizuku and attempts to write each key to system, global and secure.
- Includes a GitHub Actions workflow to build a debug APK without installing the Android SDK on the phone.

## Build requirements
- JDK 17
- Gradle 8.7
- Android SDK Platform 35
- Android Gradle Plugin 8.6.1

See [TERMUX_BUILD.md](TERMUX_BUILD.md) for phone-only instructions. The project ZIP does not include a local Android SDK; `SDK location not found` must be fixed by configuring a real SDK or using the included GitHub Actions build.

## UI
This version includes the supplied Doctrine logo and a rounded dashboard/profile UI. Buttons, selectors, search fields, cards, progress bar and status pills use rounded/bubble-style corners.
